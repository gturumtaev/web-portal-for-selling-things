package avito.service;

import avito.dto.Role;
import avito.dto.user.RegisterDTO;
import avito.dto.user.UserDTO;
import avito.entity.User;
import avito.mapper.UsersMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import avito.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author gturumtaev
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository repository;
    @Mock
    private PasswordEncoder encoder;
    @Mock
    private ImageService imageService;
    @Mock
    private Authentication authentication;
    @Spy
    private UsersMapper userMapper;

    @InjectMocks
    private UserService userService;


    @Test
    public void findUserDtoByEmailShouldReturnUserDto() {
        User testUser = new User("em", "f", "l", "p", Role.USER, "i", "pass", null, null);

        when(repository.findByEmail(testUser.getEmail())).thenReturn(testUser);

        UserDTO result = userService.getUserInfo(testUser.getEmail());

        assertInstanceOf(UserDTO.class, result);
    }

    @Test
    public void saveUserByRegReqShouldCallRepositorySave() {
        RegisterDTO testRR = new RegisterDTO();
        testRR.setPassword("p");
        testRR.setUsername("u");
        testRR.setPhone("p");
        testRR.setRole(Role.USER);
        testRR.setLastName("l");
        testRR.setFirstName("f");

        when(repository.save(any())).thenReturn(null);

        userService.saveUserFromReg(testRR, "enPass");

        verify(repository, times(1)).save(any());
    }

    @Test
    public void editUserShouldReturnEditedUserDto() {
        User testUser = new User("em", "f", "l", "p", Role.USER, "i", "pass", null, null);
        UserDTO testUD = new UserDTO();
        testUD.setFirstName("fn");
        testUD.setLastName("ln");
        testUD.setPhone("ph");

        when(repository.findByEmail(testUser.getEmail())).thenReturn(testUser);
        when(repository.save(any())).thenReturn(null);

        UserDTO result = userService.updateUserInfo(testUser.getEmail(), testUD);

        assertInstanceOf(UserDTO.class, result);
    }
}