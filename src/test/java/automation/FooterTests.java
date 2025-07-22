package automation;

import annotations.Regression;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.Footer;
import utilities.BaseTest;

public class FooterTest extends BaseTest {
    private Footer footer;

    @BeforeEach
    public void setUp(Page page) {
        footer = new Footer(page);
        commonFlows.goToShoppingPage(page);
    }

    @Test
    @Regression
    public void redesSocialesTest() {
        footer.verifyPage();
        footer.verifyLinks("https://twitter.com/saucelabs", "https://www.facebook.com/saucelabs", "https://www.linkedin.com/company/sauce-labs/");
    }

    @Test
    @Regression
    public void descriptionTest() {
        footer.verifyDescription("© 2025 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy");
    }

}
