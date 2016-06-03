package tests;

import org.junit.Rule;
import org.junit.Test;
import rules.CommonRule;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.text;

public class GoogleTest {

    @Rule
    public CommonRule commonRule = new CommonRule("https://google.com/");

    @Test
    public void googleFindSelenide() {
        $("[name='q']").val("\"Selenide\"").pressEnter();
        $("h3.r a").shouldHave(text("Selenide: лаконичные и стабильные UI тесты на Java"));
    }

    @Test
    public void googleFindAllure() {
        $("[name='q']").val("\"Yandex Allure\"").pressEnter();
        $("h3.r a").shouldHave(text("Allure | Test report and framework for writing self-documented tests"));
    }

}
