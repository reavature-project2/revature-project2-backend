package com.revature.controllers;

import com.revature.dto.Credentials;
import com.revature.exceptions.AuthenticationException;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.JwtTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

// Security risk
//@CrossOrigin(origins = "http://localhost:5000", allowedHeaders = "*")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/login")
public class LoginController {
    private UserService userService;
    private JwtTokenManager tokenManager;

//    Constructors
    @Autowired
    public LoginController(UserService userService, JwtTokenManager tokenManager) {
        this.userService = userService;
        this.tokenManager = tokenManager;
    }


//    Methods
    @PostMapping
    public ResponseEntity<Object> login(@Valid @RequestBody Credentials userCredentials, HttpServletResponse response) {
        User user = userService.getByCredentials(userCredentials);

        if (user != null) {
            String token = tokenManager.issueToken(user);
//            userService.addToken(userCredentials.getUsername(), token);
            response.addHeader("rolodex-token", token);
            response.addHeader("Access-Control-Expose-Headers", "rolodex-token");
            response.setStatus(200);
            return ResponseEntity.ok(user);
        }
        response.addHeader("error_message", "Username or password does not match.");
        response.setStatus(401);
        return null;
    }
}
