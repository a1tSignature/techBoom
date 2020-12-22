package com.a1tSign.techBoom.controller;

import com.a1tSign.techBoom.data.dto.security.AuthResDTO;
import com.a1tSign.techBoom.data.dto.security.LoginUserDTO;
import com.a1tSign.techBoom.data.dto.security.RegisterUserDTO;
import com.a1tSign.techBoom.security.JwtProvider;
import com.a1tSign.techBoom.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping ("api/v1/security")
public class SecurityController {
    private final UserService userService;
    private final JwtProvider jwtProvider;

    public SecurityController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping ("/register")
    @ResponseStatus (CREATED)
    public void register(@RequestBody RegisterUserDTO dto) {
        userService.createNewUser(dto);
    }

    @PostMapping ("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthResDTO login(@RequestBody LoginUserDTO dto) {
        var user = userService.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (user == null) {
            throw new RuntimeException("Invalid username or password.");
        }
        var token = jwtProvider.generateToken(user.getUsername(), user.getRoles());

        return new AuthResDTO(token);
    }


}
