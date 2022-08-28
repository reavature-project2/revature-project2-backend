package com.revature.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDTO {
    @NotBlank
    private String f_name;

    @NotBlank
    private String l_name;

    @Email
    @NotBlank
    private String email;

    @Min(value = 18)
    private int age;

    @NotBlank
    private String dr_lic_number;

    @NotBlank
    private String pass;
}
