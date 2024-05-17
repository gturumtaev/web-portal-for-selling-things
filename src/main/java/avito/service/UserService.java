package avito.service;

import avito.dto.user.NewPassword;
import avito.dto.user.RegisterDTO;
import avito.dto.user.UserDTO;
import avito.entity.User;
import avito.mapper.UsersMapper;
import avito.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

/**
 * @author gturumtaev
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UsersMapper usersMapper;
    private final PasswordEncoder encoder;
    private final ImageService imageService;
    /**
     * Set user's password
     * @param authentication target user's email (username)
     * @param newPasswordDTO object with new and current passwords
     * @return {@code true} if password successfully changed, <br>
     * {@code false} if current password is incorrect
     * @author gturumtaev
     */
    public boolean setPassword(NewPassword newPasswordDTO, Authentication authentication) {
        User targetUser = userRepository.findByEmail(authentication.getName());
        if (encoder.matches(newPasswordDTO.getCurrentPassword(),targetUser.getEncodedPassword())) {
            targetUser.setEncodedPassword(encoder.encode(newPasswordDTO.getNewPassword()));
            save(targetUser);
            return true;
        }
        return false;
    }

    /**
     * Save user
     * @param user {@link User}
     * @author gturumtaev
     */
    private void save(User user) {
        userRepository.save(user);
    }

    /**
     * Save user from {@link avito.dto.user.RegisterDTO} (register request)
     * @param registerDTO register request with new user's data
     * @param passwordEncode new user's encoded password
     * @author gturumtaev
     */
    public void saveUserFromReg(RegisterDTO registerDTO, String passwordEncode) {
        userRepository.save(usersMapper.mapToUserFromRegisterDTO(registerDTO, passwordEncode));
    }

    /**
     * Return {@link avito.dto.user.UserDTO} of target user
     * @param email target user's email (username)
     * @return {@link avito.dto.user.UserDTO} of target user
     * @author gturumtaev
     */
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Getting info user
     * @param email email from user
     * @return {@link avito.dto.user.UserDTO} info wrapper user
     * @author gturumtaev
     */
    public UserDTO getUserInfo(String email) {
        return usersMapper.mapToUserDTOFromUser(userRepository.findByEmail(email));
    }
    /**
     * Edit user
     * @param email target user's email (username)
     * @param userDTO {@link UserDTO} with user's new data
     * @return edited user in {@link UserDTO} instance
     * @author gturumtaev
     */
    public UserDTO updateUserInfo(String email, UserDTO userDTO) {
        User user = userRepository.findByEmail(email);
        user.setLastName(userDTO.getLastName());
        user.setFirstName(userDTO.getFirstName());
        user.setPhone(userDTO.getPhone());
        save(user);
        return usersMapper.mapToUserDTOFromUser(user);
    }

    /**
     * Upload user's avatar
     * @param targetEmail target user's email (username)
     * @param image {@link MultipartFile} with avatar
     * @author gturumtaev
     */
    @Transactional
    public void uploadImage(MultipartFile image, String targetEmail) {
        User targetUser = findUserByEmail(targetEmail);
        targetUser.setImage(imageService.uploadUserAvatar(targetEmail, image));
        save(targetUser);
    }
}
