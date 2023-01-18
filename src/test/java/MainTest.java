import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainTest {
    private final String login = "nowaj54360@lubde.com";
    private final String password = "Nowaj54360@lubde.com";
    private WebDriver driver;

    @AfterEach
    public void close() {
        if (driver != null)
            driver.quit();
    }

    @Test
    public void firstTask() {

//1. Открыть Chrome в headless режиме
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
//2. Перейти на https://duckduckgo.com/
        driver.get("https://duckduckgo.com/");
        //3. В поисковую строку ввести ОТУС
        enterString();
//4. Проверить что в поисковой выдаче первый результат Онлайн‑курсы для профессионалов, дистанционное обучение
        checkResult();
    }

    @Test
    public void secondTask() {

//1. Открыть Chrome в режиме киоска
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
//2. Перейти на https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818
        driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");
        //3. Нажать на любую картинку
        driver.findElement(By.cssSelector(".content-overlay")).click();
        //4. Проверить что картинка открылась в модальном окне
        driver.findElement(By.cssSelector(".pp_hoverContainer")).isDisplayed();
    }

    @Test
    public void thirdTask() {
        Logger logger = LogManager.getLogger(String.valueOf(MainTest.class));
//1. Открыть Chrome в режиме полного экрана
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
//2. Перейти на https://otus.ru
        driver.get("https://otus.ru/");
        //3. Авторизоваться под каким-нибудь тестовым пользователем(можно создать нового)
        loginInOtus();
//4. Вывести в лог все cookie
        logger.info(driver.manage().getCookies().toString());
    }

    private void enterString() {
        String find = "ОТУС";
        driver.findElement(By.cssSelector("#search_form_input_homepage")).sendKeys(find);
        driver.findElement(By.cssSelector("#search_button_homepage")).click();
    }

    private void checkResult() {
        String expectedResult = "Онлайн‑курсы для профессионалов, дистанционное обучение современным ...";
        String actualResult = driver.findElement(By.cssSelector("#r1-0>div+div")).getText();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    private void loginInOtus() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.findElement(By.cssSelector("button.js-cookie-accept.cookies__button")).click();
        driver.findElement(By.cssSelector(".header3__button-sign-in")).click();
        try {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".header3__button-sign-in"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("form[action='/login/']>div+div input[type='text']")));
        element.sendKeys(login);
        driver.findElement(By.cssSelector("input[type = 'password']")).sendKeys(password);
        driver.findElement(By.cssSelector("form[action='/login/']>div+div+div+div>button")).click();
    }
}


