package com.example.sanggar.presenter.absensi

import com.example.sanggar.common.doSubscribe
import com.example.sanggar.data.handler.common.ErrorHandler
import com.example.sanggar.data.handler.absensi.PertemuanHandler
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.absensi.PertemuanDataListResponse
import com.example.sanggar.data.model.absensi.PertemuanDataResponse
import com.example.sanggar.presenter.common.BasePresenter
import com.example.sanggar.presenter.pembelajaran.PembelajaranContract


class PertemuanPresenter(val view: PertemuanContract.View): BasePresenter(view), PertemuanContract.Presenter {
    val handler = PertemuanHandler()

    override fun addPertemuan(data: HashMap<String, Any?>) {
        handler.addPertemuan(data)
                .doSubscribe(object : ErrorHandler<PertemuanDataResponse>(this){
                    override fun onNext(t: PertemuanDataResponse) {
                        view.pertemuanResponse(t)
                    }
                })
    }

    override fun editPertemuan(id: Int, data: HashMap<String, Any?>) {
        handler.editPertemuan(id, data)
                .doSubscribe(object : ErrorHandler<PertemuanDataResponse>(this){
                    override fun onNext(t: PertemuanDataResponse) {
                        view.pertemuanResponse(t)
                    }
                })
    }

    override fun getPertemuan(jadwal_sanggar: Int) {
        handler.getPertemuan(jadwal_sanggar)
                .doSubscribe(object : ErrorHandler<PertemuanDataListResponse>(this){
                    override fun onNext(t: PertemuanDataListResponse) {
                        view.getPertemuanResponse(t)
                    }
                })
    }

    override fun deletePertemuan(id: Int) {
        handler.deletePertemuan(id)
                .doSubscribe(object: ErrorHandler<EmptyResponse>(this){
                    override fun onNext(t: EmptyResponse) {
                        view.deletePertemuanResponse(t)
                    }
                })
    }
}