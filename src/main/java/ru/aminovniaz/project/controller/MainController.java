package ru.aminovniaz.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.aminovniaz.project.dto.ProductForm;
import ru.aminovniaz.project.model.Product;
import ru.aminovniaz.project.service.ProductService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/main")
public class MainController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getMain(ModelMap map) {
        map.addAttribute("products", productService.getAllProduct());
        return "main";
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.GET)
    public String getProductForm(ModelMap map) {
        map.addAttribute("product", new Product());
        map.addAttribute("productForm", new ProductForm());
        map.addAttribute("categories", productService.getAllCategories());
        return "productForm";
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public String addProduct(@Valid ProductForm productForm,
                             BindingResult result,
                             RedirectAttributes redirectAttributes,
                             ModelMap map) {
        if (!result.hasErrors()) {
            System.out.println("SAVE PRODUCT");
            productService.addProduct(productForm);
        } else {
            System.out.println("ERROR");
            return "productForm";
        }

        return "redirect:/main/home";
    }
}
