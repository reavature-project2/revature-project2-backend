package com.revature.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Getter
@Setter
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "order_id")
public class Order {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;

    @Column(nullable = false)
    @Min(value = 0)
    private long rent_date;

    @Column(nullable = false)
    @Min(value = 0)
    private long return_date;

    @Column(nullable = false)
    @Min(value = 0)
    private double rent_price;

    @Column(nullable = false)
    @Length(min = 1)
    private String car_make;

    @Column(nullable = false)
    @Length(min = 1)
    private String car_model;

    @Column(nullable = false)
    @Length(min = 1)
    private String car_class;

    @Column(nullable = false)
    @Length(min = 1)
    private String car_trans;

    @Column(nullable = false)
    @Min(value = 0)
    private int car_year;

//    Many-to-One relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user_id;

//    Constructors
    @Autowired
    public Order(long rent_date, long return_date, double rent_price, String car_make, String car_model,
                 String car_class, String car_trans, int car_year, User user_id) {
        this.rent_date = rent_date;
        this.return_date = return_date;
        this.rent_price = rent_price;
        this.car_make = car_make;
        this.car_model = car_model;
        this.car_class = car_class;
        this.car_trans = car_trans;
        this.car_year = car_year;
        this.user_id = user_id;
    }

    @Autowired
    public Order(long rent_date, long return_date, double rent_price, String car_make, String car_model,
                 String car_class, String car_trans, int car_year) {
        this.rent_date = rent_date;
        this.return_date = return_date;
        this.rent_price = rent_price;
        this.car_make = car_make;
        this.car_model = car_model;
        this.car_class = car_class;
        this.car_trans = car_trans;
        this.car_year = car_year;
    }


}
