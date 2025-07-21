package automation;

import annotations.Regression;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.ItemDetailPage;
import pages.LoginPage;
import pages.ShoppingPage;
import utilities.BaseTest;

public class ItemDetailTests extends BaseTest {

    @BeforeEach
    public void setUp(Page page) {
        final var loginPage = new LoginPage(page);
        loginPage.fillForm("standard_user", "secret_sauce");
        final var shoppingPage = new ShoppingPage(page);
        shoppingPage.clickImageByIndex(0);
    }

    @Test
    @Regression
    public void verificarPaginaDetalleItemTest(Page page) {
        final var itemDetailPage = new ItemDetailPage(page);
        itemDetailPage.verifyPage();
    }

    @Test
    @Regression
    public void backToProductTest(Page page) {
        final var itemDetailPage = new ItemDetailPage(page);
        itemDetailPage.clickBackToProductsButton();
        final var shoppingPage = new ShoppingPage(page);
        shoppingPage.verifyPage();
    }
}
