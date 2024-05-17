package avito.controller;

import avito.dto.Role;
import avito.dto.comment.CreateCommentDto;
import avito.dto.comment.FullCommentDto;
import avito.dto.comment.ResponseWrapperComment;
import avito.entity.Comment;
import avito.entity.User;
import avito.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import avito.exception.NonExistentCommentException;
import avito.service.AdService;
import avito.service.CommentService;
import avito.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/ads")
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class CommentController {
    private final CommentMapper commentMapper;
    private final AdService adService;
    private final CommentService commentService;
    private final UserService userService;

    /**
     * Return all comments of target ad
     * @param adId {@code id} of target ad (URL variable)
     * @return {@link HttpStatus#OK} with all comments in {@link avito.dto.comment.ResponseWrapperComment} instance if target ad existed, <br>
     * {@link HttpStatus#NOT_FOUND} otherwise
     * @author gturumtaev
     */
    @GetMapping("/{id}/comments")
    public ResponseEntity<ResponseWrapperComment> getAdComment(@PathVariable("id") Integer adId) {
        if (isAdExisted(adId)) {
            return ResponseEntity.ok(commentMapper.mapCommentListToWrapper(commentService.findAllByAdId(adId)));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    /**
     * Create comment for target ad
     * @param authentication (inject) user auth data
     * @param adId {@code id} of target ad
     * @param createCommentDto new comment data
     * @return {@link HttpStatus#OK} with created comment in {@link avito.dto.comment.FullCommentDto} instance
     * if target ad existed, <br>
     * {@link HttpStatus#NOT_FOUND} otherwise
     * @author gturumtaev
     */
    @PostMapping("/{id}/comments")
    public ResponseEntity<FullCommentDto> createCommentsAd(Authentication authentication,
                                                           @PathVariable("id") Integer adId,
                                                           @RequestBody CreateCommentDto createCommentDto) {
        if (isAdExisted(adId)) {
            return ResponseEntity.ok(commentMapper.mapCommentToFullCommentDto(commentService.createComment(authentication.getName(),adId,createCommentDto)));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    /**
     * Delete target comment
     * @param authentication user auth data
     * @param adId id of target ad
     * @param commentId id of target comment
     * @return {@link HttpStatus#OK} if target ad and comment are existed and initiator is admin or comment's author, <br>
     * {@link HttpStatus#NOT_FOUND} if target ad or comment not existent, <br>
     * {@link HttpStatus#FORBIDDEN} if initiator not admin and not comment's author
     * @author gturumtaev
     */
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(Authentication authentication,
                                           @PathVariable("adId") Integer adId,
                                           @PathVariable("commentId") Integer commentId) {
        Optional<Comment> targetCommentOpt = commentService.findById(commentId);
        if (targetCommentOpt.isPresent() && isAdExisted(adId)) {
            if (isUserAdminOrAuthor(authentication.getName(), targetCommentOpt)) {
                commentService.deleteById(commentId);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    /**
     * Edit target comment
     * @param authentication user auth data
     * @param adId id of target ad
     * @param commentId id of target comment
     * @param fullCommentDto object with new comment's data
     * @return {@link HttpStatus#OK} with edited comment in {@link FullCommentDto}
     * if target ad and comment are existed and initiator is admin or comment's author, <br>
     * {@link HttpStatus#NOT_FOUND} if target ad or comment not existent, <br>
     * {@link HttpStatus#FORBIDDEN} if initiator not admin and not comment's author
     * @author gturumtaev
     */
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<FullCommentDto> updateComment(Authentication authentication,
                                                        @PathVariable("adId") Integer adId,
                                                        @PathVariable("commentId") Integer commentId,
                                                        @RequestBody FullCommentDto fullCommentDto
    ) {
        Optional<Comment> targetComment = commentService.findById(commentId);
        if (targetComment.isPresent() && isAdExisted(adId)) {
            if (isUserAdminOrAuthor(authentication.getName(), targetComment)) {
                return ResponseEntity.ok(commentService.updateComment(commentId, fullCommentDto));
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    /**
     * Check that user is admin or comment's author
     * @param email target user email
     * @param targetCommentOpt target comment (Optional)
     * @return {@code true} if user with {@code targetEmail} is admin or author of {@code targetComment}, <br>
     * {@code false} if not
     * @author gturumtaev
     */
    private boolean isUserAdminOrAuthor(String email, Optional<Comment> targetCommentOpt) {
        User initiator = userService.findUserByEmail(email);
        return initiator.getRole() == Role.ADMIN
                || initiator.getEmail()
                .equals(targetCommentOpt.orElseThrow(NonExistentCommentException::new).getAuthor().getEmail());
    }
    /**
     * Check that target ad is existed
     * @param adId id of target ad
     * @return true if ad existed, <br>
     * false otherwise
     * @author gturumtaev
     */
    private boolean isAdExisted(Integer adId) {
        return adService.findById(adId).isPresent();
    }
}
