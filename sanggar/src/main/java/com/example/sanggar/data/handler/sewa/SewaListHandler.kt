package com.example.sanggar.data.handler.sewa

import com.example.sanggar.data.handler.common.BaseHandler
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.daftar.DaftarResponse
import com.example.sanggar.data.model.sewa.SewaListResponse
import com.example.sanggar.data.model.sewa.SewaResponse
import com.example.sanggar.presenter.sewa.SewaListContract
import io.reactivex.Observable

class SewaListHandler : BaseHandler(){
    val service = getClient().create(SewaListContract.Handler::class.java)

    fun addListSewa(data: HashMap<String, Any?>): Observable<SewaResponse> {
        return service.addListSewa(data)
    }

    fun getListSewa(status: String): Observable<SewaListResponse> {
        return service.getListSewa(status)
    }

    fun editStatusSewa(id: Int, data: HashMap<String, Any?>): Observable<SewaResponse> {
        return service.editStatusSewa(id, data)
    }

    fun deleteListSewa(id: Int): Observable<EmptyResponse> {
        return service.deleteListSewa(id)
    }
}