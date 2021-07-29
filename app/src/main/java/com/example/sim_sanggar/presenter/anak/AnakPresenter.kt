package com.example.sim_sanggar.presenter.anak

import com.example.sim_sanggar.common.doSubscribe
import com.example.sim_sanggar.data.handler.anak.AnakHandler
import com.example.sim_sanggar.data.handler.common.ErrorHandler
import com.example.sim_sanggar.data.model.anak.AnakListResponse
import com.example.sim_sanggar.data.model.anak.AnakResponse
import com.example.sim_sanggar.presenter.common.BasePresenter


class AnakPresenter(val view: AnakContract.View): BasePresenter(view), AnakContract.Presenter {
    val handler = AnakHandler()

    override fun addAnak(data: HashMap<String, Any?>) {
        handler.addAnak(data)
                .doSubscribe(object : ErrorHandler<AnakResponse>(this){
                    override fun onNext(t: AnakResponse) {
                        view.anakResponse(t)
                    }
                })
    }

    override fun getAnak(){
        handler.getAnak()
                .doSubscribe(object : ErrorHandler<AnakListResponse>(this){
                    override fun onNext(t: AnakListResponse) {
                        view.getAnakResponse(t)
                    }
                })
    }
}