package com.hadouken900.MusicReleases.services;

import com.hadouken900.MusicReleases.entities.Role;
import com.hadouken900.MusicReleases.entities.User;
import com.hadouken900.MusicReleases.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;
    @MockBean
    UserRepository userRepository;
    @MockBean
    BCryptPasswordEncoder bCryptPasswordEncoder;

    List<User> users = new ArrayList<>(Arrays.asList(
            new User(1L,"John","123"),
            new User(2L,"Alex","555"),
            new User(3L,"Mike","000"),
            new User(4L,"Jim","777")
    ));

    @Test
    void loadUserByUsernameSuccess() {
        String username = "Jim";
        User j = users.get(3);
        j.setRoles(Collections.singleton(new Role(1L, "ADMIN")));

        Mockito.doReturn(j).when(userRepository).findByUsername(username);

        assertEquals("Jim",userService.loadUserByUsername(username).getUsername());

    }
    @Test
    void loadUserByUsernameFail() {
        UsernameNotFoundException exc = assertThrows(UsernameNotFoundException.class, ()->userService.loadUserByUsername("bblalbla"));
        assertEquals("User not found", exc.getMessage());
    }

    @Test
    void findUserByUsername() {
        String username = "John";
        Mockito.doReturn(users.get(0)).when(userRepository).findByUsername(username);
        assertEquals("123", userService.findUserByUsername(username).getPassword());
    }

    @Test
    void findUserById() {
        long id = 2;
        Mockito.doReturn(users.stream().filter(o->o.getId()==id).findFirst()).when(userRepository).findById(id);

        assertEquals("Alex",userService.findUserById(id).getUsername());
    }

    @Test
    void allUsers() {

        Mockito.doReturn(users).when(userRepository).findAll();

        assertEquals(4,userService.allUsers().size());
    }

    @Test
    void saveUserFail() {
        User user = new User("aa","ssss");
        Mockito.doReturn(new User()).when(userRepository).findByUsername(user.getUsername());

        assertFalse(userService.saveUser(user));

    }

    @Test
    void saveUserSuccess() {

        Mockito.doReturn(null).when(userRepository).save(new User());
        Mockito.doReturn("123").when(bCryptPasswordEncoder).encode("123");

        assertTrue(userService.saveUser(new User()));
    }

    @Test
    void deleteUserFail() {
        long id = 6L;

        Mockito.doReturn(Optional.empty()).when(userRepository).findById(id);

        assertFalse(userService.deleteUser(id));


    }
    @Test
    void deleteUserSuccess() {

        long id = 2L;

        Mockito.doReturn(Optional.of("smth")).when(userRepository).findById(id);

        assertTrue(userService.deleteUser(id));
    }

}