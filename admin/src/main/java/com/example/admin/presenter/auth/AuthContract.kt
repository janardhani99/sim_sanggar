package com.example.admin.presenter.auth

import com.example.admin.data.model.auth.AuthResponse
import com.example.admin.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import kotlin.collections.HashMap

interface AuthContract {
    interface View : BaseContract.View {
        fun loginResponse(response : AuthResponse)
    }

    interface Presenter {
        fun login(data: HashMap<String, Any?>)
        fun register(data: HashMap<String, Any?>)
    }

    interface Handler {
        @FormUrlEncoded
        @POST("login")
        fun login(@FieldMap data : HashMap<String, Any?>) : Observable<AuthResponse>

        @FormUrlEncoded
        @POST("register")
        fun register(@FieldMap data : HashMap<String, Any?>) : Observable<AuthResponse>
    }
}