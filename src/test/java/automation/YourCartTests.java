package automation;

import annotations.Regression;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.ShoppingPage;
import pages.YourCartPage;
import utilities.BaseTest;

public class YourCartTests extends BaseTest {
    private YourCartPage yourCartPage;
    private ShoppingPage shoppingPage;

    @BeforeEach
    public void setUp(Page page) {
        yourCartPage = new YourCartPage(page);
        shoppingPage = new ShoppingPage(page);
        commonFlows.goToYourCart(page);
    }

    @Test
    @Regression
    public void verificarPaginaTest() {
        yourCartPage.verifyPage();
    }

    @Test
    @Regression
    public void regresarShoppingTest() {
        yourCartPage.clickContinueShoppingButton();
        shoppingPage.verifyPage();
    }
}
