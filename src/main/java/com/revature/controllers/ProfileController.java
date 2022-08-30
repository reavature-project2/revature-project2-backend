package com.revature.controllers;

import com.revature.dto.Credentials;
import com.revature.models.Order;
import com.revature.models.User;
import com.revature.services.OrderService;
import com.revature.services.UserService;
import com.revature.util.JwtTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/profile")
public class ProfileController {
    private UserService userService;
    private OrderService orderService;
    private JwtTokenManager tokenManager;

//    Constructors
    @Autowired
    public ProfileController(UserService userService, OrderService orderService, JwtTokenManager tokenManager) {
        this.userService = userService;
        this.orderService = orderService;
        this.tokenManager = tokenManager;
    }

//    Methods
    @GetMapping
    public ResponseEntity<List<Order>> getProfile(HttpServletRequest request,
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
}
