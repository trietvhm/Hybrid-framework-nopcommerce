package com.nopcommerce.account;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.CustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

import java.time.Duration;
import java.util.Random;

public class Level_03_PageObject extends BasePage {
    private WebDriver driver;
    private String projectPath = System.getProperty("user.dir");
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LoginPageObject loginPage;
    private CustomerPageObject customerPage;
    private String emailAddress = getEmailRandom();

    @BeforeClass
    public void beforeClass() {
        System.setProperty("user.dir", projectPath + "\\browserDriver\\geckodriver.exe");
        driver = new FirefoxDriver();

        openPageUrl(driver, "https://demo.nopcommerce.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

        // mở 1 url ra nó ở page nào thì mình khởi tạo page đó lên  ===>> new nó len
        // tu page nay chuyen qua page kia thì ta cần khởi tạo page đó lên
        homePage = new HomePageObject(driver);
    }

    @Test
    public void User_01_Register_Empty_Data() throws InterruptedException {
        homePage.clickToRegisterLink();

        // Tu HomePage click vào registerLink nó sẽ mở ra trang Register page
        registerPage = new RegisterPageObject(driver);

        // luc nay viec viet ham la cua register khong phải cua Home, vi no da chuyen trang
        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getFirstNameErrorMessageText(), "First name is required.");
        Assert.assertEquals(registerPage.getLastNameErrorMessageText(), "Last name is required.");
        Assert.assertEquals(registerPage.getEmailErrorMessageText(), "Email is required.");
        Assert.assertEquals(registerPage.getPasswordErrorMessageText(), "Password is required.");
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMessageText(), "Password is required.");

    }

    @Test
    public void User_02_Register_Invalid_Email() throws InterruptedException {
        registerPage.clickToNopCommerceImg();

        // đang tu register page click vao logo thi no mo sang trang home lai
        homePage = new HomePageObject(driver);

        homePage.clickToRegisterLink();

        // Tu HomePage click vào registerLink nó sẽ mở ra trang Register page
        registerPage = new RegisterPageObject(driver);

        registerPage.enterToFirstNameTextBox("Automation");
        registerPage.enterToLasttNameTextBox("FC");
        registerPage.enterToEmailNameTextBox("john@kennedy@us");
        registerPage.enterToPasswordTextBox("123456789");
        registerPage.enterToConfirmPasswordTextBox("123456789");

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getEmailErrorMessageText(), "Wrong email");
    }

    @Test
    public void User_03_Register_Invalid_Password() {
        registerPage.clickToNopCommerceImg();

        // đang tu register page click vao logo thi no mo sang trang home lai
        homePage = new HomePageObject(driver);

        homePage.clickToRegisterLink();

        // Tu HomePage click vào registerLink nó sẽ mở ra trang Register page
        registerPage = new RegisterPageObject(driver);

        registerPage.enterToFirstNameTextBox("Automation");
        registerPage.enterToLasttNameTextBox("FC");
        registerPage.enterToEmailNameTextBox("Automation@gmail.com");
        registerPage.enterToPasswordTextBox("1234");
        registerPage.enterToConfirmPasswordTextBox("1234");

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getPasswordErrorMessageText(),
                "Password must meet the following rules:\n" + "must have at least 6 characters");

    }

    @Test
    public void User_04_Register_Incorrect_Confirm_Password() {
        registerPage.clickToNopCommerceImg();

        // đang tu register page click vao logo thi no mo sang trang home lai
        homePage = new HomePageObject(driver);

        homePage.clickToRegisterLink();

        // Tu HomePage click vào registerLink nó sẽ mở ra trang Register page
        registerPage = new RegisterPageObject(driver);

        registerPage.enterToFirstNameTextBox("Automation");
        registerPage.enterToLasttNameTextBox("FC");
        registerPage.enterToEmailNameTextBox("Automation@gmail.com");
        registerPage.enterToPasswordTextBox("123456789");
        registerPage.enterToConfirmPasswordTextBox("123456798");

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getConfirmPasswordErrorMessageText(),
                "The password and confirmation password do not match.");

    }

    @Test
    public void User_05_Register_Success() {
        registerPage.clickToNopCommerceImg();

        // đang tu register page click vao logo thi no mo sang trang home lai
        homePage = new HomePageObject(driver);

        homePage.clickToRegisterLink();

        // Tu HomePage click vào registerLink nó sẽ mở ra trang Register page
        registerPage = new RegisterPageObject(driver);

        registerPage.enterToFirstNameTextBox("Automation");
        registerPage.enterToLasttNameTextBox("FC");
        registerPage.enterToEmailNameTextBox(emailAddress);
        registerPage.enterToPasswordTextBox("123456789");
        registerPage.enterToConfirmPasswordTextBox("123456789");

        registerPage.clickToRegisterButton();

        // chay qua roi lay Text để verify với
        Assert.assertEquals(registerPage.getRegisterSuccessMessageText(),
                "Your registration completed");
    }

    @Test
    public void User_06_Register_Success() throws IllegalArgumentException {
        registerPage.clickToNopCommerceImg();
        // đang tu register page click vao logo thi no mo sang trang home lai
        homePage = new HomePageObject(driver);

        // Click login link
        homePage.clickToLoginLink();
        // Tu trang home click vao trang login link thi no se mo ra trang Login
        loginPage = new LoginPageObject(driver);

        // Input Email/Password
        loginPage.enterToEmailTextBox(emailAddress);
        loginPage.enterToPasswordTextBox("123456789");

        // Click login  button
        loginPage.clickToLoginButton();

        // Tu trang Login nhap data hợp lệ và click login button thì nó sẽ quay lại trang Home (Login thành công)
        homePage = new HomePageObject(driver); // cu chuyen trang la NEW trang đó lên

        // click my account link
        homePage.clickToMyAccountLink();

        // Tu trang Home --> click My account nó sẽ mở ra trang CustomerInfor
        // ---> new customerinfor
        customerPage = new CustomerPageObject(driver);

        // verify
        Assert.assertEquals(customerPage.getFirstNameTextBoxAttributeValue(), "Automation");
        Assert.assertEquals(customerPage.getLastNameTextBoxAttributeValue(), "FC");
        Assert.assertEquals(customerPage.getEmailAddressTextBoxAttributeValue(), emailAddress);
    }

    @AfterClass
    public void afterClass() {
        // driver.quit();
    }

    public String getEmailRandom() {
        Random rand = new Random();
        /*String emailAddress = "automation" + rand.nextInt(9999) + "@gmail.net";
        return emailAddress;*/
        return "automation" + rand.nextInt(9999) + "@gmail.net";
    }

}
