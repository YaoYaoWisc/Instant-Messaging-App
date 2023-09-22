package com.yao.messaging.service;

import java.util.Date;
import java.util.Random;

import com.yao.messaging.dao.UserDAO;
import com.yao.messaging.dao.UserValidationCodeDAO;
import com.yao.messaging.enums.Gender;
import com.yao.messaging.enums.Status;
import com.yao.messaging.exception.MessagingServiceException;
import com.yao.messaging.model.User;
import com.yao.messaging.model.UserValidationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserValidationCodeDAO userValidationCodeDAO;

    public void register(String username,
                         String email,
                         String password,
                         String repeatPassword,
                         String address,
                         String nickname,
                         Gender gender) throws Exception {

        // argument validation
        if (!password.equals(repeatPassword)) {
            throw new MessagingServiceException(Status.PASSWORDS_NOT_MATCHED);
        }
        if (password.length() < 8) {
            throw new Exception();
        }
        if (username == null || username.isEmpty()) {
            throw new Exception();
        }
        if (email == null || email.isEmpty()) {
            throw new Exception();
        }

        // username and email is unique

//        if (this.userDAO.selectByUsername(username).size() > 0) {
//            throw new Exception();
//        }
//        if (this.userDAO.selectByEmail(email).size() > 0) {
//            throw new Exception();
//        }

        // insert user into db

        User user = User.builder()
                .username(username)
                .email(email)
                .password(password) // salted md5
                .address(address)
                .gender(gender)
                .registerTime(new Date())
                .nickname(nickname)
                .isValid(false)
                .build();

        this.userDAO.insert(user);

        UserValidationCode userValidationCode = UserValidationCode.builder()
                .userId(user.getId())
                .validationCode(String.format("%06d", new Random().nextInt() % 100000))
                .build();

        this.userValidationCodeDAO.insert(userValidationCode);

        // send email to user

    }

    public void activate(String identification, String validationCode) {
        //select user by identification
        //select code by user_id
        //compare validation codes
        // update user set is_valid = 1 where id = ...
        // delete validation code
    }
}
// Github