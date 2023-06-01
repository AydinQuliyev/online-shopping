package com.company.onlineshopping.repository;
import com.company.onlineshopping.entity.Category;
import com.company.onlineshopping.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product,Long> {
    Product findByCategory(Category category);
}
