package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;
import utilities.BasePage;
import utilities.Logs;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class YourCartPage extends BasePage {
    private final Locator checkoutButton;
    private final Locator continueShoppingButton;
    private final Locator title;
    private final Locator itemContainer;

    public YourCartPage(Page page) {
        super(page);
        this.checkoutButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Checkout"));
        this.continueShoppingButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Go back Continue Shopping"));
        this.title = page.getByText("Your Cart");
        this.itemContainer = page.getByTestId("cart-list");
    }

    @Override
    @Step("Verificando la pagina Your Cart")
    public void verifyPage() {
        Logs.info("Verificando la pagina Your Cart");
        assertAll(
                () -> assertThat(checkoutButton).isVisible(),
                () -> assertThat(continueShoppingButton).isVisible(),
                () -> assertThat(title).isVisible(),
                () -> assertThat(itemContainer).isVisible()
        );

    }

    @Step("Haciendo click en el boton de checkout")
    public void clickCheckoutButton() {
        Logs.info("Haciendo click en el boton checkout");
        checkoutButton.click();
    }

    public void clickContinueShoppingButton() {
        continueShoppingButton.click();
    }
}
