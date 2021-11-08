package com.example.sim_sanggar.presenter.jam_operasional

import com.example.sim_sanggar.common.doSubscribe
import com.example.sim_sanggar.data.handler.common.ErrorHandler
import com.example.sim_sanggar.data.handler.jam_operasional.JamOperasionalHandler
import com.example.sim_sanggar.data.model.jam_operasional.JamOperasionalResponse
import com.example.sim_sanggar.presenter.common.BasePresenter

class JamOperasionalPresenter(val view: JamOperasionalContract.View): BasePresenter(view), JamOperasionalContract.Presenter {

    val handler = JamOperasionalHandler()

    override fun tambahJamOperasional(data: HashMap<String, Any?>) {
        handler.tambahJamOperasional(data)
                .doSubscribe(object: ErrorHandler<JamOperasionalResponse>(this) {
                    override fun onNext(t: JamOperasionalResponse) {
                        view.jamOperasionalResponse(t)
                    }
                })
    }


}