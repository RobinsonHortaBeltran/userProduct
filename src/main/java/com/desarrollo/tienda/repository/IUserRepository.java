package com.desarrollo.tienda.repository;

import com.desarrollo.tienda.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, Long> {

    default Optional<UserModel> findByOneEmail(String email) {
        return this.findAll().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }
}
