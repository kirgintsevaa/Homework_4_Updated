import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {
    WebDriver driver;

    @AfterEach //аннотация выполнения после каждого метода, отключаем драйвер
    public void close() {
        if (driver != null)
            driver.quit();

    }
}