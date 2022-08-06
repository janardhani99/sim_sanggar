package com.example.admin.presenter.auth

import com.example.admin.common.doSubscribe
import com.example.admin.data.handler.auth.AuthHandler
import com.example.admin.data.handler.common.ErrorHandler
import com.example.admin.data.model.auth.AuthResponse
import com.example.admin.presenter.common.BasePresenter

class AuthPresenter(val view :AuthContract.View) : BasePresenter(view), AuthContract.Presenter {

    val handler = AuthHandler()

    override fun login(data: HashMap<String, Any?>) {
        handler.login(data)
                .doSubscribe(object : ErrorHandler<AuthResponse>(this){
                    override fun onNext(t: AuthResponse) {
                        view.loginResponse(t)
                    }
                })
    }

    override fun register(data: HashMap<String, Any?>) {
        handler.register(data)
                .doSubscribe(object : ErrorHandler<AuthResponse>(this){
                    override fun onNext(t: AuthResponse) {
                        view.loginResponse(t)
                    }
                })
    }
}