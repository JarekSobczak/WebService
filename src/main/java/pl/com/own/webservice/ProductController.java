package pl.com.own.webservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.com.own.webservice.model.Product;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>>getAllNames(){
        return ResponseEntity.ok(service.getNamesOfAllProducts());
    }

    @GetMapping
    public ResponseEntity<Product>getProduct(@RequestParam String name){
        return ResponseEntity.ok(service.findOne(name));
    }
}
