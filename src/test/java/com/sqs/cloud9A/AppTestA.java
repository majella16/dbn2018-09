package com.sqs.cloud9A;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class AppTestA {
    /**
     * Rigorous Test :-)
     */

    static WebDriver driver;
    private String registrationSuccessful = "Registration Successful";
    private By bodyTextLocator = By.tagName("body");
    private String cloud9RegisterHeader = "Cloud9 - Register";

    private String loginSuccessful = "Welcome maj";
    // private By bodyTextLocator = By.tagName("body");
    private String cloud9signinHeader = "Cloud9 - Sign in";

    private String cloud9BookFlightHeader = "Cloud9 - Book Flight";
    private String bookingSuccessful = "Flight booked successfully";

    private String cloud9ItineraryHeader = "Cloud9 - Itinerary";
    private String cloud9EditFlightHeader = "Cloud9 - Edit Flight";
    private String updateSuccessful = "Flight successfully updated";


    @Before
    public void testSetup() {
        System.out.println("In setup");
        System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        String baseUrl = "http://10.9.10.39:81/sqlite/Main/login.html";
        String expectedTitle = "Cloud9 Airlines";
        driver.get(baseUrl);
        String actualTitle = driver.getTitle();
        System.out.println("Actual title is: " + actualTitle);
        Assert.assertEquals(actualTitle, expectedTitle);

    }

    @BeforeClass
    public static void browserSetup() {

    }


    @Test
    public void shouldRegisterNewCustomer()

    {
        driver.findElement(By.linkText("Register")).click();

        registrationObject registrationPage;
        registrationPage = new registrationObject(driver);
        registrationPage.populateRegistration("maj", "ferns", "majferns123@gmail.com", "xxx");


    }


    @Test
    public void shouldLoginCustomer() throws InterruptedException

    {


        loginObject loginPage;
        loginPage = new loginObject(driver);
        loginPage.populateLogin("majferns@gmail.com", "xxx");
        driver.findElement(By.linkText("Homepage")).click();
    }

    @Test
    public void shouldBookFlight() throws InterruptedException

    {
        /*loginObject loginPage;
        loginPage = new loginObject(driver);
        loginPage.populateLogin( "majferns@gmail.com", "xxx");
        driver.findElement(By.linkText("Homepage")).click();*/
        //shouldLoginCustomer();


        driver.findElement(By.linkText("Book Flight")).click();
        bookingflightObject bookingPage;
        bookingPage = new bookingflightObject(driver);

        new Select(driver.findElement(By.id("Origin"))).selectByVisibleText("Chicago");
        new Select(driver.findElement(By.id("Destination"))).selectByVisibleText("Dubai");
        driver.findElement(By.name("seat")).sendKeys("33");
        new Select(driver.findElement(By.id("Flightclass"))).selectByVisibleText("Economy");
        driver.findElement(By.xpath("/html/body/div/div/div[2]/form/button")).click();
        Thread.sleep(1000);



    }

    @Test
    public void Itinerary() throws InterruptedException

    {

        shouldLoginCustomer();
        //shouldBookFlight();

        driver.findElement(By.linkText("Itinerary")).click();
        itinerary itineraryPage;
        itineraryPage = new itinerary(driver);
      scanBookingsAndClickUpdate("448");

      //driver.findElement(By.linkText("Update")).click();

     /*   itinerary editPage;
        editPage = new itinerary(driver);
        driver.findElement(By.xpath("//*[@id=\"seat\"]")).clear();
        driver.findElement(By.name("seat")).sendKeys("20f");
        driver.findElement(By.xpath("/html/body/div/div/div[2]/form/button")).click();*/
        Thread.sleep(1000);

       // driver.findElement(By.xpath("/html/body/div/div/div[2]/center/a")).click();

    }

    public void scanBookingsAndClickUpdate(String flightid) throws InterruptedException {



        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr"));
        System.out.println("Number of rows is " + rows.size());

        int rowNum = rows.size();

        // Get booking id for latest booking
        List<WebElement> colVals2 = rows.get(rowNum - 1).findElements(By.tagName("td"));
        //System.out.println("What is this " + colVals2.get(3).getText());
        String columnContents;
        for (int i = 1; i < rowNum; i++) {
            List<WebElement> colVals1 = rows.get(i).findElements(By.tagName("td"));
            for (int j = 0; j < 6; j++) {
                columnContents = colVals1.get(j).getText();
                if (columnContents.equals(flightid)) {
                    System.out.println("Yippeee,  found it: i=" + i + " : j = " + j);
                    System.out.println("Column Contents = " + columnContents);
                    Thread.sleep(1000);
                   driver.findElement(By.linkText("Update")).click();
                  itinerary editPage;
                    editPage = new itinerary(driver);
                    driver.findElement(By.xpath("//*[@id=\"seat\"]")).clear();
                    driver.findElement(By.name("seat")).sendKeys("80f");
                    driver.findElement(By.xpath("/html/body/div/div/div[2]/form/button")).click();
                    Thread.sleep(1000);

                    driver.findElement(By.xpath("/html/body/div/div/div[2]/center/a")).click();
                    Thread.sleep(1000);
                  //  driver.findElement(By.linkText("Logout")).click();
                }
            else {
                    System.out.println("j= " + j + " : " + colVals1.get(j).getText());
                }
                // If we come out of the loop and have not found the ID, then print a relevant message
            }
        }
    }
    @Test
    public void updateSeatNumber() throws InterruptedException {

       

        String flightID = "265";
        String newSeatNumber = "A32";

        itineraryObject itineraryPage;
        itineraryPage = new itineraryObject(driver);
        String editURL = itineraryPage.scanBookingsAndFindFlightID(flightID);
        System.out.println("editURL is: " + editURL);

        if (editURL.equals("notfound")) {
            System.out.println("flightID: " + flightID + " not found" );
        } else {
            driver.get(editURL);
            // TODO create new object for editFlight class
            // TODO call the update method

            editObject editPage;
            editPage = new editObject(driver);
            editPage.editBooking(driver, newSeatNumber);
        }
    }



    @After
    public void tearDown()
    {
      // driver.close();
    }

}
