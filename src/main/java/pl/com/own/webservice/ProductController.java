package pl.com.own.webservice;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.own.webservice.model.Product;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public HttpEntity<List<String>> getAllNames() {
        return ResponseEntity.ok(service.getNamesOfAllProducts());
    }

    @GetMapping("/{name}")
    public HttpEntity<Product> getProduct(@PathVariable String name) {
        return ResponseEntity.ok(service.findOne(name));
    }
}
