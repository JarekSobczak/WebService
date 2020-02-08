package pl.com.own.webservice.model;

import lombok.Data;

@Data
public class Product {

    private String name;
    private String description;
    private Type type;
    private Double price;
    private long counter;

    public Product(String name, String description, Type type, Double price) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
    }
}
