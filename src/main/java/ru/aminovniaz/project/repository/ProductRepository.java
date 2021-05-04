package ru.aminovniaz.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aminovniaz.project.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
