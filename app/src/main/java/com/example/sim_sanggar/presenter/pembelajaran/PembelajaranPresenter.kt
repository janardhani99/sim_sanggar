package com.example.sim_sanggar.presenter.pembelajaran

import com.example.sim_sanggar.common.doSubscribe
import com.example.sim_sanggar.data.handler.common.ErrorHandler
import com.example.sim_sanggar.data.handler.pembelajaran.PembelajaranHandler
import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.pembelajaran.PembelajaranListResponse
import com.example.sim_sanggar.data.model.pembelajaran.PembelajaranResponse
import com.example.sim_sanggar.presenter.common.BasePresenter


class PembelajaranPresenter(val view: PembelajaranContract.View): BasePresenter(view), PembelajaranContract.Presenter {
    val handler = PembelajaranHandler()

    override fun getPembelajaran() {
        handler.getPembelajaran()
                .doSubscribe(object : ErrorHandler<PembelajaranListResponse>(this){
                    override fun onNext(t: PembelajaranListResponse) {
                        view.getPembelajaranResponse(t)
                    }
                })
    }


}