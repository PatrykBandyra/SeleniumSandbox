package bot

import bot.booking.Booking

fun main() {
    Booking(teardown = false).use { bot ->
        bot.landFirstPage()
        bot.closeCookiesPopUp()
        bot.changeLanguageToEnglish()
        bot.changeCurrency()
        bot.selectPlaceToGo()
        bot.selectDates()
        bot.selectAdults()
        bot.clickSearch()
        bot.applyFiltration()
        bot.reportResults()
    }
}