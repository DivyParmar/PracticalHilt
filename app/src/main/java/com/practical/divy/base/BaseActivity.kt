package com.practical.divy.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.practical.divy.utils.showToast
import com.practical.divy.R
import com.practical.divy.api.ApiResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {
    private var progressDialog: Dialog? = null

    fun showProgressDialog() {
        if (progressDialog == null || !progressDialog?.isShowing!!) {
            progressDialog = Dialog(this)
            progressDialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            progressDialog?.setContentView(R.layout.custom_progressbar)
            progressDialog?.setCancelable(false)
            progressDialog?.show()
        }
    }

    fun hideProgressDialog() {

        try {
            if (progressDialog != null && progressDialog?.isShowing!!) {
                progressDialog?.dismiss()
            }
        } catch (throwable: Throwable) {

        } finally {
            progressDialog?.dismiss()
        }
    }

    fun <T> manageApiResult(
        apiResult: ApiResult<T>,
        showLoadingIndicator: Boolean = true,
        failureListener: ((String) -> Unit)? = null,
        successListener: (T, String) -> Unit,
    ) {
        when (apiResult) {
            ApiResult.Loading -> {
                Log.e("BaseActivity", ": Loading")

                if (showLoadingIndicator) {
                    showProgressDialog()
                }
            }

            is ApiResult.Success -> {
                Log.e("BaseActivity", ": Success")

                if (showLoadingIndicator) {
                    val handler = Handler(Looper.getMainLooper())
                    val runnable = Runnable {
                        hideProgressDialog()
                    }
                    handler.postDelayed(runnable, 5000)
                }
                successListener.invoke(apiResult.response, apiResult.message)
            }

            is ApiResult.Failure -> {
                Log.e("BaseActivity", ": Failure")

                hideProgressDialog()
                if (failureListener != null && !apiResult.isException) {
                    failureListener.invoke(apiResult.errorMessage)
                } else {
                    showToast(apiResult.errorMessage)
                }
            }

            ApiResult.NoInternet -> {
                Log.e("BaseActivity", ": NoInternet")


                hideProgressDialog()
                showToast(getString(R.string.no_internet_connection))
            }

            ApiResult.SessionExpired -> {
                Log.e("BaseActivity", ": SessionExpired")

                hideProgressDialog()
                //perform logout code
            }

            else -> {

            }
        }
    }
}