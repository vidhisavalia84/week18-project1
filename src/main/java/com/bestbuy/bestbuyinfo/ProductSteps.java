package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.List;

public class ProductSteps {
       /*{
  "name": "string",
  "type": "string",
  "price": 0,
  "shipping": 0,
  "upc": "string",
  "description": "string",
  "manufacturer": "string",
  "model": "string",
  "url": "string",
  "image": "string"
}
*/
@Step("Creating a product by name:{0},type:{1},price:{2},shipping:{3},upc:{4},description:{5},manufacture:{6},model:{7},url:{8},image:{9}")
public ValidatableResponse addProduct(String name,String type,int price,int shipping,String upc,String description,String manufacture,String model,String url,String image){
    ProductPojo productPojo=new ProductPojo();
    productPojo.setName(name);
    productPojo.setType(type);
    productPojo.setPrice(price);
    productPojo.setShipping(shipping);
    productPojo.setUpc(upc);
    productPojo.setDescription(description);
    productPojo.setManufacturer(manufacture);
    productPojo.setModel(model);
    productPojo.setUrl(url);
    productPojo.setImage(image);
     return SerenityRest.given().log().all()
            .contentType(ContentType.JSON)
            .body(productPojo)
            .when()
            .post(EndPoints.CREATE_PRODUCT)
            .then();
}

    @Step("Getting the student information with firstName: {0}")
    public HashMap<String,Object> getProductByName(String name)
    {

    String s1="data.findAll{it.name == '";
            String s2="'}.get(0)";
  return   SerenityRest.given().log().all()
          .contentType(ContentType.JSON)
          .headers("Accept","*/*")
         // .contentType(Accept=*/*)
         // .queryParam("name",name)
            .when()
            .get(EndPoints.GET_ALL_PRODUCT)
            .then()
            .statusCode(200)
            .extract()

            .path(s1+name+s2);

    }
    @Step("Updating product by productId:{0},name:{1},type:{2},price:{3},shipping:{4},upc:{5},description:{6},manufacture:{7},model:{8},url:{9},image:{10} ")
    public ValidatableResponse updateProduct(int productId, String name,String type,int price,int shipping,String upc,String description,String manufacture,String model,String url,String image){
        ProductPojo productPojo=new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacture);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);
      return   SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("productID",productId)
        .body(productPojo)
                .when()
                .put(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then();

    }
    @Step("Deleting product productId:{0}")
    public ValidatableResponse deleteProduct(int productId){
    return SerenityRest.given().log().all()
            .pathParam("productID",productId)
            .when()
            .delete(EndPoints.DELETE_PRODUCT_BY_ID)
            .then();

    }
    @Step("Getting product by productId:{0}")
    public ValidatableResponse getProductById(int productId)
    {
        return SerenityRest.given().log().all()
                .pathParam("productID",productId)
                .when()
                .get(EndPoints.GET_PRODUCT_BY_ID)
                .then();
    }
}
