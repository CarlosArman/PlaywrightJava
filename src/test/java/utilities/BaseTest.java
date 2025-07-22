package utilities;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

@UsePlaywright(CustomOptions.class)
public class BaseTest {
    protected final CommonFlows commonFlows = new CommonFlows();

    @BeforeEach
    public void setUpMaster(Page page) {
        Logs.info("In setUpMaster");
        PageProvider.setPage(page);
    }

    @AfterEach
    public void tearDownMaster() {
        Logs.info("In tearDown");
    }
}
