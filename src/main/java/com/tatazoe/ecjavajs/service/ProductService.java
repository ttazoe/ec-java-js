package com.tatazoe.ecjavajs.service;

import com.tatazoe.ecjavajs.dto.ProductDto;
import com.tatazoe.ecjavajs.model.Category;
import com.tatazoe.ecjavajs.model.Product;
import com.tatazoe.ecjavajs.repository.CategoryRepository;
import com.tatazoe.ecjavajs.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public void addProduct(ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);
        productRepository.save(product);
    }

    public List<ProductDto> listProducts() {
        List<Product> listProducts = productRepository.findAll();
        List<ProductDto> listProductDto = new ArrayList<>();

        for (Product product : listProducts) {
            listProductDto.add(new ProductDto(product));
        }
        return listProductDto;
    }

    public ProductDto readProduct(Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Product product = optionalProduct.get();
        return new ProductDto(product);
    }

    public void updateProduct(Integer id, ProductDto newProductDto, Category category) {
        ProductDto productDto = readProduct(id);

        // Update DTO
        productDto.setName(newProductDto.getName());
        productDto.setDescription(newProductDto.getDescription());
        productDto.setPrice(newProductDto.getPrice());
        productDto.setImageURL(newProductDto.getImageURL());
        productDto.setCategoryId(newProductDto.getCategoryId());

        Product product = getProductFromDto(productDto, category);
        product.setId(productDto.getId());
        productRepository.save(product);
    }

    public static Product getProductFromDto(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setCategory(category);
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageURL());
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        return product;
    }

}
