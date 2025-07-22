package automation;

import annotations.Regression;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.ShoppingPage;
import pages.TopBar;
import utilities.BaseTest;
import utilities.Logs;

public class ShoppingTests extends BaseTest {
    private ShoppingPage shoppingPage;
    private TopBar topBar;

    @BeforeEach
    public void setUp(Page page) {
        shoppingPage = new ShoppingPage(page);
        topBar = new TopBar(page);
        commonFlows.goToShoppingPage(page);
    }

    @Test
    @Regression
    public void verificarPaginaShoppingTest() {
        shoppingPage.verifyPage();
    }

    @Test
    @Regression
    public void botonRemoveTest() {
        shoppingPage.verifyAddRemove("Sauce Labs Backpack");
    }

    @Test
    @Regression
    public void precioTest() {
        shoppingPage.verifyItemPrice(2, "$15.99");
    }

    @Test
    @Regression
    public void addToCartTest() {
        shoppingPage.clickAddToCartButton();
        topBar.verifyItemCount(6);
    }

    @Test
    @Regression
    public void ordenarMenorMayorPrecioTest() {
        Logs.info("Ordenando los precios de menor a mayor ");
        shoppingPage.orderItems("lohi");
        shoppingPage.verifyItemPrice("$7.99", "$49.99");
    }

    @Test
    @Regression
    public void ordenarMayorMenorPrecioTest() {
        Logs.info("Ordenando los nombres de mayor a menor");
        shoppingPage.orderItems("hilo");
        //page.getByTestId("product-sort-container").selectOption(new SelectOption().setIndex(3));
        shoppingPage.verifyItemPrice("$49.99", "$7.99");
    }

    @Test
    @Regression
    public void ordenarMayorMenorNombreTest() {
        Logs.info("Ordenando los nombres de mayor a menor");
        shoppingPage.orderItems("za");
        //page.getByTestId("product-sort-container").selectOption(new SelectOption().setLabel("Name (Z to A)"));
        shoppingPage.verifyItemName("Test.allTheThings() T-Shirt (Red)", "Sauce Labs Backpack");
    }

}
