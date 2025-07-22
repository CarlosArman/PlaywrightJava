package automation;

import annotations.Regression;
import com.microsoft.playwright.Page;
import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.YourCartPage;
import pages.YourInformationPage;
import utilities.BaseTest;
import utilities.Logs;

public class YourInformationTests extends BaseTest {
    private YourInformationPage yourInformationPage;
    private YourCartPage yourCartPage;
    private User user;

    @BeforeEach
    public void setUp(Page page) {
        yourInformationPage = new YourInformationPage(page);
        yourCartPage = new YourCartPage(page);
        user = new User();
        Logs.debug("Escribiendo el user: " + user);
        commonFlows.goToYourInformation(page);
    }

    @Test
    @Regression
    public void verificarPaginadaTest() {
        yourInformationPage.verifyPage();
    }

    @Test
    @Regression
    public void backToYourCartTest() {
        yourInformationPage.clickCancelButton();
        yourCartPage.verifyPage();
    }

    @Test
    public void faltaFirstNameTest() {
        yourInformationPage.fillForm("", user.getLastname(), user.getZipcode());
        yourInformationPage.verifyErrorMessage("Error: First Name is required");
    }

    @Test
    public void faltaLastNameTest() {
        yourInformationPage.fillForm(user.getFirstname(), "", user.getZipcode());
        yourInformationPage.verifyErrorMessage("Error: Last Name is required");
    }

    @Test
    public void faltaZipCodeTest() {
        yourInformationPage.fillForm(user.getFirstname(), user.getLastname(), "");
        yourInformationPage.verifyErrorMessage("Error: Postal Code is required");
    }

    @ParameterizedTest
    @MethodSource("data.CustomData#getData")
    @Regression
    public void errorMessageTest(String firstName, String lastName, String zipCode, String mensajeError) {
        yourInformationPage.fillForm(firstName, lastName, zipCode);
        yourInformationPage.verifyErrorMessage(mensajeError);
    }
}
