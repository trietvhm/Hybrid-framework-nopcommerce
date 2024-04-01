package com.nopcommerce.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Account_01_Register {
    WebDriver driver;
    //String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        // System.setProperty("user.dir",projectPath + "\\browserDriver\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void Register_01_Empty_Data() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        driver.findElement(By.cssSelector("button#register-button")).click();

        Thread.sleep(Duration.ofSeconds(3)); // có vấn đề

        //Assert.assertTrue(driver.findElement(By.xpath("//span[text()='First name is required.']")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.cssSelector("span#FirstName-error")).getText(), "First name is required.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#LastName-error")).getText(), "Last name is required.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(), "Email is required.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#Password-error")).getText(), "Password is required.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText(), "Password is required.");

    }

    @Test
    public void Register_02_Invalid_Email() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.xpath("//a[text()='Register']")).click();

        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
        driver.findElement(By.cssSelector("input#Email")).sendKeys("john@kennedy@us");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("123456789");
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456789");

        driver.findElement(By.cssSelector("button#register-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(), "Wrong email");

    }

    @Test
    public void Register_03_Invalid_Password() {
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.xpath("//a[text()='Register']")).click();

        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
        driver.findElement(By.cssSelector("input#Email")).sendKeys("Automation@gmail.com");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("1234");
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("1234");

        driver.findElement(By.cssSelector("button#register-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("span#Password-error")).getText(),
                "Password must meet the following rules:\n" + "must have at least 6 characters");
    }

    @Test
    public void Register_04_Incorrect_Confirm_Password() {
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.xpath("//a[text()='Register']")).click();

        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
        driver.findElement(By.cssSelector("input#Email")).sendKeys("Automation@gmail.com");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("123456789");
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456781");

        driver.findElement(By.cssSelector("button#register-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText(),
                "The password and confirmation password do not match.");

    }

    @Test
    public void Register_05_Success() {
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Automation");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("FC");
        driver.findElement(By.cssSelector("input#Email")).sendKeys("Automation111@gmail.com");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("123456789");
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456789");

        driver.findElement(By.cssSelector("button#register-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.registration-result-page div.result")).getText(),"Your registration completed");

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
