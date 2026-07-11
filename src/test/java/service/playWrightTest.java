package service;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.Test;

public class playWrightTest {
    @Test
    public void playwrightTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setChannel("msedge")
                        .setHeadless(false)
        );
        BrowserContext context = browser.newContext();

        Page page = context.newPage();
        page.navigate("https://playwright.dev/java/");
        page.waitForTimeout(2000);

        Page page2 = context.newPage();
        page2.navigate("https://github.com");

        browser.close();

    }
}
