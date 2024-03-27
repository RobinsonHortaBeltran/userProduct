package com.desarrollo.tienda.controller;

import com.desarrollo.tienda.entity.ProductModel;
import com.desarrollo.tienda.service.ProductService;
import com.desarrollo.tienda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;
    UserService userService;
    //Get all products
    @GetMapping()
    public ArrayList<ProductModel> getProducts(){
        return this.productService.getAllProducts();
    }

    //Get product by id
    @GetMapping(path = "/{id}")
    public Optional<ProductModel> getProductById(@PathVariable("id") Long id){
       return Optional.ofNullable(this.productService.getProductById(id));
    }

    //Save product
    @PostMapping("/")
    public ProductModel saveProduct(@RequestBody ProductModel product){
        return this.productService.saveProduct(product);
    }

    //Update product
    @PutMapping(path = "/{id}")
    public Optional<ProductModel> updateProduct(@RequestBody ProductModel product, @PathVariable("id") Long id){
        return Optional.ofNullable(this.productService.updateProduct(product, id));
    }

    //Delete product
    @DeleteMapping(path = "/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        boolean ok = this.productService.deleteProduct(id);
        if (ok){
            return "Product deleted";
        } else {
            return "Product not deleted";
        }
    }


}
