package com.practical.divy.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.practical.divy.R

fun Activity.showToast(@StringRes id: Int, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, id, length).show()
}

fun Activity.showToast(text: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, text, length).show()
}

fun Fragment.showToast(@StringRes id: Int, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(requireActivity(), id, length).show()
}

fun Fragment.showToast(text: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(requireActivity(), text, length).show()
}


fun <T> Activity.gotoActivity(
    activity: Class<T>,
    bundle: Bundle? = null,
    needToFinish: Boolean = true,
    clearAllActivity: Boolean = false
) {
    val intent = Intent(this, activity)

    if (bundle != null)
        intent.putExtras(bundle)

    if (clearAllActivity)
        intent.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK

    startActivity(intent)

    if (needToFinish)
        finish()

    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
}

fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

fun String.createClickableSpan(
    clickListener: (view: View) -> Unit,
    typeface: Typeface? = null
): SpannableString {
    val spannableString = SpannableString(this)
    val clickableSpan = object : ClickableSpan() {
        override fun onClick(widget: View) {
            clickListener.invoke(widget)
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            typeface?.let {
                ds.typeface = it
            }
        }
    }

    spannableString.setSpan(
        clickableSpan,
        0,
        spannableString.length,
        SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return spannableString
}


fun Fragment.hideKeyboard(view: View) {
    (requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.apply {
        hideSoftInputFromWindow(view.windowToken, 0)
    }
}


/**
 * Show the view  (visibility = View.VISIBLE)
 */
fun View.show(): View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}

/**
 * Hide the view. (visibility = View.GONE)
 */
fun View.gone(): View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}

/**
 * Invisible the view. (invisibility = View.INVISIBLE)
 */
fun View.invisible(): View {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
    return this
}

fun EditText.showForcedKeyboard() {
    Handler(Looper.getMainLooper()).postDelayed({
        try {
            this.dispatchTouchEvent(
                MotionEvent.obtain(
                    SystemClock.uptimeMillis(),
                    SystemClock.uptimeMillis(),
                    MotionEvent.ACTION_DOWN,
                    0f,
                    0f,
                    0
                )
            )
            this.dispatchTouchEvent(
                MotionEvent.obtain(
                    SystemClock.uptimeMillis(),
                    SystemClock.uptimeMillis(),
                    MotionEvent.ACTION_UP,
                    0f,
                    0f,
                    0
                )
            )
            this.setSelection(this.getText().length)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }, 200)
}

fun Activity.showKeyboard(editText: EditText) {
    editText.requestFocus()
    editText.postDelayed(Runnable {
        val keyboard =
            getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        keyboard.showSoftInput(editText, 0)
    }, 100)
}

fun Activity.hideKeyboard() {
    val imm = getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
}


