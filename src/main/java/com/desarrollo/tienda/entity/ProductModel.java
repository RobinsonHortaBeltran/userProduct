package com.desarrollo.tienda.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class ProductModel {

    //Id is the primary key of the table
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    //Column name is the name of the column in the table
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    //Column name is the name of the column in the table
    @Column(name = "description", nullable = false)
    private String description;

    //Column name is the name of the column in the table
    @Column(name = "price", nullable = false)
    private Double price;

    //Column name is the name of the column in the table
    @Column(name = "stock", nullable = false)
    private Integer stock;

    //Relation many to one with the user table
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private UserModel user;
}
