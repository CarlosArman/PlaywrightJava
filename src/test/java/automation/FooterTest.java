package automation;

import annotations.Regression;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.Footer;
import pages.LoginPage;
import utilities.BaseTest;

public class FooterTest extends BaseTest {

    @BeforeEach
    public void setUp(Page page) {
        final var loginPage = new LoginPage(page);
        loginPage.fillForm("standard_user", "secret_sauce");
    }

    @Test
    @Regression
    public void redesSocialesTest(Page page) {
        final var footer = new Footer(page);
        footer.verifyPage();
        footer.verifyLinks("https://twitter.com/saucelabs", "https://www.facebook.com/saucelabs", "https://www.linkedin.com/company/sauce-labs/");
    }

    @Test
    @Regression
    public void descriptionTest(Page page) {
        final var footer = new Footer(page);
        footer.verifyDescription("© 2025 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy");
    }


}
