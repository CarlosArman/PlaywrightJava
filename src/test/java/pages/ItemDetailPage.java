package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import utilities.BasePage;
import utilities.Logs;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ItemDetailPage extends BasePage {
    final private Locator itemName;
    final private Locator itemDescription;
    final private Locator itemPrice;
    final private Locator itemImage;
    final private Locator addToCartButton;
    final private Locator backToProductsButton;

    public ItemDetailPage(Page page) {
        super(page);
        this.itemName = page.getByTestId("inventory-item-name");
        this.itemDescription = page.getByTestId("inventory-item-desc");
        this.itemPrice = page.getByTestId("inventory-item-price");
        this.itemImage = page.getByTestId("item-sauce-labs-backpack-img");
        this.addToCartButton = page.locator(".btn_inventory");
        this.backToProductsButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Back to products"));
    }

    @Override
    @Step("Verificando la pagina de Item Detail")
    public void verifyPage() {
        Logs.info("Verificando la pagina de Item Detail");
        Assertions.assertAll(
                () -> assertThat(itemName).isVisible(),
                () -> assertThat(itemDescription).isVisible(),
                () -> assertThat(itemPrice).isVisible(),
                () -> assertThat(itemImage).isVisible(),
                () -> assertThat(addToCartButton).isVisible(),
                () -> assertThat(backToProductsButton).isVisible()
        );
    }

    @Step("Haciendo click en Back to Products")
    public void clickBackToProductsButton() {
        Logs.info("Haciendo click en Back to Products");
        backToProductsButton.click();
    }
}
