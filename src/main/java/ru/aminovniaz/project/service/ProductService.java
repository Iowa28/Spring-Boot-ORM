package ru.aminovniaz.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aminovniaz.project.dto.ProductForm;
import ru.aminovniaz.project.model.Category;
import ru.aminovniaz.project.model.Product;
import ru.aminovniaz.project.repository.CategoryRepository;
import ru.aminovniaz.project.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private ProductRepository productRepo;

    public void addProduct(ProductForm productForm) {
        Product product = new Product();
        product.setName(productForm.getName());
        product.setCost(productForm.getCost());

        System.out.println(productForm.getCategory());

        Category category = categoryRepo.findById(Long.parseLong(productForm.getCategory()))
                .orElseThrow(() -> new IllegalArgumentException("No category here"));

        product.setCategory(category);



        productRepo.save(product);
    }

    public void deleteProduct(Product product) {
        productRepo.delete(product);
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }
}
