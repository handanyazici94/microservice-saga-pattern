package com.example.productcontext.controller;

import com.example.productcontext.dtos.ProductCreateRequestDTO;
import com.example.productcontext.dtos.ProductListResponseDTO;
import com.example.productcontext.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public void createProduct(@RequestBody ProductCreateRequestDTO productCreateRequestDTO) {
        productService.createProduct(productCreateRequestDTO);
    }

    @GetMapping
    public List<ProductListResponseDTO> createProduct() {
       return productService.getAllProducts();
    }
}
