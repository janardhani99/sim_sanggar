package com.example.sim_sanggar.data.handler.sanggar

import com.example.sim_sanggar.data.handler.common.BaseHandler
import com.example.sim_sanggar.data.model.sanggar.SanggarListResponse
import com.example.sim_sanggar.presenter.sanggar.SanggarContract
import io.reactivex.Observable

class SanggarHandler: BaseHandler() {
    val service = getClient().create(SanggarContract.Handler::class.java)

    fun getSanggar(): Observable<SanggarListResponse> {
        return service.getSanggar()
    }
}