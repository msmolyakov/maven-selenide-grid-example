package webdrivers;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static loggers.AllureStep.logToAllure;
import static org.openqa.selenium.firefox.FirefoxDriver.PROFILE;

public class FirefoxCustomDriver implements WebDriverProvider {

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {

        FirefoxProfile firefoxProfile = new FirefoxProfile();

        firefoxProfile.setPreference("browser.fullscreen.autohide",true);
        firefoxProfile.setPreference("browser.fullscreen.animateUp",0);

        desiredCapabilities.setCapability(PROFILE, firefoxProfile);

        String hubUrl = "http://172.16.101.77:4444/wd/hub";
        try {
            return new RemoteWebDriver(new URL(hubUrl), desiredCapabilities);
        } catch (MalformedURLException e) {
            logToAllure("ошибка при обработке ссылки " + hubUrl, e);
            return new RemoteWebDriver(desiredCapabilities);
        }
    }

}
