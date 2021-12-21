package com.example.sanggar.presenter.user_sanggar

import com.example.sanggar.common.doSubscribe
import com.example.sanggar.data.handler.common.ErrorHandler
import com.example.sanggar.data.handler.user.UserHandler
import com.example.sanggar.data.model.user_sanggar.UserSanggarListResponse
import com.example.sanggar.presenter.common.BasePresenter

class UserSanggarPresenter(val view: UserSanggarContract.View) : BasePresenter(view), UserSanggarContract.Presenter {

    val handler = UserHandler()

    override fun getListUser() {
        handler.getUser()
                .doSubscribe(object : ErrorHandler<UserSanggarListResponse>(this){
                    override fun onNext(t: UserSanggarListResponse) {
                        view.getUserListResponse(t)
                    }
                })
    }
}