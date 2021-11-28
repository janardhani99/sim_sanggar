package com.example.sanggar.presenter.platform_transaksi

import com.example.sanggar.common.doSubscribe
import com.example.sanggar.data.handler.common.ErrorHandler
import com.example.sanggar.data.handler.platform_transaksi.PlatformTransaksiHandler
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.platform_transaksi.PlatformTransaksiListResponse
import com.example.sanggar.data.model.platform_transaksi.PlatformTransaksiResponse
import com.example.sanggar.presenter.common.BasePresenter

class PlatformTransaksiPresenter(val view: PlatformTransaksiContract.View): BasePresenter(view), PlatformTransaksiContract.Presenter {
    val handler = PlatformTransaksiHandler()

    override fun addPlatformTransaksi(data: HashMap<String, Any?>) {
        handler.addPlatformTransaksi(data)
                .doSubscribe(object : ErrorHandler<PlatformTransaksiResponse>(this) {
                    override fun onNext(t: PlatformTransaksiResponse) {
                        view.platformTransaksiResponse(t)
                    }
                })
    }

    override fun getPlatformTransaksi() {
        handler.getPlatformTransaksi()
                .doSubscribe(object : ErrorHandler<PlatformTransaksiListResponse>(this){
                    override fun onNext(t: PlatformTransaksiListResponse) {
                        view.getPlatformTransaksiResponse(t)
                    }
                })
    }

    override fun deletePlatformTransaksi(id: Int) {
        handler.deletePlatformTransaksi(id)
                .doSubscribe(object : ErrorHandler<EmptyResponse>(this){
                    override fun onNext(t: EmptyResponse) {
                        view.deletePlatformTransaksiResponse(t)
                    }
                })
    }

    override fun editPlatformTransaksi(id: Int, data: HashMap<String, Any?>) {
        handler.editPlatformTransaksi(id, data)
                .doSubscribe(object : ErrorHandler<PlatformTransaksiResponse>(this){
                    override fun onNext(t: PlatformTransaksiResponse) {
                        view.platformTransaksiResponse(t)
                    }
                })
    }

}