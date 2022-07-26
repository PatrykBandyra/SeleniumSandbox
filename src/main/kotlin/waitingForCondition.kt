import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration


fun main() {
    WebDriverManager.chromedriver().setup()

    val driver: WebDriver = ChromeDriver()
    driver.manage().window().maximize()

    driver.get("https://jqueryui.com/resources/demos/progressbar/download.html")
    driver.findElement(By.id("downloadButton")).click()

    WebDriverWait(driver, Duration.ofSeconds(30)).until(
        ExpectedConditions.textToBePresentInElement(
            driver.findElement(By.cssSelector("#dialog > div.progress-label")),
            "Complete!"
        )
    )

    val closeButton: WebElement = driver.findElement(
        By.cssSelector("body > div > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button")
    )
    WebDriverWait(driver, Duration.ofSeconds(30)).until(
        ExpectedConditions.textToBePresentInElement(
            closeButton,
            "Close"
        )
    )
    closeButton.click()
}