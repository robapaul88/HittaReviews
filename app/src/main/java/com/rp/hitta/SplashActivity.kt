package com.rp.hitta

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.rp.hitta.api.CompanyFetcher
import com.rp.hitta.api.OnDoneListener
import com.rp.hitta.ui.AnimationDoneListener
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.concurrent.CountDownLatch

/**
 * Created by roba
 */
class SplashActivity : AppCompatActivity() {

    companion object {
        const val INTER_CHARS_DELAY: Long = 250
    }

    private lateinit var allDoneLatch: CountDownLatch
    private lateinit var companyName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val textToAnimate = getString(R.string.splash_text)
        val startColor = ContextCompat.getColor(this, R.color.start_color)
        val endColor = ContextCompat.getColor(this, R.color.end_color)
        val colorFade = ObjectAnimator.ofObject(mainFrameContainer, "backgroundColor", ArgbEvaluator(), startColor, endColor)
        colorFade.duration = textToAnimate.length * INTER_CHARS_DELAY
        colorFade.start()

        allDoneLatch = CountDownLatch(2)

        mainTitleTw.animateText(textToAnimate, INTER_CHARS_DELAY, object : AnimationDoneListener {
            override fun onAnimationDone() {
                allDoneLatch.countDown()
                tryToOpenMainPage()
            }
        })

        CompanyFetcher.fetchAsync(object : OnDoneListener<String> {
            override fun onSuccess(displayName: String) {
                companyName = displayName
                allDoneLatch.countDown()
                tryToOpenMainPage()
            }

            override fun onFail() {
                allDoneLatch.countDown()
                tryToOpenMainPage()
            }
        })
    }

    private fun tryToOpenMainPage() {
        if (allDoneLatch.count > 0) {
            return
        }
        finish()
        startActivity(MainActivity.intentFor(this, companyName))
    }
}