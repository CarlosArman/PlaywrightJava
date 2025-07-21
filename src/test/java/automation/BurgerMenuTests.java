package automation;

import annotations.Regression;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.BurgerMenu;
import pages.LoginPage;
import pages.TopBar;
import utilities.BaseTest;

public class BurgerMenuTests extends BaseTest {

    @BeforeEach
    public void setUp(Page page) {
        final var loginPage = new LoginPage(page);

        loginPage.fillForm("standard_user", "secret_sauce");

        final var topBar = new TopBar(page);
        topBar.clickBurgerMenuButton();
    }

    @Test
    @Regression
    public void verificarBurgerMenuTest(Page page) {
        final var burgerMenu = new BurgerMenu(page);
        burgerMenu.verifyPage();

    }

    @Test
    @Regression
    public void logoutTest(Page page) {
        final var burgerMenu = new BurgerMenu(page);
        burgerMenu.clickLogoutButton();
        final var loginPage = new LoginPage(page);
        loginPage.verifyPage();

    }

    @Test
    @Regression
    public void aboutButtonTest(Page page) {
        final var burgerMenu = new BurgerMenu(page);
        burgerMenu.verifyAboutLink("https://saucelabs.com/");
    }
}
