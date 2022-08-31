package com.revature.services;

import com.revature.models.Message;
import com.revature.models.Order;
import com.revature.repositores.MessageRepo;
import com.revature.repositores.OrderRepo;
import com.revature.repositores.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MessageService {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private UserRepo userRepo;
    private OrderRepo orderRepo;
    private MessageRepo messageRepo;

//    Constructor
    @Autowired
    public MessageService(UserRepo userRepo, OrderRepo orderRepo, MessageRepo messageRepo) {
        this.userRepo = userRepo;
        this.orderRepo = orderRepo;
        this.messageRepo = messageRepo;
    }

//    Methods
    @Transactional
    public Message addMessage(Message newMessage, int userID) {
        Optional<Message> addedMessage = messageRepo.addMessage(newMessage.getEmail(), newMessage.getF_name(),
                newMessage.getL_name(), newMessage.getRequest_type(), newMessage.getUser_message(), userID);
        return addedMessage.orElse(null);
}
}
