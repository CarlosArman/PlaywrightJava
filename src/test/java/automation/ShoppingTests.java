package automation;

import annotations.Regression;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.ShoppingPage;
import pages.TopBar;
import utilities.BaseTest;
import utilities.Logs;

public class ShoppingTests extends BaseTest {

    @BeforeEach
    public void setUp(Page page) {
        final var loginPage = new LoginPage(page);
        loginPage.fillForm("standard_user", "secret_sauce");
    }

    @Test
    @Regression
    public void verificarPaginaShoppingTest(Page page) {
        final var shoppingPage = new ShoppingPage(page);
        shoppingPage.verifyPage();
    }

    @Test
    @Regression
    public void botonRemoveTest(Page page) {
        final var shoppingPage = new ShoppingPage(page);
        shoppingPage.verifyAddRemove("Sauce Labs Backpack");
    }

    @Test
    @Regression
    public void precioTest(Page page) {
        final var shoppingPage = new ShoppingPage(page);
        shoppingPage.verifyItemPrice(2, "$15.99");
    }

    @Test
    @Regression
    public void addToCartTest(Page page) {
        final var shoppingPage = new ShoppingPage(page);
        shoppingPage.clickAddToCartButton();
        final var topBar = new TopBar(page);
        topBar.verifyItemCount(6);
    }

    @Test
    @Regression
    public void ordenarMenorMayorPrecioTest(Page page) {
        Logs.info("Ordenando los precios de menor a mayor ");
        final var shoppingPage = new ShoppingPage(page);
        shoppingPage.orderItems("lohi");
        shoppingPage.verifyItemPrice("$7.99", "$49.99");
    }

    @Test
    @Regression
    public void ordenarMayorMenorPrecioTest(Page page) {
        Logs.info("Ordenando los nombres de mayor a menor");
        final var shoppingPage = new ShoppingPage(page);
        shoppingPage.orderItems("hilo");
        //page.getByTestId("product-sort-container").selectOption(new SelectOption().setIndex(3));
        shoppingPage.verifyItemPrice("$49.99", "$7.99");
    }

    @Test
    @Regression
    public void ordenarMayorMenorNombreTest(Page page) {
        Logs.info("Ordenando los nombres de mayor a menor");
        final var shoppingPage = new ShoppingPage(page);
        shoppingPage.orderItems("za");
        //page.getByTestId("product-sort-container").selectOption(new SelectOption().setLabel("Name (Z to A)"));
        shoppingPage.verifyItemName("Test.allTheThings() T-Shirt (Red)", "Sauce Labs Backpack");
    }

}
