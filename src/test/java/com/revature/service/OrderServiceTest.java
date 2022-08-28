package com.revature.service;

import com.revature.models.Order;
import com.revature.repositores.OrderRepo;
import com.revature.services.OrderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    OrderRepo mockOrderRepo;

    @InjectMocks
    OrderService orderService;

    Order dummyOrder;
    java.util.List<Order> dummyListOrders;

//    Init dummyOrder
    @BeforeEach
    void setup() throws Exception {
        this.dummyOrder = new Order(1000000, 1000001, 999.0, "test",
                "test",  "test", "test", 2000, null);
        this.dummyListOrders = new ArrayList<>();
        this.dummyListOrders.add(new Order());
    }

    @AfterEach
    void teardown() {
        this.dummyOrder = null;
        this.dummyListOrders = null;
    }

//    UnitTests
    @Test
    void testAddOrder_Success() {
        int userId = 0;

        given(this.mockOrderRepo.addOrder(this.dummyOrder.getCar_class(), this.dummyOrder.getCar_make(),
                this.dummyOrder.getCar_model(), this.dummyOrder.getCar_trans(),
                this.dummyOrder.getCar_year(), this.dummyOrder.getRent_date(), this.dummyOrder.getRent_price(),
                this.dummyOrder.getReturn_date(), userId)).willReturn(Optional.of(this.dummyOrder));

        Order expected = this.dummyOrder;
        Order actual = this.orderService.addOrder(this.dummyOrder, 0);
        assertEquals(expected, actual);
    }

    @Test
    void testAddOrder_Failure_UserInfoCannotBeProcessed() {
        int userId = 0;

        given(this.mockOrderRepo.addOrder(this.dummyOrder.getCar_class(), this.dummyOrder.getCar_make(),
                this.dummyOrder.getCar_model(), this.dummyOrder.getCar_trans(),
                this.dummyOrder.getCar_year(), this.dummyOrder.getRent_date(), this.dummyOrder.getRent_price(),
                this.dummyOrder.getReturn_date(), userId)).willReturn(Optional.empty());

        Order actual = this.orderService.addOrder(this.dummyOrder, 0);
        assertNull(actual);
    }

    @Test
    void testGetOrdersByUserId_Success() {
        int userId = 0;

        given(this.mockOrderRepo.getAllOrdersByUserId(userId)).willReturn(Optional.of(this.dummyListOrders));

        List<Order> expected = this.dummyListOrders;
        List<Order> actual = this.orderService.getAllOrdersByUserId(userId);
        assertEquals(expected, actual);
    }

    @Test
    void testGetOrdersByUserId_Failure_DataIsNotPresent() {
        int userId = 0;

        given(this.mockOrderRepo.getAllOrdersByUserId(userId)).willReturn(Optional.empty());

        List<Order> actual = this.orderService.getAllOrdersByUserId(userId);
        assertNull(actual);
    }
}
