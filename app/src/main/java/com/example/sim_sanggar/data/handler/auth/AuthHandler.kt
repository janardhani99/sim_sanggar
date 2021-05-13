package com.example.sim_sanggar.data.handler.auth

import com.example.sim_sanggar.data.handler.common.BaseHandler
import com.example.sim_sanggar.data.model.auth.AuthResponse
import com.example.sim_sanggar.presenter.auth.AuthContract
import io.reactivex.Observable

class AuthHandler: BaseHandler() {
    val service = getClient().create(AuthContract.Handler::class.java)

    fun login(data: HashMap<String, Any?>) : Observable<AuthResponse> {
        return service.login(data)
    }

    fun register(data: HashMap<String, Any?>)  : Observable<AuthResponse> {
        return service.register(data)
    }

}