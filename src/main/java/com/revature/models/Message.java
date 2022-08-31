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
import javax.validation.constraints.Email;

@Entity
@Getter
@Setter
@Table(name = "messages")
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "message_id")

public class Message {

//    Column names
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int message_id;

    @Column(nullable = false)
    @Length(min = 1)
    private String f_name;

    @Column(nullable = false)
    @Length(min = 1)
    private String l_name;

    @Email
    @Column(nullable = false)
    @Length(min = 3)
    private String email;

    @Column(nullable = false)
    @Length(min = 1)
    private String request_type;

    @Column(nullable = false)
    @Length(min = 1)
    private String user_message;

//    Foreign Key column
//    Many-to-One relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user_id;

//    Constructors
    @Autowired
    public Message(String f_name, String l_name, String email, String request_type, String user_message, User user_id) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.email = email;
        this.request_type = request_type;
        this.user_message = user_message;
        this.user_id = user_id;
    }

    @Autowired
    public Message(String f_name, String l_name, String email, String request_type, String user_message) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.email = email;
        this.request_type = request_type;
        this.user_message = user_message;
    }
}
