package com.revature.service;

import com.revature.dto.Credentials;
import com.revature.models.User;
import com.revature.repositores.UserRepo;
import com.revature.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepo mockUserRepo;

    @InjectMocks
    UserService userService;

    User dummyUser;
    Credentials dummyUserCredentials;

//    Init dummyUser
    @BeforeEach
    void setup() throws Exception {
        this.dummyUser = new User("test", "test", "test1@test.com", 20,
                "test123456", "12345", null);
        this.dummyUserCredentials = new Credentials("test", "12345");
    }

    @AfterEach
    void teardown() {
        this.dummyUser = null;
        this.dummyUserCredentials = null;
    }

//    UnitTests
    @Test
    void testGetByEmail_Success() {
        String userEmail = "test1@test.com";

        given(this.mockUserRepo.getByEmail(userEmail)).willReturn(Optional.of(this.dummyUser));

        User expected = this.dummyUser;
        User actual = this.userService.getByEmail(userEmail);
        assertEquals(expected, actual);
    }

    @Test
    void testGetByEmail_Failure_WrongUserEmail() {
        String userEmail = "test1@test.com";

        given(this.mockUserRepo.getByEmail(userEmail)).willReturn(Optional.empty());

        User actual = this.userService.getByEmail(userEmail);
        assertNull(actual);
    }

    @Test
    void testGetByDrLicense_Success() {
        String userDrLicense = "12345AB";

        given(this.mockUserRepo.getByDrLicense(userDrLicense)).willReturn(Optional.of(this.dummyUser));

        User expected = this.dummyUser;
        User actual = this.userService.getByDrLicense(userDrLicense);
        assertEquals(expected, actual);
    }

    @Test
    void testGetByDrLicense_Failure_WrongUserDrLicense() {
        String userDrLicense = "12345AB";

        given(this.mockUserRepo.getByDrLicense(userDrLicense)).willReturn(Optional.empty());

        User actual = this.userService.getByDrLicense(userDrLicense);
        assertNull(actual);
    }

    @Test
    void testGetByCredentials_Success() {
        String userEmail = "test";
        String userPass = "12345";

        given(this.mockUserRepo.getByUsernameAndPassword(userEmail, userPass)).willReturn(Optional.of(this.dummyUser));

        User expected = this.dummyUser;
        User actual = this.userService.getByCredentials(this.dummyUserCredentials);
        assertEquals(expected, actual);
    }

    @Test
    void testGetByCredentials_Failure_WrongUserCredentials() {
        String userEmail = "test";
        String userPass = "12345";

        given(this.mockUserRepo.getByUsernameAndPassword(userEmail, userPass)).willReturn(Optional.empty());

        User actual = this.userService.getByCredentials(this.dummyUserCredentials);
        assertNull(actual);
    }

    @Test
    void testAddUser_Success() {
        given(this.mockUserRepo.addUser(20, "test123456", "test1@test.com", "test",
                "test", "12345")).willReturn(Optional.of(this.dummyUser));

        User expected = this.dummyUser;
        User actual = this.userService.addUser(this.dummyUser);
        assertEquals(expected, actual);
    }

    @Test
    void testAddUser_Failure_WrongUserCredentials() {
        String userEmail = "test";

        given(this.mockUserRepo.addUser(20, "test123456", "test1@test.com", "test",
                "test", "12345")).willReturn(Optional.empty());

        User actual = this.userService.addUser(this.dummyUser);
        assertNull(actual);
    }

}
