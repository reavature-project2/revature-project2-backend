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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "user_id")
public class User {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @Column(nullable = false)
    @Length(min = 1)
    private String f_name;

    @Column(nullable = false)
    @Length(min = 1)
    private String l_name;

    @Email
    @Column(unique = true, nullable = false)
    @Length(min = 3)
    private String email;

    @Min(value = 18)
    @Column
    private int age;

    @Column(nullable = false)
    @Length(min = 1)
    private String dr_lic_number;

    @Column(nullable = false)
    @Length(min = 5, max = 25)
    private String pass;

    @Column
    private String token_id;

//    One-to-Many relationship
    @OneToMany(mappedBy = "user_id", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Order> orders;

    @OneToMany(mappedBy = "user_id", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Message> messages;

//    Constructors
    @Autowired
    public User(String f_name, String l_name, String email, int age, String dr_lic_number, String pass,
                String token_id, List<Order> orders) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.email = email;
        this.age = age;
        this.dr_lic_number = dr_lic_number;
        this.pass = pass;
        this.token_id = token_id;
        this.orders = orders;
    }

    @Autowired
    public User(String f_name, String l_name, String email, int age, String dr_lic_number, String pass,
                String token_id) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.email = email;
        this.age = age;
        this.dr_lic_number = dr_lic_number;
        this.pass = pass;
        this.token_id = token_id;
    }

    @Autowired
    public User(String f_name, String l_name, String email, int age, String dr_lic_number, String pass) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.email = email;
        this.age = age;
        this.dr_lic_number = dr_lic_number;
        this.pass = pass;
    }


}
