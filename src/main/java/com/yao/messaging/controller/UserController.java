package com.yao.messaging.controller;


import com.yao.messaging.enums.Status;
import com.yao.messaging.request.ActivateUserRequest;
import com.yao.messaging.request.RegisterUserRequest;
import com.yao.messaging.response.Response;
import com.yao.messaging.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService; // dependency

    @PostMapping("/register") // /users/register
    public Response register(@RequestBody RegisterUserRequest registerUserRequest) throws Exception {

        this.userService.register(registerUserRequest.getUsername(),
                                  registerUserRequest.getEmail(),
                                  registerUserRequest.getPassword(),
                                  registerUserRequest.getRepeatPassword(),
                                  registerUserRequest.getAddress(),
                                  registerUserRequest.getNickname(),
                                  registerUserRequest.getGender());
        return new Response(Status.OK);
    }

    @PostMapping("/activate")
    public Response activate(@RequestBody ActivateUserRequest activateUserRequest) throws Exception {
        // ...
        return new Response(Status.OK);

    }

    @PostMapping("/login")
    public void login() {

    }
}
