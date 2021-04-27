package com.example.sanggar.data.handler.auth

import com.example.sanggar.data.handler.common.BaseHandler
import com.example.sanggar.data.model.auth.AuthResponse
import com.example.sanggar.presenter.auth.AuthContract
import io.reactivex.Observable

class AuthHandler : BaseHandler() {
    val service = getClient().create(AuthContract.Handler::class.java)

    fun login(data: HashMap<String, Any?>) : Observable<AuthResponse> {
        return service.login(data)
    }

    fun register(data: HashMap<String, Any?>)  : Observable<AuthResponse> {
        return service.register(data)
    }
}