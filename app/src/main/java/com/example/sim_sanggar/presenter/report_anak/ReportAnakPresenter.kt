package com.example.sim_sanggar.presenter.report_anak

import com.example.sim_sanggar.common.doSubscribe
import com.example.sim_sanggar.data.handler.report_anak.ReportAnakHandler
import com.example.sim_sanggar.data.handler.common.ErrorHandler
import com.example.sim_sanggar.data.model.report_anak.ReportAnakListResponse
import com.example.sim_sanggar.data.model.report_anak.ReportAnakResponse
import com.example.sim_sanggar.presenter.common.BasePresenter

class ReportAnakPresenter(val view: ReportAnakContract.View):BasePresenter(view), ReportAnakContract.Presenter {

    val handler = ReportAnakHandler()

    override fun addProgressAnak(data: HashMap<String, Any?>) {
        handler.addProgressAnak(data)
                .doSubscribe(object : ErrorHandler<ReportAnakResponse>(this){
                    override fun onNext(t: ReportAnakResponse) {
                        view.progressAnakResponse(t)
                    }
                })
    }

    override fun editProgressAnak(id: Int, data: HashMap<String, Any?>) {
        handler.editProgressAnak(id, data)
                .doSubscribe(object : ErrorHandler<ReportAnakResponse>(this){
                    override fun onNext(t: ReportAnakResponse) {
                        view.progressAnakResponse(t)
                    }
                })
    }

    override fun getProgressAnak() {
        handler.getProgressAnak()
                .doSubscribe(object : ErrorHandler<ReportAnakListResponse>(this){
                    override fun onNext(t: ReportAnakListResponse) {
                        view.getProgressAnakResponse(t)
                    }
                })
    }

    override fun getDetailProgress(data: HashMap<String, Any?>) {
        handler.getDetailProgress(data)
                .doSubscribe(object : ErrorHandler<ReportAnakResponse>(this){
                    override fun onNext(t: ReportAnakResponse) {
                        view.getDetailProgress(t)
                    }
                })
    }

    override fun loadDataSearch(anak: Int) {
        handler.loadDataSearch(anak)
                .doSubscribe(object : ErrorHandler<ReportAnakListResponse>(this){
                    override fun onNext(t: ReportAnakListResponse) {
                        view.loadDataSearch(t)
                    }
                })
    }
}