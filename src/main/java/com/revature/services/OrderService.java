package com.revature.services;

import com.revature.models.Order;
import com.revature.models.User;
import com.revature.repositores.OrderRepo;
import com.revature.repositores.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private UserRepo userRepo;
    private OrderRepo orderRepo;

//    Constructors
    @Autowired
    public OrderService(UserRepo userRepo, OrderRepo orderRepo) {
        this.userRepo = userRepo;
        this.orderRepo = orderRepo;
    }

//    Methods
    @Transactional
    public Order addOrder(Order newOrder, int userID) {
        Optional<Order> addedOrder = orderRepo.addOrder(newOrder.getCar_class(), newOrder.getCar_make(),
                newOrder.getCar_model(), newOrder.getCar_trans(), newOrder.getCar_year(), newOrder.getRent_date(),
                newOrder.getRent_price(), newOrder.getReturn_date(), userID);
        return addedOrder.orElse(null);
    }

    @Transactional
    public List<Order> getAllOrdersByUserId(int userID) {
        return orderRepo.getAllOrdersByUserId(userID).orElse(null);
    }
}
