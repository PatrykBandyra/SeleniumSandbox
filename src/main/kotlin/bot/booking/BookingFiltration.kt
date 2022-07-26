package bot.booking

import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.WebDriver

class BookingFiltration(private val driver: WebDriver) {

    fun applyStarRating(starValues: Set<StarRating> = STAR_VALUES) {
        val starFiltrationBox = driver.findElement(By.cssSelector("div[data-filters-group=\"class\"]"))
        starValues.forEach { starRating ->
            starFiltrationBox.findElement(By.cssSelector("div[data-filters-item=\"class:class=${starRating.getValue()}\""))
                .click()
        }
    }

    fun sortPriceLowestFirst() {
        try {
            driver.findElement(By.cssSelector("button[data-testid=\"sorters-dropdown-trigger\"]")).click()
            driver.findElement(By.cssSelector("button[data-id=\"price\"]")).click()
        } catch (e: NoSuchElementException) {
            driver.findElement(By.cssSelector("li[data-id=\"price\"]")).click()
        }
    }
}