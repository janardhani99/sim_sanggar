package com.example.sanggar.presenter.jam_operasional

import com.example.sanggar.common.doSubscribe
import com.example.sanggar.data.handler.common.ErrorHandler
import com.example.sanggar.data.handler.jam_operasional.JamOperasionalHandler
import com.example.sanggar.data.model.jam_operasional.JamOperasionalListResponse
import com.example.sanggar.data.model.jam_operasional.JamOperasionalResponse
import com.example.sanggar.presenter.common.BasePresenter

class JamOperasionalPresenter(val view: JamOperasionalContract.View): BasePresenter(view), JamOperasionalContract.Presenter {

    val handler = JamOperasionalHandler()

    override fun tambahJamOperasional(data: HashMap<String, Any?>) {
        handler.tambahJamOpersional(data)
                .doSubscribe(object: ErrorHandler<JamOperasionalResponse>(this) {
                    override fun onNext(t: JamOperasionalResponse) {
                        view.jamOperasionalResponse(t)
                    }
                })
    }

    override fun getJamOperasional() {
        handler.getJamOperasional()
                .doSubscribe(object: ErrorHandler<JamOperasionalListResponse>(this){
                    override fun onNext(t: JamOperasionalListResponse) {
                        view.getJamOperasionalResponse(t)
                    }

                })
    }

    override fun editJamOperasional(id: Int, data: HashMap<String, Any?>) {
        handler.editJamOperasional(id, data)
                .doSubscribe(object : ErrorHandler<JamOperasionalResponse>(this){
                    override fun onNext(t: JamOperasionalResponse) {
                        view.jamOperasionalResponse(t)
                    }

                })
    }


}