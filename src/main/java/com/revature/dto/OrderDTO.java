package com.revature.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderDTO {

    @NotBlank
    String car_class;

    @NotBlank
    String car_make;

    @NotBlank
    String car_model;

    @NotBlank
    String car_trans;

    @Min(value = 1000)
    long car_year;

    @Min(value = 0)
    long rent_date;

    @Min(value = 0)
    double rent_price;

    @Min(value = 0)
    long return_date;
}
