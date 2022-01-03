package com.example.sim_sanggar.data.handler.jam_operasional

import com.example.sim_sanggar.data.handler.common.BaseHandler
import com.example.sim_sanggar.data.model.jam_operasional.JamOperasionalResponse
import com.example.sim_sanggar.presenter.jam_operasional.JamOperasionalContract
import io.reactivex.Observable

class JamOperasionalHandler: BaseHandler() {

    val service = getClient().create(JamOperasionalContract.Handler::class.java)

    fun getJamOperasional(): Observable<JamOperasionalResponse> {
        return service.getJamOperasional()
    }
}