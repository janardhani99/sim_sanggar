package com.example.sanggar.presenter.daftar

import com.example.sanggar.common.doSubscribe
import com.example.sanggar.data.handler.common.ErrorHandler
import com.example.sanggar.data.handler.daftar.DaftarListHandler
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.daftar.DaftarListResponse
import com.example.sanggar.data.model.daftar.DaftarResponse
import com.example.sanggar.presenter.common.BasePresenter

class DaftarListPresenter(val view: DaftarListContract.View): BasePresenter(view), DaftarListContract.Presenter {

    val handler = DaftarListHandler()

    override fun addListDaftar(data: HashMap<String, Any?>) {
        handler.addListDaftar(data)
                .doSubscribe(object: ErrorHandler<DaftarResponse>(this){
                    override fun onNext(t: DaftarResponse) {
                        view.daftarListResponse(t)
                    }
                })
    }


    override fun getListDaftar(status: String) {
        status?.let {
            handler.getListDaftar(it)
                    .doSubscribe(object: ErrorHandler<DaftarListResponse>(this){
                        override fun onNext(t: DaftarListResponse) {
                            view.getDaftarListResponse(t)
                        }
                    })
        }
    }


    override fun getAnakTerdaftar() {
        handler.getAnakTerdaftar()
                .doSubscribe(object: ErrorHandler<DaftarListResponse>(this){
                    override fun onNext(t: DaftarListResponse) {
                        view.getAnakTerdaftarResponse(t)
                    }
                })
    }

    override fun editStatusDaftar(id: Int, data: HashMap<String, Any?>) {
        handler.editStatusDaftar(id, data)
                .doSubscribe(object : ErrorHandler<DaftarResponse>(this){
                    override fun onNext(t: DaftarResponse) {
                        view.daftarListResponse(t)
                    }
                })
    }

    override fun deleteListDaftar(id: Int) {
        handler.deleteListDaftar(id)
                .doSubscribe(object: ErrorHandler<EmptyResponse>(this){
                    override fun onNext(t: EmptyResponse) {
                        view.deleteDaftarListResponse(t)
                    }
                })
    }
}