package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;
import utilities.BasePage;
import utilities.Logs;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class YourInformationPage extends BasePage {

    private final Locator firstNameInput;
    private final Locator lastNameInput;
    private final Locator zipCodeInput;
    private final Locator continueButton;
    private final Locator cancelButton;
    private final Locator title;
    private final Locator errorMessage;

    public YourInformationPage(Page page) {
        super(page);
        this.firstNameInput = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First Name"));
        this.lastNameInput = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Last Name"));
        this.zipCodeInput = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Zip/Postal Code"));
        this.continueButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue"));
        this.cancelButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Go back Cancel"));
        this.errorMessage = page.getByTestId("error");
        this.title = page.getByText("Checkout: Your Information");

    }

    @Override
    @Step("Verificando pagina de Your Information")
    public void verifyPage() {
        assertAll(
                () -> assertThat(firstNameInput).isVisible(),
                () -> assertThat(lastNameInput).isVisible(),
                () -> assertThat(zipCodeInput).isVisible(),
                () -> assertThat(continueButton).isVisible(),
                () -> assertThat(cancelButton).isVisible(),
                () -> assertThat(title).isVisible()
        );
    }

    @Step("Rellenando el formulario")
    public void fillForm(String firstName, String lastName, String zipCode) {

        Logs.info("Escribiendo el firstName: " + firstName);
        if (!firstName.isEmpty()) {
            firstNameInput.fill(firstName);
        }

        Logs.info("Escribiendo el lastName: " + lastName);
        if (!lastName.isEmpty()) {
            lastNameInput.fill(lastName);
        }

        Logs.info("Escribiendo el zipCode: " + zipCode);
        if (!zipCode.isEmpty()) {
            zipCodeInput.fill(zipCode);
        }

        Logs.info("Haciendo click en el boton continue");
        continueButton.click();
    }

    @Step("Verificando mensaje de error")
    public void verifyErrorMessage(String message) {
        Logs.info("Verificando mensaje de error: " + message);
        assertAll(
                () -> assertThat(errorMessage).hasText(message),
                () -> assertThat(errorMessage).isVisible()
        );
    }

    @Step("Haciendo click en el boton cancel")
    public void clickCancelButton() {
        Logs.info("Haciendo click en el boton cancel");
        cancelButton.click();
    }

}
