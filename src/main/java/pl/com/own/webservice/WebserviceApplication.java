package pl.com.own.webservice;

import com.google.common.collect.Lists;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.com.own.webservice.model.Product;
import pl.com.own.webservice.model.Type;

@SpringBootApplication
public class WebserviceApplication implements CommandLineRunner {
    private ProductRepo repo;

    public WebserviceApplication(ProductRepo repo) {
        this.repo = repo;
    }

    public static void main(String[] args) {
        SpringApplication.run(WebserviceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Product product1 = new Product("kurtka", "męska", Type.MALE, 100.0);
        Product product2 = new Product("spódnica", "damska", Type.FEMALE, 80.0);
        Product product3 = new Product("piłka", "plażowa", Type.KID, 20.0);
        Product product4 = new Product("okulary", "przeciwsłoneczne", Type.MALE, 70.0);
        Product product5 = new Product("chustka", "jedwabna", Type.FEMALE, 90.0);
        Product product6 = new Product("czapka", "baseball", Type.KID, 20.0);

        repo.getProducts().addAll(Lists.newArrayList(product1, product2, product3, product4, product5, product6));
    }
}
