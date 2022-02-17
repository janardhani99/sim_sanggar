package com.example.sanggar.presenter.absensi

import com.example.sanggar.common.doSubscribe
import com.example.sanggar.data.handler.absensi.ProgressAnakHandler
import com.example.sanggar.data.handler.common.ErrorHandler
import com.example.sanggar.data.model.absensi.ProgressAnakListResponse
import com.example.sanggar.data.model.absensi.ProgressAnakResponse
import com.example.sanggar.presenter.common.BasePresenter

class ProgressAnakPresenter(val view: ProgressAnakContract.View):BasePresenter(view), ProgressAnakContract.Presenter {

    val handler = ProgressAnakHandler()

    override fun addProgressAnak(data: HashMap<String, Any?>) {
        handler.addProgressAnak(data)
                .doSubscribe(object : ErrorHandler<ProgressAnakResponse>(this){
                    override fun onNext(t: ProgressAnakResponse) {
                        view.progressAnakResponse(t)
                    }
                })
    }

    override fun editProgressAnak(id: Int, data: HashMap<String, Any?>) {
        handler.editProgressAnak(id, data)
                .doSubscribe(object : ErrorHandler<ProgressAnakResponse>(this){
                    override fun onNext(t: ProgressAnakResponse) {
                        view.progressAnakResponse(t)
                    }
                })
    }

    override fun getProgressAnak() {
        handler.getProgressAnak()
                .doSubscribe(object : ErrorHandler<ProgressAnakListResponse>(this){
                    override fun onNext(t: ProgressAnakListResponse) {
                        view.getProgressAnakResponse(t)
                    }
                })
    }

    override fun getDetailProgress(data: HashMap<String, Any?>) {
        handler.getDetailProgress(data)
                .doSubscribe(object : ErrorHandler<ProgressAnakResponse>(this){
                    override fun onNext(t: ProgressAnakResponse) {
                        view.getDetailProgress(t)
                    }
                })
    }
}