package pageObjects;

import commons.BasePage;
import org.bouncycastle.jcajce.provider.symmetric.ARC4;
import org.openqa.selenium.WebDriver;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage {
    WebDriver driver;
    public RegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToRegisterButton() {
        waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
        clickToElement(driver,RegisterPageUI.REGISTER_BUTTON);
    }

    public String getFirstNameErrorMessageText() {
        waitForElementVisible(driver,RegisterPageUI.FIRSTNAME_ERROR_MSG);
        return getElementText(driver,RegisterPageUI.FIRSTNAME_ERROR_MSG);
    }


    public String  getLastNameErrorMessageText() {
        waitForElementVisible(driver,RegisterPageUI.LASNAME_ERROR_MSG);
        return getElementText(driver,RegisterPageUI.LASNAME_ERROR_MSG);
    }


    public String getEmailErrorMessageText() {
        waitForElementVisible(driver,RegisterPageUI.EMAIL_ERROR_MSG);
        return getElementText(driver,RegisterPageUI.EMAIL_ERROR_MSG);
    }


    public String  getPasswordErrorMessageText() {
        waitForElementVisible(driver,RegisterPageUI.PASSWORD_ERROR_MSG);
        return getElementText(driver,RegisterPageUI.PASSWORD_ERROR_MSG);
    }


    public String getConfirmPasswordErrorMessageText() {
        waitForElementVisible(driver,RegisterPageUI.CONFIRM_PASSWORD_ERROR_MSG);
        return getElementText(driver,RegisterPageUI.CONFIRM_PASSWORD_ERROR_MSG);
    }

    public void clickToNopCommerceImg() {
        waitForElementClickable(driver,RegisterPageUI.NOP_COMMERCE_LOGO);
        clickToElement(driver,RegisterPageUI.NOP_COMMERCE_LOGO);
    }

    public void enterToFirstNameTextBox(String firstNameValue) {
        waitForElementVisible(driver,RegisterPageUI.FIRSTNAME_TEXTBOX);
        sendKeyToElement(driver,RegisterPageUI.FIRSTNAME_TEXTBOX,firstNameValue);
    }

    public void enterToLasttNameTextBox(String lastNameValue) {
        waitForElementVisible(driver,RegisterPageUI.LASTNAME_TEXTBOX);
        sendKeyToElement(driver,RegisterPageUI.LASTNAME_TEXTBOX,lastNameValue);
    }


    public void enterToEmailNameTextBox(String emaiAddresslValue) {
        waitForElementVisible(driver,RegisterPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver,RegisterPageUI.EMAIL_TEXTBOX,emaiAddresslValue);
    }

    public void enterToPasswordTextBox(String passwordValue) {
        waitForElementVisible(driver,RegisterPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver,RegisterPageUI.PASSWORD_TEXTBOX,passwordValue);
    }

    public void enterToConfirmPasswordTextBox(String passwordValue) {
        waitForElementVisible(driver,RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendKeyToElement(driver,RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX,passwordValue);
    }

    public String getRegisterSuccessMessageText() {
        waitForElementVisible(driver,RegisterPageUI.REGISTRATION_COMPLETED_MSG);
        return getElementText(driver,RegisterPageUI.REGISTRATION_COMPLETED_MSG);
    }
}
