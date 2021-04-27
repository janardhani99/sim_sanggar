package com.example.sanggar.common

import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import coil.load
import com.example.sanggar.R
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun View.clickWithDebounce(debounceTime: Long = 600L, action: (View) -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0
        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action(v)
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun <T: Any> Observable<T>.doSubscribe(observer: Observer<in T>) {
    this.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
}

fun EditText.onTextChanged(onTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            onTextChanged.invoke(p0.toString())
        }

        override fun afterTextChanged(p0: Editable?) {
        }

    })
}

fun ImageView.loadImage(url: String) {
    this.load(url) {
        crossfade(true)
        fallback(R.drawable.ic_baseline_image_24)
        placeholder(R.drawable.ic_baseline_image_24)
    }
}
