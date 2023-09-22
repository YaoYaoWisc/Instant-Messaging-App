package com.yao.messaging.dao;

import com.yao.messaging.model.UserValidationCode;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper //object <-> row in database
public interface UserValidationCodeDAO { // data access object

    // user.getUsername();
    @Insert("INSERT INTO user_validation_code (user_id, validation_code) VALUES " +
            "(#{userId}, #{validationCode})")
    void insert(UserValidationCode userValidationCode);

    @Select("")
    UserValidationCode findByUserId(int userId);

    @Delete("DELETE FROM user_validation_code")
    void deleteAll();

}

