package pl.com.own.webservice;

import lombok.Data;
import org.springframework.stereotype.Repository;
import pl.com.own.webservice.model.Product;

import java.util.ArrayList;
import java.util.List;
@Data
@Repository
public class ProductRepo {

    private List<Product> products=new ArrayList<>();
}
