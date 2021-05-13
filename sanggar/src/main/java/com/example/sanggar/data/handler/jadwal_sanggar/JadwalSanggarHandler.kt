package com.example.sanggar.data.handler.jadwal_sanggar

import com.example.sanggar.data.handler.common.BaseHandler
import com.example.sanggar.data.model.jadwal_sanggar.JadwalSanggarResponse
import com.example.sanggar.presenter.jadwal_sanggar.JadwalSanggarContract
import io.reactivex.Observable


class JadwalSanggarHandler: BaseHandler() {
    val service = getClient().create((JadwalSanggarContract.Handler::class.java))

    fun tambahJadwalSanggar(data: HashMap<String, Any?>): Observable<JadwalSanggarResponse> {
        return service.tambahJadwalSanggar(data)
    }

}