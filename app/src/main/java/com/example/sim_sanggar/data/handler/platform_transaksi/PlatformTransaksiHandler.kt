package com.example.sim_sanggar.data.handler.platform_transaksi

import com.example.sim_sanggar.data.handler.common.BaseHandler
import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.platform_transaksi.PlatformTransaksiListResponse
import com.example.sim_sanggar.data.model.platform_transaksi.PlatformTransaksiResponse
import com.example.sim_sanggar.presenter.platform_transaksi.PlatformTransaksiContract
import io.reactivex.Observable

class PlatformTransaksiHandler: BaseHandler() {

    val service = getClient().create(PlatformTransaksiContract.Handler::class.java)

    fun getPlatformTransaksi(): Observable<PlatformTransaksiListResponse> {
        return service.getPlatformTransaksi()
    }


}