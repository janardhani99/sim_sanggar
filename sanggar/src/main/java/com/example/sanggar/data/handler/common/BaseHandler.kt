package com.example.sanggar.data.handler.common

import android.provider.SyncStateContract
import com.example.sanggar.GlobalClass.Companion.context
import com.example.sanggar.common.Constants
import com.example.sanggar.common.Preferences
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


open class BaseHandler {
    var retrofit: Retrofit? = null
    var httpLoggingInterceptor = HttpLoggingInterceptor()
    var httpClient = OkHttpClient.Builder()

    fun getClient(): Retrofit {
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer ${Preferences(context).accessToken}")
                    .method(original.method(), original.body())
                    .build()
            chain.proceed(request)
        }
        enabledLog()

        httpClient.addInterceptor(httpLoggingInterceptor)
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(Constants.Url.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpClient.build())
                    .build()
        }

        return retrofit!!
    }

    fun enabledLog() {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    }

}
