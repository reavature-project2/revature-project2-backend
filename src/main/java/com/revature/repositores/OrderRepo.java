package com.revature.repositores;

import com.revature.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
    @Query(value = "insert into orders (car_class, car_make, car_model, car_trans, car_year, rent_date, rent_price, return_date, user_id) values (:car_class, :car_make, :car_model, :car_trans, :car_year, :rent_date, :rent_price, :return_date, :user_id) Returning *",
            nativeQuery = true)
    Optional<Order> addOrder(@Param(value = "car_class") String carClass, @Param(value = "car_make") String carMake,
                             @Param(value = "car_model") String carModel, @Param(value = "car_trans") String carTrans,
                             @Param(value = "car_year") long carYear, @Param(value = "rent_date") long rentDate,
                             @Param(value = "rent_price") double rentPrice,
                             @Param(value = "return_date") long returnDate, @Param(value = "user_id") int userID);

    @Query(value = "select * from orders where user_id = ?", nativeQuery = true)
    Optional<List<Order>> getAllOrdersByUserId(int userId);
}
