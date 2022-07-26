package bot.booking

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Booking(private val teardown: Boolean = false) : AutoCloseable {
    private var driver: WebDriver

    init {
        WebDriverManager.chromedriver().setup()
        driver = ChromeDriver()
        driver.manage().window().maximize()
    }

    fun landFirstPage() {
        driver.get(BASE_URL)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3))
    }

    fun closeCookiesPopUp() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2))
        driver.findElement(By.id("onetrust-accept-btn-handler")).click()
    }

    fun changeLanguageToEnglish() {
        driver.findElement(By.cssSelector("button[data-modal-id=\"language-selection\"]")).click()
        driver.findElement(By.cssSelector("a[data-lang=\"en-gb\"]")).click()
    }

    fun changeCurrency(currency: String = CURRENCY) {
        driver.findElement(By.cssSelector("button[data-tooltip-text=\"Choose your currency\"]")).click()
        driver.findElement(By.cssSelector("a[data-modal-header-async-url-param*=\"selected_currency=$currency\"]"))
            .click()
    }

    fun selectPlaceToGo(placeToGo: String = PLACE_TO_GO) {
        val searchField = driver.findElement(By.id("ss"))
        searchField.clear()
        searchField.sendKeys(placeToGo)
        driver.findElement(By.cssSelector("li[data-i=\"0\"]")).click()
    }

    fun selectDates(checkInDate: LocalDateTime = CHECK_IN_DATE, checkoutDate: LocalDateTime = CHECK_OUT_DATE) {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        driver.findElement(By.cssSelector("td[data-date=\"${checkInDate.format(formatter)}\"]")).click()
        driver.findElement(By.cssSelector("td[data-date=\"${checkoutDate.format(formatter)}\"]")).click()
    }

    fun selectAdults(count: Int = ADULTS_COUNT) {
        driver.findElement(By.id("xp__guests__toggle")).click()
        val decreaseAdultsNumberButton = driver.findElement(By.cssSelector("button[aria-label=\"Decrease number of Adults\"]"))
        val adultsNumberElement = driver.findElement(By.id("group_adults"))
        while (adultsNumberElement.getAttribute("value").toInt() > 1) {
            decreaseAdultsNumberButton.click()
        }
        val increaseAdultsNumberButton = driver.findElement(By.cssSelector("button[aria-label=\"Increase number of Adults\"]"))
        while (adultsNumberElement.getAttribute("value").toInt() < count) {
            increaseAdultsNumberButton.click()
        }
    }

    fun clickSearch() {
        driver.findElement(By.cssSelector("button[type=\"submit\"]")).click()
    }

    fun applyFiltration() {
        val filtration = BookingFiltration(driver)
        filtration.applyStarRating()
    }

    override fun close() {
        if (teardown) {
            driver.quit()
        }
    }
}