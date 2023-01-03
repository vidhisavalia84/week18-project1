package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StorePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.seleniumhq.jetty9.io.EndPoint;

import java.util.HashMap;

public class StoreSteps {

    /*{
   "name": "str12",
    "type": "str13",
   "address": "string",
   "address2": "string",
   "city": "string",
   "state": "string",
   "zip": "string",
   "lat": 0,
   "lng": 0,
   "hours": "string",
   "services": {}
 }*/
@Step("Creating a store with name:{0},type:{1},address:{2},address2:{3},city:{4},state:{5},zip:{6},lat:{7},lang:{8},hours:{9}")
    public ValidatableResponse addStore(String name, String type, String address, String address2, String city, String state, String zip, int lat, int lang, String hours){
    StorePojo storePojo=new StorePojo();
    storePojo.setName(name);
    storePojo.setType(type);
    storePojo.setAddress(address);
    storePojo.setAddress2(address2);
    storePojo.setCity(city);
    storePojo.setState(state);
    storePojo.setZip(zip);
    storePojo.setLat(lat);
    storePojo.setLng(lang);
    storePojo.setHours(hours);
   // storePojo.setServices(services);
   return SerenityRest.given().log().all()
            .contentType(ContentType.JSON)
            .body(storePojo)
            .when()
            .post(EndPoints.STORE)
            .then();
}

@Step("Get store by name:{0}")
    public HashMap<String,Object> getStoreByName(String name){
    String p1= "data.findAll{it.name =='" ;
       String p2=     "'}.get(0)";
       return SerenityRest.given().log().all()
               .when()
               .get(EndPoints.STORE)
               .then()
               .statusCode(200)
               .extract()
               .path(p1+name+p2);
}
@Step("Updating a store by storeId:{0},name:{1},type:{2},address:{3},address2:{4},city:{5},state:{6},zip:{7},lat:{8},lang:{9},hours:{10} ")
    public ValidatableResponse updateStoreByName(int storeId,String name,String type, String address, String address2, String city, String state, String zip, int lat, int lang, String hours){
    StorePojo storePojo=new StorePojo();
    storePojo.setName(name);
    storePojo.setType(type);
    storePojo.setAddress(address);
    storePojo.setAddress2(address2);
    storePojo.setCity(city);
    storePojo.setState(state);
    storePojo.setZip(zip);
    storePojo.setLat(lat);
    storePojo.setLng(lang);
    storePojo.setHours(hours);
    return SerenityRest.given().log().all()
            .header("Content-Type", "application/json")
            .pathParam("storeId", storeId)
            .body(storePojo)
            .when()
            .put(EndPoints.UPDATE_STORE_BY_ID)
            .then();
}

@Step("Deleting store storeId:{0}")
    public ValidatableResponse deleteStore(int storeId){
    return SerenityRest.given().log().all()
            .pathParam("Id",storeId)
            .when()
            .delete(EndPoints.DELETE_STORE_BY_ID)
            .then();

}
@Step("Getting store by storeId:{0}")
    public ValidatableResponse getStoreById(int storeId){
    return SerenityRest.given().log().all()
            .pathParam("Id",storeId)
            .when()
            .get(EndPoints.GET_STORE_BY_ID)
            .then();
}
}
