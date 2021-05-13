package com.example.sanggar.presenter.jadwal_sanggar

import com.example.sanggar.common.doSubscribe
import com.example.sanggar.data.handler.common.ErrorHandler
import com.example.sanggar.data.handler.jadwal_sanggar.JadwalSanggarHandler
import com.example.sanggar.data.model.jadwal_sanggar.JadwalSanggarResponse
import com.example.sanggar.presenter.common.BasePresenter


class JadwalSanggarPresenter(val view: JadwalSanggarContract.View): BasePresenter(view), JadwalSanggarContract.Presenter {

    val handler = JadwalSanggarHandler()

    override fun tambahJadwalSanggar(data: HashMap<String, Any?>) {
        handler.tambahJadwalSanggar(data)
                .doSubscribe(object : ErrorHandler<JadwalSanggarResponse>(this) {
                  override fun onNext(t: JadwalSanggarResponse) {
                      view.jadwalSanggarResponse(t)
                  }
                })
    }
}