package com.example.sanggar.presenter.daftar

import com.example.sanggar.common.doSubscribe
import com.example.sanggar.data.handler.common.ErrorHandler
import com.example.sanggar.data.handler.daftar.DaftarListHandler
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.daftar.DaftarListItem
import com.example.sanggar.data.model.daftar.DaftarListResponse
import com.example.sanggar.data.model.daftar.DaftarResponse
import com.example.sanggar.presenter.common.BaseContract
import com.example.sanggar.presenter.common.BasePresenter


class DaftarListPresenter(val view: DaftarListContract.View): BasePresenter(view), BaseContract.Presenter {

    val handler = DaftarListHandler()

    override fun addDaftarList(data: HashMap<String, Any?>) {
        handler.addListDaftar(data)
                .doSubscribe(object: ErrorHandler<DaftarResponse>(this){
                    override fun onNext(t: DaftarResponse) {
                        view.daftarListResponse(t)
                    }
                })
    }

    override fun getDaftarList() {
        handler.getListDaftar()
                .doSubscribe(object: ErrorHandler<DaftarListResponse>(this){
                    override fun onNext(t: DaftarListResponse) {
                        view.getDaftarListResponse(t)
                    }
                })
    }

    override fun deleteDaftarList(id: Int) {
        handler.deleteListDaftar(id)
                .doSubscribe(object: ErrorHandler<EmptyResponse>(this){
                    override fun onNext(t: EmptyResponse) {
                        view.deleteDaftarListResponse(t)
                    }
                })
    }
}