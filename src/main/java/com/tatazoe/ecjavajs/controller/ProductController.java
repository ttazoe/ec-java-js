package com.tatazoe.ecjavajs.controller;


import com.tatazoe.ecjavajs.config.ApiResponse;
import com.tatazoe.ecjavajs.dto.ProductDto;
import com.tatazoe.ecjavajs.model.Category;
import com.tatazoe.ecjavajs.service.CategoryService;
import com.tatazoe.ecjavajs.service.ProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;


    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> productDtos = productService.listProducts();
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto) {
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category is invalid"),HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();
        productService.addProduct(productDto, category);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("id") Integer id, @Valid @RequestBody ProductDto productDto) {
        if (Objects.isNull(productService.readProduct(id)))
            return new ResponseEntity<>(new ApiResponse(false, "Product Id" + id + " is not found"), HttpStatus.NOT_FOUND);

        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new ResponseEntity<>(new ApiResponse(false, "Category ID " + productDto.getCategoryId() + " is not found"), HttpStatus.NOT_FOUND);

        Category category = optionalCategory.get();
        productService.updateProduct(id, productDto, category);
        return new ResponseEntity<>(new ApiResponse(true, "Product is updated"), HttpStatus.OK);
    }
}
