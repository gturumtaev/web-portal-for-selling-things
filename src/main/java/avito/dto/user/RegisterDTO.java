package avito.dto.user;

import lombok.Data;
import avito.dto.Role;

/**
 * @author rvorozheikin
 */
@Data
public class RegisterDTO {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
}
