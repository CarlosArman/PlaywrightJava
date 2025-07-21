package automation;

import annotations.Regression;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import utilities.BaseTest;
import utilities.Logs;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginTests extends BaseTest {

    @Test
    @Regression
    public void mensajeErrorTest(Page page) {
        Logs.info("Test mensaje error");
        final var loginPage = new LoginPage(page);
        loginPage.fillForm("locked_out_user", "secret_sauce");
        loginPage.verifyErrorMessage("Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    @Regression
    public void verificarPaginaLoginTest(Page page) {
        Logs.info("Verificando la pagina de Login");
        final var loginPage = new LoginPage(page);
        loginPage.verifyPage();
    }

    @Test
    @Regression
    public void loginSuccessTest(Page page) {
        Logs.info("Login Success Test");
        final var loginPage = new LoginPage(page);
        loginPage.fillForm("standard_user", "secret_sauce");
        Logs.info("Verificando la pagina de Shopping");
        Assertions.assertAll(
                () -> assertThat(page.getByText("Products")).isVisible(),
                () -> assertThat(page.getByTestId("inventory-list")).isVisible(),
                () -> assertThat(page.locator(".product_sort_container")).isVisible()
        );

    }
}
