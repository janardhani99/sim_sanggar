package com.example.sim_sanggar.data.handler.jadwal_sanggar

import com.example.sim_sanggar.data.handler.common.BaseHandler
import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.jadwal_sanggar.JadwalSanggarListResponse
import com.example.sim_sanggar.data.model.jadwal_sanggar.JadwalSanggarResponse
import com.example.sim_sanggar.presenter.jadwal_sanggar.JadwalSanggarContract
import io.reactivex.Observable


class JadwalSanggarHandler: BaseHandler() {
    val service = getClient().create((JadwalSanggarContract.Handler::class.java))

    fun tambahJadwalSanggar(data: HashMap<String, Any?>): Observable<JadwalSanggarResponse> {
        return service.tambahJadwalSanggar(data)
    }

    fun getJadwal(): Observable<JadwalSanggarListResponse> {
        return service.getJadwal()
    }

    fun deleteJadwal(id: Int): Observable<EmptyResponse> {
        return service.deleteJadwal(id)
    }

    fun editJadwal(id: Int, data: HashMap<String, Any?>): Observable<JadwalSanggarResponse> {
        return service.editJadwal(id, data)
    }
}