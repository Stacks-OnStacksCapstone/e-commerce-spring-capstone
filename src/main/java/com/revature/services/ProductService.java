package com.revature.services;

import com.revature.dtos.ProductInfo;
import com.revature.models.Product;
import com.revature.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAllActive();
    }

    public Optional<Product> findById(int id) {
        return productRepository.findActiveById(id);
    }

    public List<Product> findByKeyword(String keyword){
        return productRepository.findByKeyword("%" + keyword + "%");
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> saveAll(List<Product> productList, List<ProductInfo> metadata) {
        return productRepository.saveAll(productList);
    }

    public void delete(int id) {
        Optional<Product> p = findById(id);

        if (p.isPresent()) {
            Product pr = p.get();
            pr.setActive(false);
            save(pr);
        }
    }
}
