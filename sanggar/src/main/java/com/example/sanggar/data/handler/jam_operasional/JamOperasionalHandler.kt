package com.example.sanggar.data.handler.jam_operasional

import com.example.sanggar.data.handler.common.BaseHandler
import com.example.sanggar.data.model.jam_operasional.JamOperasionalResponse
import com.example.sanggar.presenter.jam_operasional.JamOperasionalContract
import io.reactivex.Observable

class JamOperasionalHandler: BaseHandler() {

    val service = getClient().create(JamOperasionalContract.Handler::class.java)

    fun tambahJamOpersional(data: HashMap<String, Any?>): Observable<JamOperasionalResponse> {
        return service.tambahJamOperasoional(data)
    }
}