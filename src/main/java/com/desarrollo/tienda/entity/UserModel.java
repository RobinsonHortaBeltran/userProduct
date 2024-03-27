package com.desarrollo.tienda.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
@Data
public class UserModel {
    //Id is the primary key of the table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Column name is the name of the column in the table
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    //Column name is the name of the column in the table
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    //Column name is the name of the column in the table
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    //Column password is the password of the column in the table
    @Column(name = "password", nullable = false, length = 50)
    private String password;

    //Relation one to many with the product table
    @OneToMany(mappedBy = "user")
    private List<ProductModel> products;

}
