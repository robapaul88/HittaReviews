package com.rp.hitta.init

import android.text.format.DateUtils
import com.rp.hitta.model.Review
import java.util.*

/**
 * Created by roba
 */
class Initializer {
    companion object {
        fun getOwnDefaultReview(): Review {
            return Review(0, "",
                    "",
                    0,
                    0,
                    "hitta.se",
                    "",
                    1)
        }

        fun getInitialReviews(): List<Review> {
            return Arrays.asList(
                    getOwnDefaultReview(),
                    Review(1, "",
                            "",
                            System.currentTimeMillis() - 12 * DateUtils.HOUR_IN_MILLIS,
                            4,
                            "hitta.se", "Liked it very much - probably one of the best thai restaurants in the city - recommend!"),
                    Review(2, "Jenny Svensson",
                            "",
                            System.currentTimeMillis() - DateUtils.DAY_IN_MILLIS,
                            3,
                            "hitta.se",
                            "Maybe a bit too fast food. I personally dislike that. Good otherwise."),
                    Review(3, "happy56",
                            "https://image.freepik.com/free-vector/hand-drawn-woman-face_23-2147507412.jpg",
                            System.currentTimeMillis() - DateUtils.DAY_IN_MILLIS,
                            5,
                            "yelp.com",
                            "Super good! Love the food!"))
        }
    }
}