package com.sqs.cloud9A;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class itinerary {

    WebDriver driver;
    private String cloud9ItineraryHeader = "Cloud9 - Itinerary";
   private String cloud9EditFlightHeader = "Cloud9 - Edit Flight";
    private By seatLocator = By.name("seat");
    private String updateSuccessful = "Flight successfully updated";
    private By updateButtonLocator = By.xpath("/html/body/div/div/div[2]/form/button");
    private By bodyTextLocator = By.tagName("body");

   public itinerary(WebDriver driver) {

       this.driver = driver;
   }

   public void clickUpdate(WebDriver driver){

       // System.out.println("Click Register Button");
       driver.findElement(updateButtonLocator).click();
   }

    public void assertItineraryHeader(){
        String bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Text not found!", bodyText.contains(cloud9ItineraryHeader));
    }
    public void assertEditFlightHeader(){
        String bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Text not found!", bodyText.contains(cloud9ItineraryHeader));
    }

    public void assertUpdateSuccessful(){
        String bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Text not found!", bodyText.contains(updateSuccessful));
    }

    public void populateEditbooking(String seat){

        assertEditFlightHeader();
       // driver.findElement(OriginLocator).sendKeys(Origin);
       // driver.findElement(DestinationLocator).sendKeys(Destination);
        driver.findElement(seatLocator).sendKeys(seat);
       // driver.findElement(FlightclassLocator).sendKeys(Flightclass);

        clickUpdate(driver);
        assertUpdateSuccessful();
    }
}
