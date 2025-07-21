package utilities;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;

import java.util.List;

public class CustomOptions implements OptionsFactory {

    @Override
    public Options getOptions() {
        return new Options()
                .setLaunchOptions(createLaunchOptions())
                .setContextOptions(createContextOptions())
                .setHeadless(false)
                .setBrowserName("chromium") // chromium, firefox , webkit
                .setChannel("chrome")
                .setTestIdAttribute("data-test")// chrome msedge
                .setBaseUrl("https://www.saucedemo.com");
    }

    //opciones del browser
    private BrowserType.LaunchOptions createLaunchOptions() {
        final var arguments = List.of("--start-maximized");
        return new BrowserType.LaunchOptions()
                //setSlowMo(1500)
                .setArgs(arguments);
    }

    //opciones del browser context
    private Browser.NewContextOptions createContextOptions() {
        return new Browser.NewContextOptions().setViewportSize(null);
    }
}
