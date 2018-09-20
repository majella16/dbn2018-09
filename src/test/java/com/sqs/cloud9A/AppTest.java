package com.sqs.cloud9A;

import static org.junit.Assert.assertTrue;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */

    static WebDriver driver;
    private String registrationSuccessful = "Registration Successful";
    private By bodyTextLocator = By.tagName("body");
    private String cloud9RegisterHeader = "Cloud9 - Register";


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
        System.out.println("Actual title is: " +actualTitle);
        Assert.assertEquals(actualTitle, expectedTitle);

    }

    @BeforeClass
    public static void browserSetup()
    {

    }


   @Test
    public void shouldRegisterNewCustomer() throws InterruptedException

    {
        By loginlocator = By.xpath("/html/body/div/form/center/a");
        driver.findElement(By.xpath("/html/body/div/form/center/a")).click();
        driver.findElement(By.name("firstname")).sendKeys("Majella");
        driver.findElement(By.name("lastname")).sendKeys("Pinto");
        driver.findElement(By.name("email")).sendKeys("majellapinto1121@sqs.com");
        driver.findElement(By.name("password")).sendKeys("abc");
        driver.findElement(By.xpath("/html/body/div/form/button")).click();

        String bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Text not found!", bodyText.contains(registrationSuccessful));
        Thread.sleep(5000);
    }


    @Test
    public void shouldLoginCustomer() throws InterruptedException

    {
        driver.findElement(By.name("email")).sendKeys("majellapinto11@sqs.com");
        driver.findElement(By.name("password")).sendKeys("abc");
        driver.findElement(By.xpath("/html/body/div/form/button")).click();
        Thread.sleep(5000);
    }

    @After
    public void tearDown()
    {
      // driver.close();
    }

}
