package automation;

import annotations.Regression;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.LoginPage;
import page.ShoppingPage;
import utilities.BaseTest;
import utilities.Logs;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

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
        Logs.info("Verificando la pagina de Item Detail");
        Assertions.assertAll(
                () -> assertThat(page.getByTestId("inventory-item-name")).isVisible(),
                () -> assertThat(page.getByTestId("inventory-item-desc")).isVisible(),
                () -> assertThat(page.getByTestId("inventory-item-price")).isVisible(),
                () -> assertThat(page.getByTestId("item-sauce-labs-backpack-img")).isVisible(),
                () -> assertThat(page.locator(".btn_inventory")).isVisible(),
                () -> assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Back to products"))).isVisible()

        );
    }

    @Test
    @Regression
    public void backToProductTest(Page page) {
        Logs.info("Haciendo click en Back to Products");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Back to products")).click();

        Logs.info("Verificando la pagina de Shopping");
        assertThat(page.getByText("Products")).isVisible();
    }
}
