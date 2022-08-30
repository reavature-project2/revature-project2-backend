package com.revature.controllers;

import com.revature.dto.UserDTO;
import com.revature.exceptions.UserCredentialsAlreadyInUse;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.JwtTokenManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

// @CrossOrigin(origins = "http://localhost:5000", allowedHeaders = "*")
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/register")
public class RegisterController {
    private UserService userService;
    private JwtTokenManager tokenManager;
    private ModelMapper modelMapper = new ModelMapper();

//    Constructors
    @Autowired
    public RegisterController(UserService userService, JwtTokenManager tokenManager) {
        this.userService = userService;
        this.tokenManager = tokenManager;
    }

//    Methods
    @PostMapping
    public ResponseEntity<User> addUser(@Valid @RequestBody UserDTO userDto, HttpServletResponse response) {
        System.out.println("\n\n");
        System.out.println(userDto);
        System.out.println("\n\n");
        User user = modelMapper.map(userDto, User.class);
        User existingEmail = userService.getByEmail(user.getEmail());
        User existingDrLicense = userService.getByDrLicense(user.getDr_lic_number());

        if(existingEmail != null) {
            response.addHeader("error_message", "This email is already in use.");
            response.setStatus(409);
            return null;
        }
        if(existingDrLicense != null) {
            response.addHeader("error_message", "This driver license number is already in use.");
            response.setStatus(410);
            return null;
        }

        User addedUser = userService.addUser(user);
        if (addedUser != null) {
            String token = tokenManager.issueToken(addedUser);
            response.addHeader("rolodex-token", token);
            response.addHeader("Access-Control-Expose-Headers", "rolodex-token");
            response.setStatus(200);
            return ResponseEntity.ok(addedUser);
        }
        response.addHeader("error_message", "Internal Server Error.");
        response.setStatus(500);
        return null;
    }
}
