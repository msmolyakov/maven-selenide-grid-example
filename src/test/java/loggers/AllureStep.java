package loggers;

import org.junit.runner.Description;
import ru.yandex.qatools.allure.annotations.Step;

public class AllureStep {

    @Step("{0}")
    public static void logToAllure(String log, Description description, Exception e) {
        System.out.println(description + " : " + log + " : " + e);
    }

    @Step("{0}")
    public static void logToAllure(String log, Exception e) {
        System.out.println(log + " : " + e);
    }

    @Step("{0}")
    public static void logToAllure(String log, Description description) {
        System.out.println(description + " : " + log);
    }

    @Step("{0}")
    public static void logToAllure(String log) {
        System.out.println(log);
    }

}
