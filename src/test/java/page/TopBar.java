package page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;
import utilities.BasePage;
import utilities.Logs;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TopBar extends BasePage {

    final private Locator itemCounter;
    final private Locator burgerMenuButton;

    public TopBar(Page page) {
        super(page);
        this.itemCounter = page.getByTestId("shopping-cart-badge");
        this.burgerMenuButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Open Menu"));
    }

    @Override
    public void verifyPage() {

    }

    @Step("Verificando la cantidad de items")
    public void verifyItemCount(int expectedCount) {
        Logs.info("Verificando la cantidad de items");
        assertThat(itemCounter).hasText(String.valueOf(expectedCount));
    }

    @Step("Haciendo click en el burger menu button")
    public void clickBurgerMenuButton() {
        Logs.info("Haciendo click en el burger menu button");
        burgerMenuButton.click();
    }

}
