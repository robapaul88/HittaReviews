package com.rp.hitta.ui

import android.content.Context
import android.os.Handler
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet

/**
 * Created by roba
 */
interface AnimationDoneListener {
    fun onAnimationDone()
}

class TypeWriter(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {

    private var targetText: CharSequence? = null
    private var currentIndex: Int = 0
    private var delay: Long = 150
    private lateinit var animationDoneListener: AnimationDoneListener

    private val animationHandler = Handler()
    private val typeWriterAnimation = Runnable {
        updateText()
    }

    fun animateText(txt: CharSequence, interCharsDelay: Long, listener: AnimationDoneListener) {
        targetText = txt
        delay = interCharsDelay
        currentIndex = 0
        animationDoneListener = listener
        text = ""
        animationHandler.removeCallbacks(typeWriterAnimation)
        animationHandler.post(typeWriterAnimation)
    }

    private fun updateText() {
        if (currentIndex <= targetText!!.length) {
            text = targetText!!.subSequence(0, currentIndex++)
            animationHandler.postDelayed(typeWriterAnimation, delay)
        } else {
            animationHandler.postDelayed({ animationDoneListener.onAnimationDone() }, delay)
        }
    }
}