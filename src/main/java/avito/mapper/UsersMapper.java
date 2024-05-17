package avito.mapper;

import org.springframework.stereotype.Component;
import avito.dto.user.RegisterDTO;
import avito.dto.user.UserDTO;
import avito.entity.User;

import java.util.ArrayList;

/**
 * @author gturumtaev
 */
@Component
public class UsersMapper {
    /**
     * Map {@link User} to {@link UserDTO}
     * @param user target {@link User}
     * @return created {@link UserDTO}
     */
    public UserDTO mapToUserDTOFromUser(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getRole(),
                user.getImage()
        );
    }
    /**
     * Create new {@link User} from register request
     * @param registerDTO register request
     * @param passwordEncode user's encoded password
     * @return created {@link User}
     */
    public User mapToUserFromRegisterDTO(RegisterDTO registerDTO,String passwordEncode) {
        return new User(
                registerDTO.getUsername(),
                registerDTO.getFirstName(),
                registerDTO.getLastName(),
                registerDTO.getPhone(),
                registerDTO.getRole(),
                null,
                passwordEncode,
                new ArrayList<>(),
                new ArrayList<>()
        );
    }
}
