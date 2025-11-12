import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommandsTest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("http://the-internet.herokuapp.com/dynamic_controls");
            WebElement enableButton = driver.findElement(By.xpath("//button[text()='Enable']"));
            enableButton.click();

            WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='text']")));
            System.out.println("შეყვანის ველი გააქტიურდა და ტექსტი ჩანს");

            wait.until(ExpectedConditions.textToBePresentInElement(enableButton, "Disable"));
            System.out.println("ღილაკის ტექსტი წარმატებით შეიცვალა");

            inputField.sendKeys("Bootcamp");
            inputField.clear();

            driver.get("http://the-internet.herokuapp.com/drag_and_drop");
            WebElement columnA = driver.findElement(By.id("column-a"));
            WebElement columnB = driver.findElement(By.id("column-b"));

            int yA = columnA.getLocation().getY();
            int yB = columnB.getLocation().getY();

            if (yA == yB) {
                System.out.println("სვეტები A და B წარმატებით არიან გასწორებული");
            } else {
                System.out.println("სვეტები A და B არ არის გასწორებული");
            }

        } finally {
            driver.quit();
        }
    }
}
