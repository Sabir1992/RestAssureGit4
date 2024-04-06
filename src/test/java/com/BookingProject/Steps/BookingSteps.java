package com.BookingProject.Steps;

import com.BookingProject.BookingServices.Services;
import com.BookingProject.Model.BookingResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class BookingSteps {

    Services bookingServices;
    String token;
    BookingResponse bookingResponse;
    @Given("First we need create token for creating booking")
    public void startTest(){
         bookingServices = new Services();
    }

    @Given("Create token")
    public void createToken(){
         token = bookingServices.createToken();
    }

    @When("Create booking")
    public void createBooking(){
        bookingResponse = bookingServices.createBooking();
    }

    @Then("Assertions checking")
    public void bookingAssertions(){
        Assertions.assertEquals("Sabir",bookingResponse.getBooking().getFirstname());
        Assertions.assertEquals("Tahirli",bookingResponse.getBooking().getLastname());
        Assertions.assertEquals(999,bookingResponse.getBooking().getTotalprice());
        Assertions.assertEquals(true,bookingResponse.getBooking().isDepositpaid());
        Assertions.assertEquals("SmokeArea",bookingResponse.getBooking().getAdditionalneeds());
    }

    @Then("Delete booking")
    public void deleteBooking(){
       bookingServices.deleteBooking(bookingResponse.getBookingid(),token);
    }
}
