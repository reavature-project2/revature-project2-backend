package com.revature.controllers;

import com.revature.dto.Credentials;
import com.revature.dto.UserDTO;
import com.revature.models.Order;
import com.revature.models.User;
import com.revature.services.OrderService;
import com.revature.services.UserService;
import com.revature.util.JwtTokenManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

// Security risk
//@CrossOrigin(origins = "http://localhost:5000", allowedHeaders = "*")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/profile")
public class ProfileController {
    private UserService userService;
    private OrderService orderService;
    private JwtTokenManager tokenManager;
    private ModelMapper modelMapper = new ModelMapper();

//    Constructors
    @Autowired
    public ProfileController(UserService userService, OrderService orderService, JwtTokenManager tokenManager) {
        this.userService = userService;
        this.orderService = orderService;
        this.tokenManager = tokenManager;
    }

//    Methods
    @GetMapping
    public ResponseEntity<List<Order>> getProfileOrders(HttpServletRequest request,
                                                                HttpServletResponse response) {
        int userID = tokenManager.parseUserIdFromToken(request.getHeader("rolodex-token"));
        List<Order> userOrders = orderService.getAllOrdersByUserId(userID);

        if(userOrders.size() > 0) {
            response.addHeader("rolodex-token", request.getHeader("rolodex-token"));
            response.addHeader("Access-Control-Expose-Headers", "rolodex-token");
            response.setStatus(200);
            return ResponseEntity.ok(userOrders);
        }
        response.addHeader("error_message", "No orders.");
        response.setStatus(204);
        return null;
    }

    @GetMapping(path = "/info")
    public ResponseEntity<User> getProfileInfo(HttpServletRequest request,
                                                  HttpServletResponse response) {
        int userID = tokenManager.parseUserIdFromToken(request.getHeader("rolodex-token"));
        User userInfo = userService.getProfileById(userID);

        if(userInfo != null) {
            response.addHeader("rolodex-token", request.getHeader("rolodex-token"));
            response.addHeader("Access-Control-Expose-Headers", "rolodex-token");
            response.setStatus(200);
            return ResponseEntity.ok(userInfo);
        }
        response.addHeader("error_message", "User is not in Data Base.");
        response.setStatus(404);
        return null;
    }

//    @PostMapping(path = "/info")
    @PatchMapping(path = "/info")
    public ResponseEntity<User> addUser(@Valid @RequestBody Credentials userCredentials, HttpServletResponse response,
                                        HttpServletRequest request) {
        System.out.println("\n\n");
        System.out.println(request.getHeader("rolodex-token"));
        System.out.println("\n\n");
        int userID = tokenManager.parseUserIdFromToken(request.getHeader("rolodex-token"));
        User updatedProfile = userService.updateUser(userCredentials.getPassword(), userID,
                userCredentials.getUsername());

        if (updatedProfile != null) {
            response.addHeader("rolodex-token", request.getHeader("rolodex-token"));
            response.addHeader("Access-Control-Expose-Headers", "rolodex-token");
            response.setStatus(200);
            return ResponseEntity.ok(updatedProfile);
        }
        response.addHeader("error_message", "Internal Server Error.");
        response.setStatus(500);
        return null;
    }
}
