package com.example.sim_sanggar.data.handler.sewa

import com.example.sim_sanggar.data.handler.common.BaseHandler
import com.example.sim_sanggar.data.model.sewa.SewaResponse
import com.example.sim_sanggar.presenter.sewa.SewaContract
import io.reactivex.Observable

class SewaHandler: BaseHandler() {

    val service = getClient().create(SewaContract.Handler::class.java)

    fun addSewa(data: HashMap<String, Any?>): Observable<SewaResponse> {
        return service.addSewa(data)
    }
}