package com.example.sanggar.data.handler.sanggar

import com.example.sanggar.data.handler.common.BaseHandler
import com.example.sanggar.data.model.sanggar.ProfilSanggarListResponse
import com.example.sanggar.data.model.sanggar.ProfilSanggarResponse
import com.example.sanggar.presenter.sanggar.ProfilSanggarContract
import io.reactivex.Observable

class ProfilSanggarHandler: BaseHandler() {
    val service = getClient().create(ProfilSanggarContract.Handler::class.java)

    fun addProfilSanggar(data: HashMap<String,  Any?>): Observable<ProfilSanggarResponse> {
        return service.addProfilSanggar(data)
    }

    fun getProfilSanggar(): Observable<ProfilSanggarListResponse> {
        return service.getProfilSanggar()
    }

    fun editProfilSanggar(id: Int, data: HashMap<String, Any?>):Observable<ProfilSanggarResponse> {
        return service.editProfilSanggar(id, data)
    }

}