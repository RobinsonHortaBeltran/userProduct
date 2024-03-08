package com.desarrollo.tienda.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private Long id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("email")
    private String email;

    private List<ProductDto> products;

    public UserDto(Long id, String firstName, String lastName, String email) {
    }

    public UserDto(Long id, String firstName, String lastName, String email, List<ProductDto> products) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.products = products;

    }

}
