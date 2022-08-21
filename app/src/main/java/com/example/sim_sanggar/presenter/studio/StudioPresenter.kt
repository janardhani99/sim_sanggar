package com.example.sim_sanggar.presenter.studio

import com.example.sim_sanggar.common.doSubscribe
import com.example.sim_sanggar.data.handler.common.ErrorHandler
import com.example.sim_sanggar.data.handler.studio.StudioHandler
import com.example.sim_sanggar.data.model.studio.StudioListResponse
import com.example.sim_sanggar.presenter.common.BasePresenter

class StudioPresenter(val view: StudioContract.View): BasePresenter(view), StudioContract.Presenter {

    val handler = StudioHandler()

    override fun getStudio() {
        handler.getStudio()
                .doSubscribe(object : ErrorHandler<StudioListResponse>(this) {
                    override fun onNext(t: StudioListResponse) {
                        view.getStudioResponse(t)
                    }
                })
    }

}