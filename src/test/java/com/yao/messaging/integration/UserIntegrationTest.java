package com.yao.messaging.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.yao.messaging.dao.UserDAO;
import com.yao.messaging.dao.UserValidationCodeDAO;
import com.yao.messaging.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc; // http client

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserValidationCodeDAO userValidationCodeDAO;

    @BeforeEach
    public void cleanUp() {
        this.userDAO.deleteAll();
        this.userValidationCodeDAO.deleteAll();
    }

    @Test
    void testRegister_passwordsNotMatched_returnsBadRequest() throws Exception {
        var requestBody = "{\n" +
                "    \"username\": \"George33322\",\n" +
                "    \"password\": \"xxxx\",\n" +
                "    \"repeatPassword\": \"qqqqqqqqqqqqqqq111111\",\n" +
                "    \"email\": \"ffffffff332223@gmail.com\",\n" +
                "    \"address\": \"Canada\",\n" +
                "    \"nickname\": \"AAAAA\",\n" +
                "    \"gender\": \"MALE\"\n" +
                "}";
        this.mockMvc.perform(post("/users/register")
                                     .content(requestBody)
                                     .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(1001))
                .andExpect(jsonPath("$.message").value("Passwords not matched"));

    }

    @Test
    void testRegister_happyCase() throws Exception {
        var requestBody = "{\n" +
                "    \"username\": \"Alice101\",\n" +
                "    \"password\": \"qqqqqqqqqqqqqqq111111\",\n" +
                "    \"repeatPassword\": \"qqqqqqqqqqqqqqq111111\",\n" +
                "    \"email\": \"alice@gmail.com\",\n" +
                "    \"address\": \"Canada\",\n" +
                "    \"nickname\": \"AAAAA\",\n" +
                "    \"gender\": \"MALE\"\n" +
                "}";
        this.mockMvc.perform(post("/users/register")
                                     .content(requestBody)
                                     .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(1000))
                .andExpect(jsonPath("$.message").value("Successful"));

        List<User> insertedUsers = this.userDAO.selectByUsername("Alice101");
        assertEquals(1, insertedUsers.size());
        User insertedUser = insertedUsers.get(0);
        assertEquals("alice@gmail.com", insertedUser.getEmail());

        //UserValidationCode userValidationCode = this.userValidationCodeDAO.findByUserId(insertedUser.getId());
    }

    @Test
    void testActivate_happyCase() {
    }
}
