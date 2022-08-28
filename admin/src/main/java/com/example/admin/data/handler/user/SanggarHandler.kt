package com.example.admin.data.handler.user


import com.example.admin.data.handler.common.BaseHandler
import com.example.admin.data.model.user.SanggarListResponse
import com.example.admin.data.model.user.SanggarResponse
import com.example.admin.presenter.user.SanggarContract
import io.reactivex.Observable

class SanggarHandler: BaseHandler() {
    val service = getClient().create(SanggarContract.Handler::class.java)

    fun getSanggar(): Observable<SanggarListResponse> {
        return service.getSanggar()
    }

    fun addSanggar(data: HashMap<String, Any?>): Observable<SanggarResponse> {
        return service.addSanggar(data)
    }

    fun editSanggar(id: Int, data: HashMap<String, Any?>): Observable<SanggarResponse> {
        return service.editProfilSanggar(id, data)
    }
}