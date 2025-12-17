package com.example.vehicle;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Using random port to avoid conflicts
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SeleniumE2ETest {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Disabled("Requires a local Chrome installation. Enable this test when running in an environment with Chrome.")
    void testFullScenario() {
        driver.get("http://localhost:" + port + "/");

        // 1. Verify Initial State
        WebElement speedEl = driver.findElement(By.id("speed"));
        assertEquals("0", speedEl.getText().trim());

        // 2. Start Engine
        WebElement engineBtn = driver.findElement(By.xpath("//button[contains(text(), 'Start Engine')]"));
        engineBtn.click();

        // 3. Accelerate
        WebElement accelInput = driver.findElement(By.name("value")); // Assumes first one found is accel
        accelInput.clear();
        accelInput.sendKeys("25");

        WebElement accelBtn = driver.findElement(By.name("accelerate"));
        accelBtn.click();

        // Check speed
        speedEl = driver.findElement(By.id("speed"));
        assertEquals("25", speedEl.getText().trim());

        // 4. Steer
        WebElement steerInput = driver.findElement(By.xpath("(//input[@name='value'])[2]"));
        steerInput.clear();
        steerInput.sendKeys("30");

        WebElement steerBtn = driver.findElement(By.name("steer"));
        steerBtn.click();

        WebElement steerEl = driver.findElement(By.id("steering_angle"));
        assertEquals("30", steerEl.getText().trim());

        // 5. Brake
        WebElement brakeBtn = driver.findElement(By.name("brake"));
        brakeBtn.click();

        speedEl = driver.findElement(By.id("speed"));
        assertEquals("0", speedEl.getText().trim());
    }
}
