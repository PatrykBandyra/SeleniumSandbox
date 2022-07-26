package bot.booking

import de.m3y.kformat.Table
import de.m3y.kformat.table
import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.WebElement

class BookingReport(private val resultsContainer: WebElement) {

    private data class PlaceData(val title: String, val price: String, val prevPrice: String)

    fun createReport() {
        val data: MutableList<PlaceData> = ArrayList()
        val resultsElements = resultsContainer.findElements(By.cssSelector("div[data-testid=\"property-card\"]"))
        resultsElements.forEach { resultsElement ->
            val title: String = resultsElement.findElement(By.cssSelector("div[data-testid=\"title\"]")).text
            val priceElements =
                resultsElement.findElement(By.cssSelector("div[data-testid=\"price-and-discounted-price\"]"))
            var prevPrice = ""
            if (priceElements.findElements(By.tagName("span")).size > 1) {
                try {
                    prevPrice = priceElements.findElement(By.className("a0c113411d")).text
                } catch (_: NoSuchElementException) {
                }
            }
            val price: String = priceElements.findElement(By.className("fcab3ed991")).text
            data.add(PlaceData(title, price, prevPrice))
        }

        println(
            table {
                header("Place", "Price", "Prev Price")

                data.forEach { placeData ->
                    row(placeData.title, placeData.price, placeData.prevPrice)
                }

                hints {
                    alignment("Place", Table.Hints.Alignment.LEFT)
                    borderStyle = Table.BorderStyle.SINGLE_LINE
                }
            }.render(StringBuilder()).toString()
        )
    }
}