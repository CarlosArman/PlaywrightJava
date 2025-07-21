package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import utilities.BasePage;
import utilities.Logs;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ShoppingPage extends BasePage {

    final private Locator title;
    final private Locator inventoryList;
    final private Locator select;
    final private Locator itemPrice;
    final private Locator itemName;
    final private Locator itemImage;
    final private Locator addToCartButton;

    private Locator getItemName(String state, String itemName) {
        String newItemName = String.format("%s %s", state, itemName)
                .toLowerCase()
                .replace(" ", "-");
        return page.getByTestId(newItemName);
    }

    public ShoppingPage(Page page) {
        super(page);
        this.title = page.getByText("Products");
        this.inventoryList = page.getByTestId("inventory-list");
        this.select = page.locator(".product_sort_container");
        this.itemPrice = page.getByTestId("inventory-item-price");
        this.itemName = page.getByTestId("inventory-item-name");
        this.itemImage = page.locator(".inventory_item_img");
        this.addToCartButton = page.locator(".btn_inventory");
    }

    @Override
    @Step("Verificando el pagina de Shopping")
    public void verifyPage() {
        Logs.info("Verificando el pagina de Shopping");
        assertAll(
                () -> assertThat(title).isVisible(),
                () -> assertThat(inventoryList).isVisible(),
                () -> assertThat(select).isVisible()
        );
    }


    @Step("Verificando los textos de Add to Cart & Remove")
    public void verifyAddRemove(String itemName) {
        Logs.info("Verificando el texto Add to Cart");
        final var addToCartButton = getItemName("Add to cart", itemName);
        assertThat(addToCartButton).hasText("Add to cart");

        Logs.info("Hago click en el boton add to cart");
        addToCartButton.click();

        Logs.info("Verificando el texto Remove");
        assertThat(getItemName("Remove", itemName)).hasText("Remove");
    }

    @Step("Verificando precio")
    public void verifyItemPrice(int index, String expected) {
        Logs.info("Verificando precio de " + expected);
        assertThat(itemPrice.nth(index)).hasText(expected);
    }

    @Step("Haciendo click en todos los botones Add to Cart")
    public void clickAddToCartButton() {
        Logs.info("Haciendo click en todos los botones Add to Cart");

        final var list = addToCartButton.all();
        list.forEach(Locator::click);
    }

    @Step("Ordenar los items")
    public void orderItems(String value) {
        Logs.info("Ordenar los items");
        select.selectOption(value);
    }

    @Step("Verificando el primer y ultimo precio")
    public void verifyItemPrice(String first, String last) {
        Logs.info("Verificando el primer y ultimo precio");
        assertAll(
                () -> assertThat(itemPrice.first()).hasText(first),
                () -> assertThat(itemPrice.last()).hasText(last)
        );
    }


    @Step("Verificando el primer y ultimo nombre")
    public void verifyItemName(String first, String last) {
        Logs.info("Verificando el primer y ultimo nombre");

        assertAll(
                () -> assertThat(itemName.first()).hasText(first),
                () -> assertThat(itemName.last()).hasText(last)
        );
    }

    @Step("Haciendo click en la imagen segun su index")
    public void clickImageByIndex(int index) {
        Logs.info("Haciendo click en la imagen segun su index");
        itemImage.nth(index).click();
    }

}
