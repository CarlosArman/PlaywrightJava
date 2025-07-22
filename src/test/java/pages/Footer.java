package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;
import utilities.BasePage;
import utilities.Logs;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class Footer extends BasePage {
    private final Locator twitterButton;
    private final Locator facebookButton;
    private final Locator linkedInButton;
    private final Locator descriptionFooter;

    public Footer(Page page) {
        super(page);
        this.twitterButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Twitter"));
        this.facebookButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Facebook"));
        this.linkedInButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("LinkedIn"));
        this.descriptionFooter = page.getByTestId("footer-copy");
    }

    @Override
    public void verifyPage() {
    }

    @Step("Verificando los hipervinculos")
    public void verifyLinks(String twitterLink, String facebookLink, String linkedinLink) {
        Logs.info("Verificando los hipervinculos");

        assertAll(
                () -> assertThat(twitterButton).isVisible(),
                () -> assertThat(twitterButton).isEnabled(),
                () -> assertThat(twitterButton).hasAttribute("href", twitterLink),
                () -> assertThat(facebookButton).isVisible(),
                () -> assertThat(facebookButton).isEnabled(),
                () -> assertThat(facebookButton).hasAttribute("href", facebookLink),
                () -> assertThat(linkedInButton).isVisible(),
                () -> assertThat(linkedInButton).isEnabled(),
                () -> assertThat(linkedInButton).hasAttribute("href", linkedinLink)
        );
    }

    @Step("Verificando descripcion del footer")
    public void verifyDescription(String description) {
        Logs.info("Verificando descripcion del footer");
        assertAll(
                () -> assertThat(descriptionFooter).isVisible(),
                () -> assertThat(descriptionFooter).hasText(description)
        );
    }

}
