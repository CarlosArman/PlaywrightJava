package automation;

import com.microsoft.playwright.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.YourCartPage;
import pages.YourInformationPage;
import utilities.BaseTest;

public class YourInformationTest extends BaseTest {
    private YourInformationPage yourInformationPage;
    private YourCartPage yourCartPage;

    @BeforeEach
    public void setUp(Page page) {
        yourInformationPage = new YourInformationPage(page);
        yourCartPage = new YourCartPage(page);
        commonFlows.goToYourInformation(page);
    }

    @Test
    public void verificarPaginadaTest() {
        yourInformationPage.verifyPage();
    }

    @Test
    public void backToYourCartTest() {
        yourInformationPage.clickCancelButton();
        yourCartPage.verifyPage();
    }

    @Test
    public void faltaFirstNameTest() {
        yourInformationPage.fillForm("", "lastname", "zipcode");
        yourInformationPage.verifyErrorMessage("Error: First Name is required");
    }

    @Test
    public void faltaLastNameTest() {
        yourInformationPage.fillForm("firstname", "", "zipcode");
        yourInformationPage.verifyErrorMessage("Error: Last Name is required");
    }

    @Test
    public void faltaZipCodeTest() {
        yourInformationPage.fillForm("firstname", "lastname", "");
        yourInformationPage.verifyErrorMessage("Error: Postal Code is required");
    }
}
