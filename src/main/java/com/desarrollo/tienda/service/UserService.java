package com.desarrollo.tienda.service;

import com.desarrollo.tienda.entity.UserModel;
import com.desarrollo.tienda.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    IUserRepository userRepository;

    //Return all users
    public ArrayList<UserModel> getAllUsers() {
        return (ArrayList<UserModel>) this.userRepository.findAll();
    }

    //Return user by id
    public Optional<UserModel> getUserById(Long id) {
        return this.userRepository.findById(id);
    }

    //Save user, return user saved
    public UserModel saveUser(UserModel user) {
        return this.userRepository.save(user);
    }

    //Update user, return user updated
    public Optional<UserModel> updateUser(UserModel request, Long id) {
        Optional<UserModel> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setFirstName(request.getFirstName());
            user.get().setLastName(request.getLastName());
            user.get().setEmail(request.getEmail());

            return Optional.of(this.userRepository.save(user.get()));
        } else {
            return Optional.empty();
        }
    }

    //Delete user, return true if user was deleted
    public boolean deleteUser(Long id) {
        try {
            this.userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
