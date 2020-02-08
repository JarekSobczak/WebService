package pl.com.own.webservice;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import pl.com.own.webservice.model.Product;
import pl.com.own.webservice.model.Type;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepo repo;
    private ProductService service;

    @Before
    public void setUp() throws Exception {
        service = new ProductService(repo);
    }

    @Test
    public void testShouldGetNamesOfAllProducts() {
        //given
        Product product = new Product("stół", null, null, null);
        Product product2 = new Product("łyżka", null, null, null);
        Product product3 = new Product("fotel", null, null, null);
        ArrayList<Product> products = Lists.newArrayList(product, product2, product3);
        Mockito.when(repo.getProducts()).thenReturn(products);
        ArrayList<String> expected = Lists.newArrayList("stół", "łyżka", "fotel");
        //when
        List<String> result = service.getNamesOfAllProducts();
        //then
        assertEquals(expected, result);
    }

    @Test
    public void testShouldGetNamesOfAllProductsButExpectedIsDifferent() {
        //given
        Product product = new Product("stół", null, null, null);
        Product product2 = new Product("łyżka", null, null, null);
        Product product3 = new Product("fotel", null, null, null);
        ArrayList<Product> products = Lists.newArrayList(product, product2, product3);
        Mockito.when(repo.getProducts()).thenReturn(products);
        ArrayList<String> expected = Lists.newArrayList("stół", "samochód", "fotel");
        //when
        List<String> result = service.getNamesOfAllProducts();
        //then
        assertNotEquals(expected, result);
    }

    @Test
    public void testShouldGetNamesOfAllProductsButOneIsNull() {
        //given
        Product product = new Product("stół", null, null, null);
        Product product2 = new Product(null, null, null, null);
        Product product3 = new Product("fotel", null, null, null);
        ArrayList<Product> products = Lists.newArrayList(product, product2, product3);
        Mockito.when(repo.getProducts()).thenReturn(products);
        ArrayList<String> expected = Lists.newArrayList("stół", null, "fotel");
        //when
        List<String> result = service.getNamesOfAllProducts();
        //then
        assertEquals(expected, result);
    }

    @Test
    public void testShouldReturnOneProductWithCounter1AndPriceAfterDiscount() {
        //given
        Product product = new Product("stół", "stary", Type.MALE, 100.0);
        Product product2 = new Product("ława", "nowa", Type.KID, 100.0);
        ArrayList<Product> products = Lists.newArrayList(product, product2);
        Mockito.when(repo.getProducts()).thenReturn(products);
        Product expectedProduct = product2;
        long expectedCounter = 1L;
        Double expectedPrice = 90.0;
        //when
        Product result = service.findOne("ława");
        assertEquals(expectedProduct, result);
        assertEquals(expectedCounter, result.getCounter());
        assertEquals(expectedPrice, result.getPrice());
    }

    @Test
    public void testShouldReturnOneProductWithCounter1AndPriceAfterDiscountButExpectedProductIsWrong() {
        //given
        Product product = new Product("stół", "stary", Type.MALE, 100.0);
        Product product2 = new Product("ława", "nowa", Type.KID, 100.0);
        ArrayList<Product> products = Lists.newArrayList(product, product2);
        Mockito.when(repo.getProducts()).thenReturn(products);
        Product expectedProduct = product;
        long expectedCounter = 1L;
        Double expectedPrice = 90.0;
        //when
        Product result = service.findOne("ława");
        assertNotEquals(expectedProduct, result);
        assertEquals(expectedCounter, result.getCounter());
        assertEquals(expectedPrice, result.getPrice());
    }

    @Test
    public void testShouldReturnOneProductWithCounter1AndPriceAfterDiscountButExpectedCounterIsWrong() {
        //given
        Product product = new Product("stół", "stary", Type.MALE, 100.0);
        Product product2 = new Product("ława", "nowa", Type.KID, 100.0);
        ArrayList<Product> products = Lists.newArrayList(product, product2);
        Mockito.when(repo.getProducts()).thenReturn(products);
        Product expectedProduct = product2;
        long expectedCounter = 0;
        Double expectedPrice = 90.0;
        //when
        Product result = service.findOne("ława");
        assertEquals(expectedProduct, result);
        assertNotEquals(expectedCounter, result.getCounter());
        assertEquals(expectedPrice, result.getPrice());
    }

    @Test
    public void testShouldReturnOneProductWithCounter1AndPriceAfterDiscountButExpectedPriceIsWrong() {
        //given
        Product product = new Product("stół", "stary", Type.MALE, 100.0);
        Product product2 = new Product("ława", "nowa", Type.KID, 100.0);
        ArrayList<Product> products = Lists.newArrayList(product, product2);
        Mockito.when(repo.getProducts()).thenReturn(products);
        Product expectedProduct = product2;
        long expectedCounter = 1;
        Double expectedPrice = 100.0;
        //when
        Product result = service.findOne("ława");
        assertEquals(expectedProduct, result);
        assertEquals(expectedCounter, result.getCounter());
        assertNotEquals(expectedPrice, result.getPrice());
    }
}
