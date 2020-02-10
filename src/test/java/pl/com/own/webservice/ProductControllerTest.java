package pl.com.own.webservice;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import pl.com.own.webservice.model.Product;
import pl.com.own.webservice.model.Type;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    @Mock
    private ProductService service;
    private ProductController controller;

    @Before
    public void setUp() throws Exception {
        controller = new ProductController(service);
    }

    @Test
    public void testShouldReturnListOfAllNames() {
        //given
        ArrayList<String> listOfNames = Lists.newArrayList("książka", "stolik", "kubek");
        ResponseEntity<ArrayList<String>> expected = ResponseEntity.ok(listOfNames);
        Mockito.when(service.getNamesOfAllProducts()).thenReturn(listOfNames);
        //when
        HttpEntity<List<String>> result = controller.getAllNames();
        //then
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testShouldReturnListOfAllNamesButOneIsNull() {
        //given
        ArrayList<String> listOfNames = Lists.newArrayList("książka", null, "kubek");
        ResponseEntity<ArrayList<String>> expected = ResponseEntity.ok(listOfNames);
        Mockito.when(service.getNamesOfAllProducts()).thenReturn(listOfNames);
        //when
        HttpEntity<List<String>> result = controller.getAllNames();
        //then
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testShouldReturnListOfAllNamesButExpectedIsWrong() {
        //given
        ArrayList<String> listOfNames = Lists.newArrayList("książka", null, "kubek");
        ResponseEntity<ArrayList<String>> expected = ResponseEntity.ok(Lists.newArrayList("książka", "kubek"));
        Mockito.when(service.getNamesOfAllProducts()).thenReturn(listOfNames);
        //when
        HttpEntity<List<String>> result = controller.getAllNames();
        //then
        Assert.assertNotEquals(expected, result);
    }

    @Test
    public void testShouldReturnOneProduct() {
        //given
        Product product = new Product("stolik", "nowy", Type.MALE, 200.0);
        ResponseEntity<Product> expected = ResponseEntity.ok(product);
        Mockito.when(service.findOne("stolik")).thenReturn(product);
        //when
        HttpEntity<Product> result = controller.getProduct("stolik");
        //then
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testShouldReturnOneProductButResultIsNull() {
        //given
        Product product = new Product("stolik", "nowy", Type.MALE, 200.0);
        ResponseEntity<Product> expected = ResponseEntity.ok(product);
        Mockito.when(service.findOne(Mockito.any())).thenReturn(null);
        //when
        HttpEntity<Product> result = controller.getProduct("dom");
        //then
        Assert.assertNotEquals(expected, result);
    }
}
