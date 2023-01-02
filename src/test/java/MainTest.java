import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Set;

public class MainTest extends BaseTest {

    private final String login = "nowaj54360@lubde.com";
    private final String password = "Nowaj54360@lubde.com";
    private final String find = "ОТУС";
    private final String expectedResult = "Онлайн‑курсы для профессионалов, дистанционное обучение современным ...";

    @Test
    public void firstTask() throws InterruptedException {

//1. Открыть Chrome в headless режиме
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
//2. Перейти на https://duckduckgo.com/
        driver.get("https://duckduckgo.com/");
        Thread.sleep(2000);
//3. В поисковую строку ввести ОТУС
        enterString();
//4. Проверить что в поисковой выдаче первый результат Онлайн‑курсы для профессионалов, дистанционное обучение
        checkResult();
    }

    @Test
    public void secondTask() throws InterruptedException {

//1. Открыть Chrome в режиме киоска
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
//2. Перейти на https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818
        driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");
        Thread.sleep(2000);
//3. Нажать на любую картинку
        driver.findElement(By.cssSelector(".content-overlay")).click();
        Thread.sleep(2000);
//4. Проверить что картинка открылась в модальном окне
        driver.findElement(By.cssSelector(".pp_hoverContainer")).isDisplayed();
    }

    @Test
    public void thirdTask() throws InterruptedException {
        Logger logger = LogManager.getLogger(BaseTest.class);
//1. Открыть Chrome в режиме полного экрана
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
//2. Перейти на https://otus.ru
        driver.get("https://otus.ru/");
        Thread.sleep(2000);
//3. Авторизоваться под каким-нибудь тестовым пользователем(можно создать нового)
        loginInOtus();
//4. Вывести в лог все cookie
        Set<Cookie> cookies;
        cookies = driver.manage().getCookies();
        //System.out.println(cookies);
        logger.info(cookies);
    }

    private void enterString() throws InterruptedException {
        driver.findElement(By.cssSelector("#search_form_input_homepage")).sendKeys(find);
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#search_button_homepage")).click();
        Thread.sleep(2000);
    }

    private void checkResult() {
        Assertions.assertEquals(expectedResult, driver.findElement(By.cssSelector(".ikg2IXiCD14iVX7AdZo1")).getText());
    }

    private void loginInOtus() throws InterruptedException {
        driver.findElement(By.cssSelector("button.js-cookie-accept.cookies__button")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".header3__button-sign-in")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[type = 'text']")).sendKeys(login);
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[type = 'password']")).sendKeys(password);
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("button[class='new-button new-button_full new-button_blue new-button_md']")).click();
        Thread.sleep(2000);
    }
}


