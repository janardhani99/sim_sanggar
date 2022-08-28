package com.example.admin.presenter.user

import com.example.admin.common.doSubscribe
import com.example.admin.data.handler.common.ErrorHandler
import com.example.admin.data.handler.user.SanggarHandler
import com.example.admin.data.model.user.SanggarListResponse
import com.example.admin.data.model.user.SanggarResponse
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

    override fun addSanggar(data: HashMap<String, Any?>) {
        handler.addSanggar(data)
                .doSubscribe(object : ErrorHandler<SanggarResponse>(this){
                    override fun onNext(t: SanggarResponse) {
                        view.sanggarResponse(t)
                    }
                })
    }

    override fun editProfilSanggar(id: Int, data: HashMap<String, Any?>) {
        handler.editSanggar(id, data)
                .doSubscribe(object : ErrorHandler<SanggarResponse>(this){
                    override fun onNext(t: SanggarResponse) {
                        view.sanggarResponse(t)
                    }
                })
    }
}