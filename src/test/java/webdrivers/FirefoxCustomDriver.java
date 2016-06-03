package webdrivers;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.openqa.selenium.firefox.FirefoxDriver.PROFILE;

public class FirefoxCustomDriver implements WebDriverProvider {

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {

        FirefoxProfile firefoxProfile = new FirefoxProfile();

        firefoxProfile.setPreference("browser.fullscreen.autohide",true);
        firefoxProfile.setPreference("browser.fullscreen.animateUp",0);
        firefoxProfile.setPreference("network.negotiate-auth.trusted-uris", "http://, https://");
        firefoxProfile.setPreference("network.negotiate-auth.delegation-uris", "http://, https://");

        desiredCapabilities.setCapability(PROFILE, firefoxProfile);

        return new FirefoxDriver(desiredCapabilities);
    }

}
