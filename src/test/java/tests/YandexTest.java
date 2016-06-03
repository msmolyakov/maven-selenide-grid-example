package tests;

import org.junit.Rule;
import org.junit.Test;
import rules.CommonRule;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class YandexTest {

    @Rule
    public CommonRule commonRule = new CommonRule("https://ya.ru/");

    @Test
    public void yandexFindSelenide() {
        $("#text").val("\"Selenide\"").pressEnter();
        $(".serp-item__title-link").shouldHave(text("Selenide: лаконичные и стабильные UI тесты на Java"));
    }

    @Test
    public void yandexFindAllure() {
        $("#text").val("\"Yandex Allure\"").pressEnter();
        $(".serp-item__title-link").shouldHave(text("Test report and framework for writing self-documented tests"));
    }

}
