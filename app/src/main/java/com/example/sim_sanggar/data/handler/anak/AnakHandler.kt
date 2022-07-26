package com.example.sim_sanggar.data.handler.anak

import com.example.sim_sanggar.data.handler.common.BaseHandler
import com.example.sim_sanggar.data.model.anak.AnakListResponse
import com.example.sim_sanggar.data.model.anak.AnakResponse
import com.example.sim_sanggar.presenter.anak.AnakContract
import io.reactivex.Observable

class AnakHandler: BaseHandler() {

    val service = getClient().create(AnakContract.Handler::class.java)

    fun addAnak(data: HashMap<String, Any?>): Observable<AnakResponse> {
        return service.addAnak(data)
    }

    fun getAnak(): Observable<AnakListResponse> {
        return service.getAnak()
    }

    fun editAnak(id: Int, data: HashMap<String, Any?>): Observable<AnakResponse> {
        return service.editAnak(id, data)
    }
}