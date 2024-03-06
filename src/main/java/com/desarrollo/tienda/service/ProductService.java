package com.desarrollo.tienda.service;

import com.desarrollo.tienda.entity.ProductModel;
import com.desarrollo.tienda.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {

    @Autowired
    IProductRepository productRepository;

    //Get all products, return list of products
    public ArrayList<ProductModel> getAllProducts() {
        return (ArrayList<ProductModel>) this.productRepository.findAll();
    }

    //Get product by id, return product
    public ProductModel getProductById(Long id) {
        return this.productRepository.findById(id).orElse(null);
    }

    //Save product, return product saved
    public ProductModel saveProduct(ProductModel product) {
        return this.productRepository.save(product);
    }

    //Update product, return product updated
    public ProductModel updateProduct(ProductModel request, Long id) {
        ProductModel product = this.productRepository.findById(id).orElse(null);

        if(product != null) {
            product.setName(request.getName());
            product.setDescription(request.getDescription());
            product.setPrice(request.getPrice());
            product.setStock(request.getStock());

            return this.productRepository.save(product);
        } else {
            return null;
        }
    }

    //Delete product, return boolean
    public boolean deleteProduct(Long id) {
        try {
            this.productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
