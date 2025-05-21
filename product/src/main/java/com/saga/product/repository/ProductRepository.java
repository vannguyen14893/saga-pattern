package com.saga.product.repository;

import com.saga.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Product entities.
 * Provides CRUD operations and custom queries for Product data access.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * Finds a product by its name.
     *
     * @param name the name of the product to search for
     * @return the Product entity if found, null otherwise
     */
    Product findByName(String name);
}
