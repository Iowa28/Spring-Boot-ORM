package ru.aminovniaz.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.aminovniaz.project.model.Category;
import ru.aminovniaz.project.model.Product;
import ru.aminovniaz.project.model.User;
import ru.aminovniaz.project.util.EntityConverter;

@Configuration
@ComponentScan("ru.aminovniaz.project.controller")
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public EntityConverter userGenericConverter() {
        return new EntityConverter(User.class);
    }

    @Bean
    public EntityConverter productGenericConverter() {
        return new EntityConverter(Product.class);
    }

    @Bean
    public EntityConverter categoryGenericConverter() {
        return new EntityConverter(Category.class);
    }
}
