package com.example.sanggar.presenter.kegiatan

import com.example.sanggar.common.doSubscribe
import com.example.sanggar.data.handler.common.ErrorHandler
import com.example.sanggar.data.handler.kegiatan.KegiatanHandler
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.kegiatan.KegiatanListResponse
import com.example.sanggar.data.model.kegiatan.KegiatanResponse
import com.example.sanggar.presenter.common.BasePresenter

class KegiatanPresenter(val view: KegiatanContract.View): BasePresenter(view), KegiatanContract.Presenter {

    val handler = KegiatanHandler()

    override fun addKegiatan(data: HashMap<String, Any?>) {
        handler.addKegiatan(data)
                .doSubscribe(object : ErrorHandler<KegiatanResponse>(this) {
                    override fun onNext(t: KegiatanResponse) {
                        view.kegiatanResponse(t)
                    }
                })
    }

    override fun getKegiatan() {
        handler.getKegiatan()
                .doSubscribe(object : ErrorHandler<KegiatanListResponse>(this){
                    override fun onNext(t: KegiatanListResponse) {
                        view.getKegiatanResponse(t)
                    }
                })

    }

    override fun deleteKegiatan(id: Int) {
        handler.deleteKegiatan(id)
                .doSubscribe(object : ErrorHandler<EmptyResponse>(this){
                    override fun onNext(t: EmptyResponse) {
                        view.deleteKegiatanResponse(t)
                    }
                })
    }

    override fun editKegiatan(id: Int, data: HashMap<String, Any?>) {
        handler.editKegiatan(id, data)
                .doSubscribe(object : ErrorHandler<KegiatanResponse>(this){
                    override fun onNext(t: KegiatanResponse) {
                        view.kegiatanResponse(t)
                    }
                })
    }
}