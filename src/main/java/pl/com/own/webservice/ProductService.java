package pl.com.own.webservice;

import org.springframework.stereotype.Service;
import pl.com.own.webservice.model.Product;
import pl.com.own.webservice.model.Type;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private ProductRepo repo;

    public ProductService(ProductRepo repo) {
        this.repo = repo;
    }

    public List<String> getNamesOfAllProducts() {
        return repo.getProducts().stream()
                .map(Product::getName)
                .collect(Collectors.toList());
    }

    public Product findOne(String name) {
        Product found = repo.getProducts().stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
        if (found !=null) found.setCounter(found.getCounter() + 1);
        if (found != null&& found.getCounter()==1) setPrice(found);

        return found;
    }

    private void setPrice(Product found) {
        if (found.getType().equals(Type.KID)) {
            found.setPrice(found.getPrice() * 0.90);
        } else {
            found.setPrice(found.getPrice() * 0.95);
        }
    }
}
