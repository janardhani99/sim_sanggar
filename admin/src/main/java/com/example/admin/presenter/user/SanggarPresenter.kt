package com.example.admin.presenter.user

import com.example.admin.common.doSubscribe
import com.example.admin.data.handler.common.ErrorHandler
import com.example.admin.data.handler.user.SanggarHandler
import com.example.admin.data.model.user.SanggarListResponse
import com.example.admin.presenter.common.BasePresenter


class SanggarPresenter(val view: SanggarContract.View): BasePresenter(view), SanggarContract.Presenter  {

    val handler = SanggarHandler()

    override fun getSanggar() {
        handler.getSanggar()
                .doSubscribe(object : ErrorHandler<SanggarListResponse>(this){
                    override fun onNext(t: SanggarListResponse) {
                        view.getSanggarResponse(t)
                    }
                })

    }
}