package com.example.sanggar.data.handler.daftar

import com.example.sanggar.data.handler.common.BaseHandler
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.daftar.DaftarListResponse
import com.example.sanggar.data.model.daftar.DaftarResponse
import com.example.sanggar.presenter.daftar.DaftarListContract
import io.reactivex.Observable

class DaftarListHandler: BaseHandler() {

    val service = getClient().create(DaftarListContract.Handler::class.java)

    fun addListDaftar(data: HashMap<String, Any?>): Observable<DaftarResponse> {
        return service.addListDaftar(data)
    }

    fun getListDaftar(status: String): Observable<DaftarListResponse> {
        return service.getListDaftar(status)
    }

    fun getAnakTerdaftar(): Observable<DaftarListResponse> {
        return service.getAnakTerdaftar()
    }

    fun editStatusDaftar(id: Int, data: HashMap<String, Any?>): Observable<DaftarResponse> {
        return service.editStatusDaftar(id, data)
    }

    fun deleteListDaftar(id: Int): Observable<EmptyResponse> {
        return service.deleteListDaftar(id)
    }
}