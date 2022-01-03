package com.example.sim_sanggar.presenter.sanggar

import com.example.sim_sanggar.common.doSubscribe
import com.example.sim_sanggar.data.handler.common.ErrorHandler
import com.example.sim_sanggar.data.handler.sanggar.SanggarHandler
import com.example.sim_sanggar.data.model.anak.AnakListResponse
import com.example.sim_sanggar.data.model.sanggar.SanggarListResponse
import com.example.sim_sanggar.presenter.anak.AnakContract
import com.example.sim_sanggar.presenter.common.BasePresenter

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