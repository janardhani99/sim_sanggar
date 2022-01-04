package com.example.sanggar.presenter.pembelajaran

import com.example.sanggar.common.doSubscribe
import com.example.sanggar.data.handler.common.ErrorHandler
import com.example.sanggar.data.handler.pembelajaran.PembelajaranHandler
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.pembelajaran.PembelajaranListResponse
import com.example.sanggar.data.model.pembelajaran.PembelajaranResponse
import com.example.sanggar.presenter.common.BasePresenter


class PembelajaranPresenter(val view: PembelajaranContract.View): BasePresenter(view), PembelajaranContract.Presenter {
    val handler = PembelajaranHandler()

    override fun addPembelajaran(data: HashMap<String, Any?>) {
        handler.addPembelajaran(data)
                .doSubscribe(object : ErrorHandler<PembelajaranResponse>(this){
                    override fun onNext(t: PembelajaranResponse){
                        view.pembelajaranResponse(t)
                    }
                })
    }

    override fun editPembelajaran(id: Int, data: HashMap<String, Any?>) {
        handler.editPembelajaran(id, data)
                .doSubscribe(object : ErrorHandler<PembelajaranResponse>(this){
                    override fun onNext(t: PembelajaranResponse) {
                        view.pembelajaranResponse(t)
                    }
                })
    }

    override fun getPembelajaran() {
        handler.getPembelajaran()
                .doSubscribe(object : ErrorHandler<PembelajaranListResponse>(this){
                    override fun onNext(t: PembelajaranListResponse) {
                        view.getPembelajaranResponse(t)
                    }
                })
    }

    override fun deletePembelajaran(id: Int) {
        handler.deletePembelajaran(id)
                .doSubscribe(object : ErrorHandler<EmptyResponse>(this){
                    override fun onNext(t: EmptyResponse) {
                        view.deletePembelajaranResponse(t)
                    }
                })
    }

}