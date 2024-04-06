package com.BookingProject.BookingServices;

import com.BookingProject.Model.Auth;
import com.BookingProject.Model.Booking;
import com.BookingProject.Model.BookingResponse;
import com.BookingProject.Model.Bookingdates;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Services extends BaseTests{
    Auth tokenBody;
    public String createToken(){

         tokenBody = new Auth("admin","password123");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(tokenBody)
                .post("/auth");
        response
                .then()
                .statusCode(200);
        return response.jsonPath().getJsonObject("token");
    }

    public BookingResponse createBooking(){

        Bookingdates bookingdates = new Bookingdates("2024-05-05","2024-05-05");
        Booking booking = new Booking("Sabir","Tahirli",999,true,
                bookingdates,"SmokeArea");
        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(booking)
                .post("/booking ");
        response
                .then()
                .statusCode(200);
        return response.as(BookingResponse.class);
    }

    public void deleteBooking(int bookingid,String tokenBody){

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie",tokenBody)
                .when()
                .delete("/booking/"+bookingid);
        response
                .then()
                .statusCode(201);

    }



}
