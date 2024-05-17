package avito.service;

import avito.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author gturumtaev
 */
@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserService userService;
    /**
     * Return {@link UserDetails} of target user
     * @param username target user's username (email)
     * @return {@link UserDetails} of target user
     * @throws UsernameNotFoundException if user with this username is not existed
     * @author gturumtaev
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User targetUser = userService.findUserByEmail(username);
        if (targetUser != null) {
            return buildUserDetails(targetUser);
        }
        throw new UsernameNotFoundException("Unknown user" + username);
    }

    /**
     * Build {@link UserDetails} for target user
     * @param targetUser target {@link User}
     * @return {@link UserDetails} for target user
     * @author gturumtaev
     */
    private UserDetails buildUserDetails(User targetUser) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(targetUser.getEmail())
                .password(targetUser.getEncodedPassword())
                .roles(targetUser.getRole().name())
                .build();
    }
}
