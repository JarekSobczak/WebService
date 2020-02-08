package pl.com.own.webservice.model;

import lombok.Data;

@Data
public class Product {

    private String name;
    private String description;
    private Type type;
    private Double price;
}
