package com.revplay.util.service;

import com.revplay.util.dao.UserDAO;
import com.revplay.util.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser_Success() {

        when(userDAO.registerUser(any(User.class)))
                .thenReturn(true);

        boolean result = userService
                .registerUser("Ram", "ram@mail.com", "1234");

        assertTrue(result);
    }

    @Test
    void testLoginUser_Failure() {

        when(userDAO.loginUser(anyString(), anyString()))
                .thenReturn(null);

        User user = userService
                .loginUser("x@mail.com", "x");

        assertNull(user);
    }
}
