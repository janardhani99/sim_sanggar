package com.example.sanggar.common

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.Window
import com.example.sanggar.R
import com.example.sanggar.data.handler.common.BaseHandler
import com.example.sanggar.data.model.common.APIError
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

object Utilities {
    fun parseError(response: Response<*>): APIError {
        val converter: Converter<ResponseBody, APIError> = BaseHandler().getClient()
                .responseBodyConverter(APIError::class.java, arrayOfNulls<Annotation>(0))

        val error: APIError

        try {
            error = converter.convert(response.errorBody())!!
        } catch (e: IOException) {
            e.printStackTrace()
            return APIError()
        }
        return error
    }

    private lateinit var dialog: Dialog

    fun showProgress(context: Context) {
        dialog = Dialog(context)
        val view = LayoutInflater.from(context).inflate(R.layout.layout_progress_dialog, null)
        dialog.run {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.setBackgroundDrawableResource(R.color.transparent)
//            setCancelable(false)
            setCanceledOnTouchOutside(false)
            setContentView(view)
            show()
        }
    }

    fun hideProgress() = if (this::dialog.isInitialized && dialog.isShowing) dialog.dismiss() else {
    }

}