package avito.mapper;

import org.springframework.stereotype.Component;
import avito.entity.Comment;
import avito.entity.User;
import avito.dto.comment.FullCommentDto;
import avito.dto.comment.ResponseWrapperComment;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gturumtaev
 */
@Component
public class CommentMapper {
    /**
     * Map {@link List} of {@link Comment} to {@link ResponseWrapperComment}
     *
     * @param comments target {@link List} of {@link Comment}
     * @return created {@link ResponseWrapperComment}
     * @author gturumtaev
     */
    public ResponseWrapperComment mapCommentListToWrapper(List<Comment> comments) {
        List<FullCommentDto> commentDtoList = comments.stream().map(this::mapCommentToFullCommentDto).collect(Collectors.toList());
        return new ResponseWrapperComment(commentDtoList.size(), commentDtoList);
    }
    /**
     * Map {@link Comment} to {@link FullCommentDto}
     *
     * @param comment target {@link Comment}
     * @return created {@link FullCommentDto}
     * @author gturumtaev
     */
    public FullCommentDto mapCommentToFullCommentDto(Comment comment) {
        User author = comment.getAuthor();
        return new FullCommentDto(
                author.getUserId(),
                author.getImage(),
                author.getFirstName(),
                comment.getCreatingTime(),
                comment.getId(),
                comment.getText()
        );
    }

}
