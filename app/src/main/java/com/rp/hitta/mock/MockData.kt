package com.rp.hitta.mock

import android.text.format.DateUtils
import com.rp.hitta.model.Review
import java.util.*

/**
 * Created by roba
 */
class MockData {
    companion object {
        fun getInitialReviews(): List<Review> {
            return Arrays.asList(
                    Review("",
                            "",
                            System.currentTimeMillis() - 12 * DateUtils.HOUR_IN_MILLIS,
                            4,
                            "hitta.se", "Liked it very much - probably one of the best thai restaurants in the city - recommend!"),
                    Review("Jenny Svensson",
                            "",
                            System.currentTimeMillis() - DateUtils.DAY_IN_MILLIS,
                            3,
                            "hitta.se",
                            "Maybe a bit too fast food. I personally dislike that. Good otherwise."),
                    Review("happy56",
                            "https://image.freepik.com/free-vector/hand-drawn-woman-face_23-2147507412.jpg",
                            System.currentTimeMillis() - DateUtils.DAY_IN_MILLIS,
                            5,
                            "yelp.com",
                            "Super good! Love the food!"))
        }
    }
}