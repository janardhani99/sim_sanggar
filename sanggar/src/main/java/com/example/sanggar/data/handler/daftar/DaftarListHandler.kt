package com.example.sanggar.data.handler.daftar

import com.example.sanggar.data.handler.common.BaseHandler
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.daftar.DaftarListResponse
import com.example.sanggar.data.model.daftar.DaftarResponse
import com.example.sanggar.presenter.daftar.DaftarListContract
import io.reactivex.Observable
import kotlin.collections.HashMap

class DaftarListHandler: BaseHandler() {

    val service = getClient().create(DaftarListContract.Handler::class.java)

    fun addListDaftar(data: HashMap<String, Any?>): Observable<DaftarResponse> {
        return service.addListDaftar(data)
    }

    fun getListDaftar(): Observable<DaftarListResponse> {
        return service.getListDaftar()
    }

    fun deleteListDaftar(id: Int): Observable<EmptyResponse> {
        return service.deleteListDaftar(id)
    }
}