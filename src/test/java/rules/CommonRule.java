package rules;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.WebDriverProvider;
import com.codeborne.selenide.WebDriverRunner;
import com.google.common.io.Files;
import org.junit.AssumptionViolatedException;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Selenide.open;
import static loggers.AllureStep.logToAllure;

public class CommonRule extends TestWatcher {

    private String host = "";

    public CommonRule(String hostURL) {
        this.host = hostURL;
    }

    @Override
    protected void starting(Description description) {
        System.out.println("START " + description);

        Configuration.browser = MyGridFirefoxProvider.class.getName();

        open(host);
    }

    public static class MyGridFirefoxProvider implements WebDriverProvider {

        @Override
        public WebDriver createDriver(DesiredCapabilities capabilities) {
            try {
                return new RemoteWebDriver(new URL("http://172.16.101.126:4444/wd/hub"), DesiredCapabilities.firefox());
            }
            catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    protected void succeeded(Description description) {
        System.out.println("SUCCEEDED " + description);
    }

    @Override
    protected void failed(Throwable e, Description description) {
        File lastSelenideScreenshot = Screenshots.getScreenShotAsFile();
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
        WebDriverRunner.closeWebDriver();
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
