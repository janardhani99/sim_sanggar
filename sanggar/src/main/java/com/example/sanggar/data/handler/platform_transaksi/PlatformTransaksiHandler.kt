package com.example.sanggar.data.handler.platform_transaksi

import com.example.sanggar.data.handler.common.BaseHandler
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.platform_transaksi.PlatformTransaksiListResponse
import com.example.sanggar.data.model.platform_transaksi.PlatformTransaksiResponse
import com.example.sanggar.presenter.platform_transaksi.PlatformTransaksiContract
import io.reactivex.Observable

class PlatformTransaksiHandler: BaseHandler() {

    val service = getClient().create(PlatformTransaksiContract.Handler::class.java)

    fun addPlatformTransaksi(data: HashMap<String, Any?>): Observable<PlatformTransaksiResponse> {
        return service.addPlatformTransaksi(data)
    }

    fun getPlatformTransaksi(): Observable<PlatformTransaksiListResponse> {
        return service.getPlatformTransaksi()
    }

    fun deletePlatformTransaksi(id: Int): Observable<EmptyResponse> {
        return service.deletePlatformTransaksi(id)
    }

    fun editPlatformTransaksi(id: Int, data: HashMap<String, Any?>): Observable<PlatformTransaksiResponse> {
        return service.editPlatformTransaksi(id, data)
    }

}