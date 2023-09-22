package com.yao.messaging.dao;

import java.util.List;

import com.yao.messaging.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper //object <-> row in database
public interface UserDAO { // data access object

    // user.getUsername();
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    @Insert("INSERT INTO user (username, password, email, gender, address, nickname, is_valid, register_time) VALUES " +
            "(" +
            "#{username}, #{password}, #{email}, #{gender}, #{address}, #{nickname}, #{isValid}, #{registerTime})")
    void insert(User user);

    @Select("SELECT * FROM user WHERE username=#{username}")
    List<User> selectByUsername(String username);

    @Select("SELECT * FROM user WHERE username=#{username}")
    List<User> selectByEmail(String email);

    @Delete("DELETE FROM user")
    void deleteAll();

}

