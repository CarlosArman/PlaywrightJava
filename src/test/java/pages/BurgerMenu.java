package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;
import utilities.BasePage;
import utilities.Logs;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BurgerMenu extends BasePage {

    final private Locator allItemsButton;
    final private Locator aboutButton;
    final private Locator logoutButton;
    final private Locator ResetAppStateButton;

    public BurgerMenu(Page page) {
        super(page);
        this.allItemsButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("All Items"));
        this.aboutButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("About"));
        this.logoutButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Logout"));
        this.ResetAppStateButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Reset App State"));
    }

    @Override
    @Step("Verificando los elementos del Burger Menu")
    public void verifyPage() {
        Logs.info("Verificando los elementos del Burger Menu");
        assertAll(
                () -> assertThat(allItemsButton).isVisible(),
                () -> assertThat(aboutButton).isVisible(),
                () -> assertThat(logoutButton).isVisible(),
                () -> assertThat(ResetAppStateButton).isVisible()
        );
    }

    @Step("Haciendo click en logout")
    public void clickLogoutButton() {
        Logs.info("Haciendo click en logout");
        logoutButton.click();
    }

    @Step("Verificando el hipervinculo about")
    public void verifyAboutLink(String aboutLink) {
        Logs.info("Verificando el hipervinculo about");
        assertAll(
                () -> assertThat(aboutButton).isVisible(),
                () -> assertThat(aboutButton).isEnabled(),
                () -> assertThat(aboutButton).hasAttribute("href", aboutLink)
        );
    }

}
