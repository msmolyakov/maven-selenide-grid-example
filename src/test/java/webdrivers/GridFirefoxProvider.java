package webdrivers;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class GridFirefoxProvider implements WebDriverProvider {

    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        try {
            return new RemoteWebDriver(new URL("http://172.16.101.77:4444/wd/hub"), DesiredCapabilities.firefox());
        }
        catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
