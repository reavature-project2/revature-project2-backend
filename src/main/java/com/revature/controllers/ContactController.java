package com.revature.controllers;

import com.revature.dto.MessageDTO;
import com.revature.models.Message;
import com.revature.models.Order;
import com.revature.services.MessageService;
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
@RequestMapping("/contact")
public class ContactController {
    private ModelMapper modelMapper = new ModelMapper();
    private JwtTokenManager tokenManager;
    private UserService userService;
    private OrderService orderService;
    private MessageService messageService;

    //    Constructor
    @Autowired
    public ContactController(JwtTokenManager tokenManager, UserService userService, OrderService orderService,
                             MessageService messageService) {
        this.tokenManager = tokenManager;
        this.userService = userService;
        this.orderService = orderService;
        this.messageService = messageService;
    }

    //    Methods
    @PostMapping
    public ResponseEntity<Message> addMessage(@Valid @RequestBody MessageDTO messageDto, HttpServletResponse response,
                                            HttpServletRequest request) {
        Message newMessage = modelMapper.map(messageDto, Message.class);
        int userID = tokenManager.parseUserIdFromToken(request.getHeader("rolodex-token"));
        Message addedMessage = messageService.addMessage(newMessage, userID);

        if (addedMessage != null) {
            response.addHeader("rolodex-token", request.getHeader("rolodex-token"));
            response.addHeader("Access-Control-Expose-Headers", "rolodex-token");
            response.setStatus(200);
            return ResponseEntity.ok(addedMessage);
        }
        response.addHeader("error_message", "Message was not saved. Try it again later.");
        response.setStatus(500);
        return null;
    }
}
