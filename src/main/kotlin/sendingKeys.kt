import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.time.Duration

fun main() {
    WebDriverManager.chromedriver().setup()

    val driver: WebDriver = ChromeDriver()
    driver.manage().window().maximize()
    driver.get("https://demo.anhtester.com/basic-first-form-demo.html")
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5))
    try {
        driver.findElement(By.className("at-cm-no-button")).click()
    } catch (_: NoSuchElementException) {
        println("No pop-up to close")
    }

    driver.findElement(By.id("sum1")).sendKeys(15.toString())
    driver.findElement(By.id("sum2")).sendKeys(10.toString())

    driver.findElement(By.cssSelector("#gettotal > button")).click()
}