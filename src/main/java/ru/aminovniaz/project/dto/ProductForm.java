package ru.aminovniaz.project.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ProductForm {
    @NotNull
    @NotEmpty(message = "This field can't be empty")
    private String name;
    @Min(value = 50, message = "Too little")
    @Max(value = 10000, message = "Too many")
    private int cost;
    @NotEmpty
    @NotNull(message = "This field can't be empty")
    private String category;

    public ProductForm() {}

    public ProductForm(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
