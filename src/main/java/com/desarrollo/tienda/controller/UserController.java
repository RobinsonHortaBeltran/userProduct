package com.desarrollo.tienda.controller;

import com.desarrollo.tienda.dto.UserDto;
import com.desarrollo.tienda.entity.UserModel;
import com.desarrollo.tienda.repository.IUserRepository;
import com.desarrollo.tienda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    //Get all users
    @GetMapping
    public ArrayList<UserDto> getUsers(){
        return (ArrayList<UserDto>) this.userService.getAllUsers();
    }

    //Get user by id
    @GetMapping(path = "/{id}")
    public Optional<UserModel> getUserById(@PathVariable("id") Long id){
        return this.userService.getUserById(id);
    }

    //Save user
    @PostMapping("/")
    public UserModel saveUser(@RequestBody UserModel user){
        return this.userService.saveUser(user);
    }

    //Update user
    @PutMapping(path = "/{id}")
    public Optional<UserModel> updateUser(@RequestBody UserModel user, @PathVariable("id") Long id){
        return this.userService.updateUser(user, id);
    }

    //Delete user
    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        boolean ok = this.userService.deleteUser(id);
        if (ok){
            return "User deleted";
        } else {
            return "User not deleted";
        }
    }

}
