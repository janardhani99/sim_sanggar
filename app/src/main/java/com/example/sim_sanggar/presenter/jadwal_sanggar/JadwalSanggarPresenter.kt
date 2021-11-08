package com.example.sim_sanggar.presenter.jadwal_sanggar

import com.example.sim_sanggar.common.doSubscribe
import com.example.sim_sanggar.data.handler.common.ErrorHandler
import com.example.sim_sanggar.data.handler.jadwal_sanggar.JadwalSanggarHandler
import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.jadwal_sanggar.JadwalSanggarListResponse
import com.example.sim_sanggar.data.model.jadwal_sanggar.JadwalSanggarResponse
import com.example.sim_sanggar.presenter.common.BasePresenter

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

    override fun getJadwal() {
        handler.getJadwal()
                .doSubscribe(object : ErrorHandler<JadwalSanggarListResponse>(this){
                    override fun onNext(t: JadwalSanggarListResponse) {
                        view.getJadwalSanggarResponse(t)
                    }
                })
    }

    override fun deleteJadwal(id: Int) {
        handler.deleteJadwal(id)
                .doSubscribe(object: ErrorHandler<EmptyResponse>(this){
                    override fun onNext(t: EmptyResponse) {
                        view.deleteJadwalResponse(t)
                    }
                })
    }

    override fun editJadwal(id: Int, data: HashMap<String, Any?>) {
        handler.editJadwal(id, data)
                .doSubscribe(object : ErrorHandler<JadwalSanggarResponse>(this){
                    override fun onNext(t: JadwalSanggarResponse) {
                        view.jadwalSanggarResponse(t)
                    }
                })
    }
}