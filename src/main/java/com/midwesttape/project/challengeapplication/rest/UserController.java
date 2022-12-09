package com.midwesttape.project.challengeapplication.rest;

import com.midwesttape.project.challengeapplication.model.User;
import com.midwesttape.project.challengeapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/v1/users/{userId}")
    public User user(@PathVariable final Long userId) {
        return userService.user(userId);
    }

    @PutMapping("/v1/users/{userId}/update")
    public String updateUser(@PathVariable final Long userId,
                             @RequestParam(required = false) final String firstName,
                             @RequestParam(required = false) final String lastName,
                             @RequestParam(required = false) final String userName,
                             @RequestParam(required = false) final String password) {
        User user = userService.user(userId);
        if(firstName != null ) user.setFirstName(firstName);
        if(lastName != null ) user.setLastName(lastName);
        if(userName != null ) user.setUsername(userName);
        if(password != null ) user.setPassword(password);
        userService.updateUser(user);

        return "Success";
    }
}
