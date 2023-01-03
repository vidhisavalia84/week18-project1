package com.bestbuy.bestbuyinfo;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class ProductCRUDTest extends TestBase {
    static String name = "Battery-" + TestUtils.getRandomValue();
    static String type = "Chargable";
    static int price = 6;
    static int shipping = 8;
    static String upc = "045689";
    static String description = "Compatible with select electronic devices";
    static String manufacture = "Duracell";
    static String model = "MN2400B4Z";
    static String url = "http://www.bestbuy.com";
    static String image = "http://img.bbystatic.com";
    static int productId;

    @Steps
    ProductSteps productSteps;

    @Title("This will create new product")
    @Test
    public void test001() {
        ValidatableResponse response = productSteps.addProduct(name, type, price, shipping, upc, description, manufacture, model, url, image);
        response.log().all().statusCode(201);
    }

    @Title("Getting the product information with Name:")
    @Test
    public void test002() {

        HashMap<String, Object> product = productSteps.getProductByName(name);
        Assert.assertThat(product, hasValue(name));
        int productId = (int) product.get("id");
    }

    @Title("Updating product information and verify the updated information")
    @Test
    public void test003() {
        name = name + "_add";
        productSteps.updateProduct(productId, name, type, price, shipping, upc, description, manufacture, model, url, image)
                .log().all().statusCode(200);

        HashMap<String, Object> product = productSteps.getProductByName(name);
        Assert.assertThat(product, hasValue(name));
    }

    @Title("Deleting product with id and verify that user is deleted")
    @Test
    public void test004() {
        productSteps.deleteProduct(productId).statusCode(204);
        productSteps.getProductById(productId).statusCode(404);
    }
}
