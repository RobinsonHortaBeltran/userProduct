package com.desarrollo.tienda.service;

import com.desarrollo.tienda.dto.ProductDto;
import com.desarrollo.tienda.dto.UserDto;
import com.desarrollo.tienda.entity.UserModel;
import com.desarrollo.tienda.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    IUserRepository userRepository;

    //Return all users
    /*public ArrayList<UserModel> getAllUsers() {
        return (ArrayList<UserModel>) this.userRepository.findAll();
    }*/

    public List<UserDto> getAllUsers() {
        return this.userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    //Return user by id
    public Optional<UserDto> getUserById(Long id) {
        Optional<UserModel> user = this.userRepository.findById(id);
        return user.map(this::convertToDto);
    }

    //Save user, return user saved
    public UserModel saveUser(UserDto userDto) {
        UserModel user = this.convertToEntity(userDto);
        return this.userRepository.save(user);
    }

    //Update user, return user updated
    public Optional<UserDto> updateUser(UserDto request, Long id) {
        Optional<UserModel> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            UserModel existingUser = user.get();
            existingUser.setFirstName(request.getFirstName());
            existingUser.setLastName(request.getLastName());
            existingUser.setEmail(request.getEmail());

            UserModel updatedUser = this.userRepository.save(existingUser);
            return Optional.of(this.convertToDto(updatedUser));
            //return Optional.of(this.userRepository.save(user.get()));
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

    public UserModel findByOneEmail(String email) {
        return this.userRepository.findByOneEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


    public UserDto convertToDto(UserModel user) {

        List<ProductDto> productDto = user.getProducts().stream()
                .map(product -> new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getUser().getId()))
                .toList();

        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), productDto);
    }


    public UserModel convertToEntity(UserDto userDto) {
        UserModel user = new UserModel();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }

}
