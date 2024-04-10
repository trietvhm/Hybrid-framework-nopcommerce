package com.nopcommerce.account;

import commons.BasePage;
import org.bouncycastle.jcajce.provider.symmetric.ARC4;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Level_02_BasePage_3_Inheritance extends BasePage {
    private WebDriver driver;
    private String projectPath = System.getProperty("user.dir");

    // Khai bao cach 2
    //private BasePage basePage =getBasePage();

    @BeforeClass
    public void beforeClass() {
        System.setProperty("user.dir", projectPath + "\\browserDriver\\geckodriver.exe");
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void Register_01_Empty_Data() throws InterruptedException {
        openPageUrl(driver, "https://demo.nopcommerce.com/");
        clickToElement(driver, "//a[text()='Register']");
        clickToElement(driver, "//button[@id='register-button']");

        //Thread.sleep(Duration.ofSeconds(5)); // có vấn đề

        //Assert.assertTrue(driver.findElement(By.xpath("//span[text()='First name is required.']")).isDisplayed());
        Assert.assertEquals(getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
        Assert.assertEquals(getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
        Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
        Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
        Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");

    }

    @Test
    public void Register_02_Invalid_Email() throws InterruptedException {
        openPageUrl(driver, "https://demo.nopcommerce.com/");
        clickToElement(driver, "//a[text()='Register']");

        sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
        sendKeyToElement(driver, "//input[@id='LastName']", "FC");
        sendKeyToElement(driver, "//input[@id='Email']", "john@kennedy@us");
        sendKeyToElement(driver, "//input[@id='Password']", "123456789");
        sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456789");

        clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
    }

    @Test
    public void Register_03_Invalid_Password() {
        openPageUrl(driver, "https://demo.nopcommerce.com/");
        clickToElement(driver, "//a[text()='Register']");

        sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
        sendKeyToElement(driver, "//input[@id='LastName']", "FC");
        sendKeyToElement(driver, "//input[@id='Email']", "Automation@gmail.com");
        sendKeyToElement(driver, "//input[@id='Password']", "1234");
        sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "1234");

        clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"),
                "Password must meet the following rules:\n" + "must have at least 6 characters");

    }

    @Test
    public void Register_04_Incorrect_Confirm_Password() {
        openPageUrl(driver, "https://demo.nopcommerce.com/");
        clickToElement(driver, "//a[text()='Register']");

        sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
        sendKeyToElement(driver, "//input[@id='LastName']", "FC");
        sendKeyToElement(driver, "//input[@id='Email']", "Automation@gmail.com");
        sendKeyToElement(driver, "//input[@id='Password']", "123456789");
        sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456798");

        clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"),
                "The password and confirmation password do not match.");


    }

    @Test
    public void Register_05_Success() {
        openPageUrl(driver, "https://demo.nopcommerce.com/");
        clickToElement(driver, "//a[text()='Register']");

        sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
        sendKeyToElement(driver, "//input[@id='LastName']", "FC");
        sendKeyToElement(driver, "//input[@id='Email']", "Automation@gmail2.com");
        sendKeyToElement(driver, "//input[@id='Password']", "123456789");
        sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456789");

        clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(getElementText(driver, "//div[@class='result']"),
                "Your registration completed");

        //Assert.assertEquals(driver.findElement(By.cssSelector("div.registration-result-page div.result")).getText(), "Your registration completed");

    }

    @AfterClass
    public void afterClass() {
        // driver.quit();
    }

}
