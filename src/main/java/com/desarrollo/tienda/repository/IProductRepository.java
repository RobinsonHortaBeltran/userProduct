package com.desarrollo.tienda.repository;

import com.desarrollo.tienda.entity.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<ProductModel, Long> {
}
