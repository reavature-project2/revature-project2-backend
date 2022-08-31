package com.revature.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MessageDTO {

    @NotBlank
    private String f_name;

    @NotBlank
    private String l_name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String request_type;

    @NotBlank
    private String user_message;

}
