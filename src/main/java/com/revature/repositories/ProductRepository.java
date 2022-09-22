package com.revature.repositories;

import com.revature.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("FROM Product WHERE is_active = true")
    List<Product> findAllActive();

    @Query("FROM Product WHERE is_active = true and id = :id")
    Optional<Product> findActiveById(int id);

    @Query("FROM Product WHERE (lower(name) LIKE lower(:keyword) OR lower(description) LIKE lower(:keyword)) AND is_active = true")
    List<Product> findByKeyword(String keyword);

}
