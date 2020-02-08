package pl.com.own.webservice;

import org.springframework.stereotype.Service;
import pl.com.own.webservice.model.Product;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private ProductRepo repo;

    public ProductService(ProductRepo repo) {
        this.repo = repo;
    }

    public List<String> getNamesOfAllProducts(){
       return repo.getProducts().stream()
                .map(Product::getName)
                .collect(Collectors.toList());
    }

    public Product findOne(String name){
       return repo.getProducts().stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }
}
