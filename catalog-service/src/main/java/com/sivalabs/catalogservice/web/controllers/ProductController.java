package com.sivalabs.catalogservice.web.controllers;

import com.sivalabs.catalogservice.entities.Product;
import com.sivalabs.catalogservice.exceptions.ProductNotFoundException;
import com.sivalabs.catalogservice.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/products", consumes = {"application/json"}, produces = {"application/json"})
@Slf4j
public class ProductController {
   private final ProductService productService;

   @Autowired
   public ProductController(ProductService productService) {
      this.productService = productService;
   }

   @GetMapping("")
   public List<Product> allProducts() {
      List<Product> response = productService.findAllProducts();
      log.info("Products: {}", response);

      return response;
   }

   @GetMapping("/{code}")
   public Product productByCode(@PathVariable String code) {
      return productService.findProductByCode(code)
         .orElseThrow(() -> new ProductNotFoundException("Product with code [" + code + "] doesn't exist"));
   }
}