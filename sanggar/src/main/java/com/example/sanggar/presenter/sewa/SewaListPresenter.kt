package com.example.sanggar.presenter.sewa

import com.example.sanggar.common.doSubscribe
import com.example.sanggar.data.handler.common.ErrorHandler
import com.example.sanggar.data.handler.sewa.SewaListHandler
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.sewa.SewaListResponse
import com.example.sanggar.data.model.sewa.SewaResponse
import com.example.sanggar.presenter.common.BasePresenter

class SewaListPresenter(val view: SewaListContract.View): BasePresenter(view), SewaListContract.Presenter {
    val handler = SewaListHandler()

    override fun addListSewa(data: HashMap<String, Any?>) {
        handler.addListSewa(data)
                .doSubscribe(object: ErrorHandler<SewaResponse>(this){
                    override fun onNext(t: SewaResponse) {
                        view.sewaListResponse(t)
                    }
                })
    }

    override fun getListSewa(status: String) {
        handler.getListSewa(status)
                .doSubscribe(object: ErrorHandler<SewaListResponse>(this){
                    override fun onNext(t: SewaListResponse) {
                        view.getSewaListResponse(t)
                    }
                })
    }

    override fun deleteListSewa(id: Int) {
        handler.deleteListSewa(id)
                .doSubscribe(object: ErrorHandler<EmptyResponse>(this){
                    override fun onNext(t: EmptyResponse) {
                        view.deleteSewaListResponse(t)
                    }
                })
    }

    override fun editStatusSewa(id: Int, data: HashMap<String, Any?>) {
        handler.editStatusSewa(id, data)
                .doSubscribe(object : ErrorHandler<SewaResponse>(this){
                    override fun onNext(t: SewaResponse) {
                        view.sewaListResponse(t)
                    }
                })
    }

    override fun getTanggalTersewa(tanggal: String) {
        tanggal.let {
            handler.getTanggalTersewa(it)
                    .doSubscribe(object : ErrorHandler<SewaListResponse>(this){
                        override fun onNext(t: SewaListResponse) {
                            view.getSewaListResponse(t)
                        }
                    })
        }
    }
}