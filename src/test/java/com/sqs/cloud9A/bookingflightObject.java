package com.sqs.cloud9A;

        import org.junit.Assert;
        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;

    public class  bookingflightObject {
        WebDriver driver;

        private By OriginLocator = By.name("Origin");
        private By DestinationLocator = By.name("Destination");
        private By seatLocator = By.name("seat");
        private By FlightclassLocator = By.name("Flightclass");
        private By bookButtonLocator = By.xpath("/html/body/div/div/div[2]/form/button");
        private By bodyTextLocator = By.tagName("body");

        private String cloud9BookFlightHeader = "Cloud9 - Book Flight";
        private String bookingSuccessful = "Flight booked successfully";



        public bookingflightObject(WebDriver driver) {

            this.driver = driver;
        }

        public void clickBook(WebDriver driver){

            //System.out.println("Click Register Button");
            driver.findElement(bookButtonLocator).click();
        }

        public void assertBookFlightHeader(){
            String bodyText = driver.findElement(bodyTextLocator).getText();
            Assert.assertTrue("Text not found!", bodyText.contains(cloud9BookFlightHeader));
        }

        public void assertBookingSuccessful(){
            String bodyText = driver.findElement(bodyTextLocator).getText();
            Assert.assertTrue("Text not found!", bodyText.contains(bookingSuccessful));
        }

        public void populateBooking(String Origin, String Destination, String seat, String Flightclass){

            assertBookFlightHeader();
            driver.findElement(OriginLocator).sendKeys(Origin);
            driver.findElement(DestinationLocator).sendKeys(Destination);
            driver.findElement(seatLocator).sendKeys(seat);
            driver.findElement(FlightclassLocator).sendKeys(Flightclass);

            clickBook(driver);
            assertBookingSuccessful();
        }
    }

