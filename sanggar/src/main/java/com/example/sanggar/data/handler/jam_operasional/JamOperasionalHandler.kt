package com.example.sanggar.data.handler.jam_operasional

import com.example.sanggar.data.handler.common.BaseHandler
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.jam_operasional.JamOperasionalListResponse
import com.example.sanggar.data.model.jam_operasional.JamOperasionalResponse
import com.example.sanggar.presenter.jam_operasional.JamOperasionalContract
import io.reactivex.Observable

class JamOperasionalHandler: BaseHandler() {

    val service = getClient().create(JamOperasionalContract.Handler::class.java)

    fun tambahJamOpersional(data: HashMap<String, Any?>): Observable<JamOperasionalResponse> {
        return service.tambahJamOperasoional(data)
    }

    fun getJamOperasional(): Observable<JamOperasionalListResponse> {
        return service.getJamOperasional()
    }

    fun editJamOperasional(id: Int, data: HashMap<String, Any?>): Observable<JamOperasionalResponse> {
        return service.editJamOperasional(id, data)
    }

    fun deleteJamOperasional(id: Int): Observable<EmptyResponse> {
        return service.deleteJamOperasional(id)
    }
}