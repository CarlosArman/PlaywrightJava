package automation;

import annotations.Regression;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.LoginPage;
import page.TopBar;
import utilities.BaseTest;
import utilities.Logs;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BurgerMenuTests extends BaseTest {

    @BeforeEach
    public void setUp(Page page) {
        final var loginPage = new LoginPage(page);

        loginPage.fillForm("standard_user", "secret_sauce");

        final var topBar = new TopBar(page);
        topBar.clickBurgerMenuButton();
    }

    @Test
    @Regression
    public void verificarBurgerMenuTest(Page page) {
        Logs.info("Verificando los elementos del burger menu");
        assertAll(
                () -> assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("All Items"))).isVisible(),
                () -> assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("About"))).isVisible(),
                () -> assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Logout"))).isVisible(),
                () -> assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Reset App State"))).isVisible()

        );
    }

    @Test
    public void logoutTest(Page page) {
        Logs.info("Logout Test");
        Logs.info("Haciendo click en logout button");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Logout")).click();

        Logs.info("Verifico que este en la pagina de Login");
        assertThat(page.getByText("Swag Labs")).isVisible();
    }

    @Test
    public void aboutButtonTest(Page page) {
        Logs.info("Verificando el hipervinculo about");
        final var aboutButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("About"));

        assertAll(
                () -> assertThat(aboutButton).isVisible(),
                () -> assertThat(aboutButton).isEnabled(),
                () -> assertThat(aboutButton).hasAttribute("href", "https://saucelabs.com/")
        );
    }
}
