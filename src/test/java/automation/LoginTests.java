package automation;

import annotations.Regression;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import utilities.BaseTest;
import utilities.Logs;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginTests extends BaseTest {

    private LoginPage loginPage;

    @BeforeEach
    public void setUp(Page page) {
        loginPage = new LoginPage(page);
        commonFlows.goToLoginPage();
    }

    @Test
    @Regression
    public void mensajeErrorTest() {
        Logs.info("Test mensaje error");
        loginPage.fillForm("locked_out_user", "secret_sauce");
        loginPage.verifyErrorMessage("Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    @Regression
    public void verificarPaginaLoginTest() {
        Logs.info("Verificando la pagina de Login");
        loginPage.verifyPage();
    }

    @Test
    @Regression
    public void loginSuccessTest(Page page) {
        Logs.info("Login Success Test");
        loginPage.fillForm("standard_user", "secret_sauce");
        Logs.info("Verificando la pagina de Shopping");
        Assertions.assertAll(
                () -> assertThat(page.getByText("Products")).isVisible(),
                () -> assertThat(page.getByTestId("inventory-list")).isVisible(),
                () -> assertThat(page.locator(".product_sort_container")).isVisible()
        );

    }
}
