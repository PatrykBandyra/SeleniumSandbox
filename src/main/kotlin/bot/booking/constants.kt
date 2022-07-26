package bot.booking

import java.time.LocalDateTime

const val BASE_URL: String = "https://www.booking.com"
const val CURRENCY: String = "USD"
const val PLACE_TO_GO: String = "Zurich"
val CHECK_IN_DATE: LocalDateTime = LocalDateTime.of(2022, 8, 12, 12, 0)
val CHECK_OUT_DATE: LocalDateTime = LocalDateTime.of(2022, 8, 25, 12, 0)
const val ADULTS_COUNT: Int = 4
val STAR_VALUES: Set<StarRating> = hashSetOf(
    StarRating.UNRATED,
    StarRating.FIVE
)

enum class StarRating {
    UNRATED {
        override fun getValue(): String {
            return "0"
        }
    },
    ONE {
        override fun getValue(): String {
            return "1"
        }
    },
    TWO {
        override fun getValue(): String {
            return "2"
        }
    },
    THREE {
        override fun getValue(): String {
            return "3"
        }
    },
    FOUR {
        override fun getValue(): String {
            return "4"
        }
    },
    FIVE {
        override fun getValue(): String {
            return "5"
        }
    };

    abstract fun getValue(): String
}
