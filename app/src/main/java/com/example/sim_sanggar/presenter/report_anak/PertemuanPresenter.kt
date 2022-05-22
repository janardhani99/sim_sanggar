package com.example.sim_sanggar.presenter.report_anak

import com.example.sim_sanggar.common.doSubscribe
import com.example.sim_sanggar.data.handler.common.ErrorHandler
import com.example.sim_sanggar.data.handler.report_anak.PertemuanHandler
import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.report_anak.PertemuanDataListResponse
import com.example.sim_sanggar.data.model.report_anak.PertemuanDataResponse
import com.example.sim_sanggar.presenter.common.BasePresenter
import com.example.sim_sanggar.presenter.pembelajaran.PembelajaranContract


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