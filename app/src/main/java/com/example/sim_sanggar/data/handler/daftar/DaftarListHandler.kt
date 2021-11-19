package com.example.sim_sanggar.data.handler.daftar

import com.example.sim_sanggar.presenter.daftar.DaftarListContract
import com.example.sim_sanggar.data.handler.common.BaseHandler
import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.daftar.DaftarListResponse
import com.example.sim_sanggar.data.model.daftar.DaftarResponse
import io.reactivex.Observable

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