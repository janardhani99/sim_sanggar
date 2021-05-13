package com.example.sim_sanggar.presenter.auth

import com.example.sim_sanggar.data.model.auth.AuthResponse
import com.example.sim_sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.*
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