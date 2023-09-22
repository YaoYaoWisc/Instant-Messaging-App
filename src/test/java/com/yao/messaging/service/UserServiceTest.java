package com.yao.messaging.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.yao.messaging.enums.Gender;
import org.junit.jupiter.api.Test;

class UserServiceTest {

    private UserService userService = new UserService();

    @Test // testTarget_scenario_expectation
    void testRegister_passwordsNotMatched_throwsException() {

        Exception exception = assertThrows(Exception.class,
                                           () -> this.userService.register("",
                                                                           "",
                                                                           "123",
                                                                           "456",
                                                                           "",
                                                                           "",
                                                                           Gender.FEMALE));
        assertEquals("Passwords not matched", exception.getMessage());
    }
}
