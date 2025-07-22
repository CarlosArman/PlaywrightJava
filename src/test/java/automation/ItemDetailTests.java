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
    private LoginPage loginPage;
    private ShoppingPage shoppingPage;
    private ItemDetailPage itemDetailPage;


    @BeforeEach
    public void setUp(Page page) {
        shoppingPage = new ShoppingPage(page);
        itemDetailPage = new ItemDetailPage(page);
        commonFlows.goToItemDetail(page, 0);

    }

    @Test
    @Regression
    public void verificarPaginaDetalleItemTest(Page page) {
        itemDetailPage.verifyPage();
    }

    @Test
    @Regression
    public void backToProductTest(Page page) {
        itemDetailPage.clickBackToProductsButton();
        shoppingPage.verifyPage();
    }
}
