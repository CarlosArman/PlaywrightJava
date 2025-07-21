package page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;
import utilities.BasePage;
import utilities.Logs;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class LoginPage extends BasePage {

    private final Locator usernameInput;
    private final Locator passwordInput;
    private final Locator loginButton;
    private final Locator errorMessage;
    private final Locator title;

    public LoginPage(Page page) {
        super(page);
        this.usernameInput = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username"));
        this.passwordInput = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password"));
        this.loginButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
        this.errorMessage = page.getByTestId("error");
        this.title = page.getByText("Swag Labs");
    }

    @Override
    public void verifyPage() {
        Logs.info("Verificando la pagina de Login");
        assertAll(
                () -> assertThat(usernameInput).isVisible(),
                () -> assertThat(passwordInput).isVisible(),
                () -> assertThat(loginButton).isVisible(),
                () -> assertThat(title).isVisible()
        );
    }

    @Step("Rellenando el formulario")
    public void fillForm(String username, String password) {
        Logs.info("Rellenando el formulario");
        Logs.info("Escribiendo el username: " + username);
        usernameInput.fill(username);
        Logs.info("Escribiendo el password: " + password);
        passwordInput.fill(password);
        Logs.info("Haciendo click en el boton login");
        loginButton.click();
    }

    @Step("Verificando el mensaje de error")
    public void verifyErrorMessage(String message) {
        Logs.info("Verificando el mensaje de error");
        assertAll(
                () -> assertThat(errorMessage).isVisible(),
                () -> assertThat(errorMessage).hasText(message)
        );
    }

}
