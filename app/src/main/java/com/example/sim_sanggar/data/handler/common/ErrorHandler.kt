package com.example.sim_sanggar.data.handler.common

import android.util.Log
import com.example.sim_sanggar.GlobalClass.Companion.context
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.data.model.common.BaseResultData
import com.example.sim_sanggar.presenter.common.BaseContract
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import java.io.IOException
import java.lang.ref.WeakReference
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.*

abstract class ErrorHandler<T : BaseResultData>(presenter: BaseContract.Presenter) : Observer<T> {
    private val TAG = "APIError Handler"
    override fun onComplete() {
        Log.d("ProfileDisplayPresenter", "Data sudah disini complete ")
    }

    override fun onSubscribe(d: Disposable) {
        Log.d("ProfileDisplayPresenter", "Data sudah disini dis ")
    }

    private val weakReference: WeakReference<BaseContract.Presenter> = WeakReference(presenter)
    override fun onError(e: Throwable) {
        Log.d("ProfileDisplayPresenter", "Data sudah disini err ")
        val presenter = weakReference.get()
        when (e) {
            is HttpException -> {
                val error = e.response()?.let { Utilities.parseError(it) }
                Log.i(TAG, e.response()?.code().toString())
                when (e.response()?.code()) {
                    403 -> presenter?.showError(
                            error?.error?.title.toString(),
                            error?.error?.errors?.get(0)?.message.toString()
                    )
                    422 -> presenter?.showError(
                            error?.error?.title.toString(),
                            error?.error?.errors?.get(0)?.message.toString()
                    )

                    else -> presenter?.showError(
                            error?.error?.title.toString(),
                            error?.error?.errors?.get(0)?.message.toString()
                    )
                }

            }
            is UnknownHostException -> {
                Log.i(TAG, "No connection")
                presenter?.showError(
                        context.getString(R.string.error),
                        context.getString(R.string.no_connection)
                )
            }
            is SocketTimeoutException -> {
                Log.i(TAG, "Timeout == ${e.cause}")
                presenter?.showError(
                        context.getString(R.string.error)
                        , context.getString(R.string.connection_timeout) + "==" + e.cause
                )
            }
            is IOException -> {
                Log.i(TAG, "IO Exception == ${e.localizedMessage}")
                presenter?.showError(
                        context.getString(R.string.error),
                        context.getString(R.string.io_exception) + "==" + e.localizedMessage
                )
            }
            else -> {
                Log.i(TAG, e.localizedMessage)
            }
        }
    }

}
