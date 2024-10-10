package com.app.ecom.controller;

import com.app.ecom.dto.ProductRequest;
import com.app.ecom.entity.Category;
import com.app.ecom.entity.Product;
import com.app.ecom.repository.ICategoryRepository;
import com.app.ecom.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class ProductGraphQLController {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @QueryMapping
    public List<Product> productList(){
        return productRepository.findAll();
    }

    @QueryMapping
    public Product productById(@Argument String id){
        return productRepository.findById(id).orElseThrow(
                ()-> new RuntimeException(String.format("Product %s not found", id))
        );
    }

    @QueryMapping
    public List<Category> categoryList(){
        return categoryRepository.findAll();
    }

    @QueryMapping
    public Category categoryById(@Argument Long id){
        return categoryRepository.findById(id).orElseThrow(
                ()-> new RuntimeException(String.format("Category %s not found", id))
        );
    }

    @MutationMapping
    public Product saveProduct(@Argument ProductRequest productRequest){
        Category category = categoryRepository.findById(productRequest.categoryId()).orElse(null);
        Product productBBDD = new Product();
        productBBDD.setId(UUID.randomUUID().toString());
        productBBDD.setName(productRequest.name());
        productBBDD.setPrice(productRequest.price());
        productBBDD.setAmount(productRequest.amount());
        productBBDD.setCategory(category);
        return productRepository.save(productBBDD);
    }

    @MutationMapping
    public Product updateProduct(@Argument String id, @Argument ProductRequest productRequest){

        Category category = categoryRepository.findById(productRequest.categoryId()).orElse(null);
        Product productBBDD = new Product();
        productBBDD.setId(id);
        productBBDD.setName(productRequest.name());
        productBBDD.setPrice(productRequest.price());
        productBBDD.setAmount(productRequest.amount());
        productBBDD.setCategory(category);
        return productRepository.save(productBBDD);
    }

    @MutationMapping
    public void deleteProduct(@Argument String id){
        productRepository.deleteById(id);
    }
}
