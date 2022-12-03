import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCard {
    private WebDriver driver;
    @BeforeAll
    static void setUpAll() {
        System.setProperty("web-driver.chrome.driver", "driver/win/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
    }

    @BeforeEach
    void setup() {
        driver = WebDriverManager.chromedriver().create();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void getWebsite() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Анна-Мария Петрова-Иванова");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+71231111231");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__text")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

}

