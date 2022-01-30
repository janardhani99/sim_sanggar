package com.example.sim_sanggar.data.handler.jam_operasional

import com.example.sim_sanggar.data.handler.common.BaseHandler
import com.example.sim_sanggar.data.model.jam_operasional.TanggalLiburListResponse
import com.example.sim_sanggar.data.model.jam_operasional.TanggalLiburResponse
import com.example.sim_sanggar.presenter.jam_operasional.TanggalLiburContract

import io.reactivex.Observable

class TanggalLiburHandler: BaseHandler() {

    val service = getClient().create(TanggalLiburContract.Handler::class.java)

    fun tambahTanggalLibur(data: HashMap<String, Any?>): Observable<TanggalLiburResponse> {
        return service.tambahTanggalLibur(data)
    }

    fun getTanggalLibur(): Observable<TanggalLiburListResponse> {
        return service.getTanggalLibur()
    }

    fun editTanggalLibur(id: Int, data: HashMap<String, Any?>): Observable<TanggalLiburResponse> {
        return service.editTanggalLibur(id, data)
    }
}