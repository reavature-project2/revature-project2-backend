package com.revature.controllers;

import com.revature.dto.OrderDTO;
import com.revature.models.Order;
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

// Security risk
//@CrossOrigin(origins = "http://localhost:5000", allowedHeaders = "*")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/rental")
public class RentalController {
    private UserService userService;
    private OrderService orderService;
    private JwtTokenManager tokenManager;
    private ModelMapper modelMapper = new ModelMapper();

//    Constructors
    @Autowired
    public RentalController(UserService userService, OrderService orderService, JwtTokenManager tokenManager) {
        this.userService = userService;
        this.orderService = orderService;
        this.tokenManager = tokenManager;
    }

//    Methods
    @PostMapping
    public ResponseEntity<Order> addOrder(@Valid @RequestBody OrderDTO orderDto, HttpServletResponse response,
                                          HttpServletRequest request) {
        Order order = modelMapper.map(orderDto, Order.class);
        int userID = tokenManager.parseUserIdFromToken(request.getHeader("rolodex-token"));
        Order addedOrder = orderService.addOrder(order, userID);

        if (addedOrder != null) {
            response.addHeader("rolodex-token", request.getHeader("rolodex-token"));
            response.addHeader("Access-Control-Expose-Headers", "rolodex-token");
            response.setStatus(200);
            return ResponseEntity.ok(addedOrder);
        }
        response.addHeader("error_message", "Order was not processed. Try it again later.");
        response.setStatus(500);
        return null;
    }
}
