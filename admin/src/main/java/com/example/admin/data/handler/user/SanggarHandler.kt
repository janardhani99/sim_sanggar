package com.example.admin.data.handler.user


import com.example.admin.data.handler.common.BaseHandler
import com.example.admin.data.model.user.SanggarListResponse
import com.example.admin.presenter.user.SanggarContract
import io.reactivex.Observable

class SanggarHandler: BaseHandler() {
    val service = getClient().create(SanggarContract.Handler::class.java)

    fun getSanggar(): Observable<SanggarListResponse> {
        return service.getSanggar()
    }
}