package com.example.sim_sanggar.presenter.platform_transaksi

import com.example.sim_sanggar.data.handler.platform_transaksi.PlatformTransaksiHandler
import com.example.sim_sanggar.common.doSubscribe
import com.example.sim_sanggar.data.handler.common.ErrorHandler
import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.platform_transaksi.PlatformTransaksiListResponse
import com.example.sim_sanggar.data.model.platform_transaksi.PlatformTransaksiResponse
import com.example.sim_sanggar.presenter.common.BasePresenter

class PlatformTransaksiPresenter(val view: PlatformTransaksiContract.View): BasePresenter(view), PlatformTransaksiContract.Presenter {
    val handler = PlatformTransaksiHandler()

    override fun getPlatformTransaksi() {
        handler.getPlatformTransaksi()
                .doSubscribe(object : ErrorHandler<PlatformTransaksiListResponse>(this){
                    override fun onNext(t: PlatformTransaksiListResponse) {
                        view.getPlatformTransaksiResponse(t)
                    }
                })
    }

}