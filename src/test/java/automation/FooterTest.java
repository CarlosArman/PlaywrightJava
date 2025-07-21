package automation;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.LoginPage;
import utilities.BaseTest;
import utilities.Logs;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class FooterTest extends BaseTest {

    @BeforeEach
    public void setUp(Page page) {
        final var loginPage = new LoginPage(page);
        loginPage.fillForm("standard_user", "secret_sauce");
    }

    @Test
    public void redesSocialesTest(Page page) {
        Logs.info("Verificando la pagina de redes sociales");
        final var twitterButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Twitter"));
        final var facebookButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Facebook"));
        final var linkedInButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("LinkedIn"));

        assertAll(
                () -> assertThat(twitterButton).isVisible(),
                () -> assertThat(twitterButton).isEnabled(),
                () -> assertThat(twitterButton).hasAttribute("href", "https://twitter.com/saucelabs"),
                () -> assertThat(facebookButton).isVisible(),
                () -> assertThat(facebookButton).isEnabled(),
                () -> assertThat(facebookButton).hasAttribute("href", "https://www.facebook.com/saucelabs"),
                () -> assertThat(linkedInButton).isVisible(),
                () -> assertThat(linkedInButton).isEnabled(),
                () -> assertThat(linkedInButton).hasAttribute("href", "https://www.linkedin.com/company/sauce-labs/")
        );
    }


}
