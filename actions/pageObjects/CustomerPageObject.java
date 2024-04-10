package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.CustomerPageUI;

public class CustomerPageObject extends BasePage {
    WebDriver driver;
    public CustomerPageObject(WebDriver driver) {
        this.driver = driver;
    }



    public String  getFirstNameTextBoxAttributeValue() {
        waitForElementVisible(driver, CustomerPageUI.FIRSTNAME_TEXTBOX);
        return getElementAttribute(driver,CustomerPageUI.FIRSTNAME_TEXTBOX,"value");
    }

    public String getLastNameTextBoxAttributeValue() {
        waitForElementVisible(driver, CustomerPageUI.LASTNAME_TEXTBOX);
        return getElementAttribute(driver,CustomerPageUI.LASTNAME_TEXTBOX,"value");
    }

    public String getEmailAddressTextBoxAttributeValue() {
        waitForElementVisible(driver, CustomerPageUI.EMAIL_TEXTBOX);
        return getElementAttribute(driver,CustomerPageUI.EMAIL_TEXTBOX,"value");
    }
}
