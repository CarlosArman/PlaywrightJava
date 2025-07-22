package automation;

import annotations.Regression;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.BurgerMenu;
import pages.LoginPage;
import utilities.BaseTest;

public class BurgerMenuTests extends BaseTest {
    private LoginPage loginPage;
    private BurgerMenu burgerMenu;

    @BeforeEach
    public void setUp(Page page) {
        loginPage = new LoginPage(page);
        burgerMenu = new BurgerMenu(page);
        commonFlows.openBurgerMenu(page);
    }

    @Test
    @Regression
    public void verificarBurgerMenuTest() {
        burgerMenu.verifyPage();
    }

    @Test
    @Regression
    public void logoutTest() {
        burgerMenu.clickLogoutButton();
        loginPage.verifyPage();
    }

    @Test
    @Regression
    public void aboutButtonTest() {
        burgerMenu.verifyAboutLink("https://saucelabs.com/");
    }
}
