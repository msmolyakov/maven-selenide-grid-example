package rules;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.WebDriverRunner;
import com.google.common.io.Files;
import org.junit.AssumptionViolatedException;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.Keys;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static loggers.AllureStep.logToAllure;

public class CommonRule extends TestWatcher {

    private String host = "";

    public CommonRule(String hostURL) {
        this.host = hostURL;
    }

    @Override
    protected void starting(Description description) {
        System.out.println("START " + description);

        System.setProperty("selenide.browser", "webdrivers.FirefoxCustomDriver");

        open(host);
        WebDriverRunner.getWebDriver().manage().window().fullscreen();
        ((FirefoxDriver)getWebDriver()).getKeyboard().pressKey(Keys.F11);
    }

    @Override
    protected void succeeded(Description description) {
        System.out.println("SUCCEEDED " + description);
    }

    @Override
    protected void failed(Throwable e, Description description) {
        File lastSelenideScreenshot = Screenshots.getLastScreenshot();
        if (lastSelenideScreenshot != null) {
            try {
                screenshot(Files.toByteArray(lastSelenideScreenshot));
            } catch (IOException ioe) {
                logToAllure("не удалось приложить скриншот", description, ioe);
            }
        }

        System.out.println("FAILED " + description + " : " + e);
    }

    @Override
    protected void finished(Description description) {
        System.out.println("FINISHED " + description);
    }

    @Override
    protected void skipped(AssumptionViolatedException e, Description description) {
        System.out.println("SKIPPED " + description + " : " + e);
    }

    @Attachment(value = "Скриншот в момент ошибки", type = "image/png")
    private static byte[] screenshot(byte[] dataForScreenshot) {
        return dataForScreenshot;
    }

}
