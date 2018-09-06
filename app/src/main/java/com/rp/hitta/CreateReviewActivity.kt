package com.rp.hitta

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.RatingBar
import com.rp.hitta.api.HittaSandbox
import com.rp.hitta.db.ReviewsDatabase
import kotlinx.android.synthetic.main.create_review_layout.*

/**
 * Created by roba
 */
class CreateReviewActivity : AppCompatActivity() {
    companion object {
        private const val KEY_COMPANY_NAME = "KEY_COMPANY_NAME"
        private const val KEY_INITIAL_RATING = "KEY_INITIAL_RATING"
        private const val KEY_INITIAL_NAME = "KEY_INITIAL_NAME"
        private const val KEY_INITIAL_CONTENT = "KEY_INITIAL_CONTENT"
        fun start(context: Context, companyName: String, rating: Int, name: String? = "", content: String? = "") {
            val intent = Intent(context, CreateReviewActivity::class.java)
            intent.putExtra(KEY_COMPANY_NAME, companyName)
            intent.putExtra(KEY_INITIAL_RATING, rating)
            intent.putExtra(KEY_INITIAL_NAME, name)
            intent.putExtra(KEY_INITIAL_CONTENT, content)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_review_layout)

        val initialReview = intent.getIntExtra(KEY_INITIAL_RATING, 3)
        createReviewRating.rating = initialReview.toFloat()
        updateRatingLabel(initialReview)

        createReviewOwnName.setText(intent.getStringExtra(KEY_INITIAL_NAME))
        createReviewOwnContent.setText(intent.getStringExtra(KEY_INITIAL_CONTENT))

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.title = getString(R.string.review_frm, intent.getStringExtra(KEY_COMPANY_NAME))

        createReviewRating.onRatingBarChangeListener =
                RatingBar.OnRatingBarChangeListener { _, p1, _ ->
                    updateRatingLabel(p1.toInt())
                }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.create_review_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.save_review -> {
                if (isValidReview()) {
                    persistOwnReview()
                    showSuccessAlert()
                }
                return true
            }
        }
        return false
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        persistOwnReview()
        super.onBackPressed()
    }

    private fun persistOwnReview() {
        val ownReview = ReviewsDatabase.getInstance(this@CreateReviewActivity).reviewsDao().getOwnReview()
        ownReview?.let {
            ownReview.userName = createReviewOwnName.text.toString()
            ownReview.content = createReviewOwnContent.text.toString()
            ownReview.createdAt = System.currentTimeMillis()
            ownReview.rating = createReviewRating.rating.toInt()
            ReviewsDatabase.getInstance(this@CreateReviewActivity).reviewsDao().updateReview(ownReview)
            HittaSandbox.persistReview(ownReview)
        }
    }

    private fun showSuccessAlert() {
        AlertDialog.Builder(this, R.style.AlertDialogTheme)
                .setTitle(getString(R.string.alert_title))
                .setMessage(getString(R.string.alert_message))
                .setPositiveButton(getString(R.string.alert_button_text)) { _, _ -> finish() }
                .setCancelable(false)
                .show()
    }

    private fun updateRatingLabel(rating: Int) {
        when (rating) {
            1 -> createReviewRatingLabel.text = getString(R.string.rating_1_lbl)
            2 -> createReviewRatingLabel.text = getString(R.string.rating_2_lbl)
            3 -> createReviewRatingLabel.text = getString(R.string.rating_3_lbl)
            4 -> createReviewRatingLabel.text = getString(R.string.rating_4_lbl)
            5 -> createReviewRatingLabel.text = getString(R.string.rating_5_lbl)
        }
    }

    private fun isValidReview(): Boolean {
        if (TextUtils.isEmpty(createReviewOwnName.text.toString())) {
            createReviewOwnName.error = getString(R.string.required)
            return false
        }
        return true
    }
}