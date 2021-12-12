package com.example.sim_sanggar.data.handler.sewa

import com.example.sim_sanggar.data.handler.common.BaseHandler
import com.example.sim_sanggar.data.model.sewa.SewaListResponse
import com.example.sim_sanggar.data.model.sewa.SewaResponse
import com.example.sim_sanggar.presenter.sewa.SewaContract
import io.reactivex.Observable
import okhttp3.MultipartBody

class SewaHandler: BaseHandler() {

    val service = getClient().create(SewaContract.Handler::class.java)

    fun addSewa(data: HashMap<String, Any?>): Observable<SewaResponse> {
        return service.addSewa(data)
    }

    fun getSewa(): Observable<SewaListResponse> {
        return service.getSewa()
    }

    fun uploadBukti(id: Int, data: HashMap<String, Any?>): Observable<SewaResponse> {
        return service.uploadBukti(id, data)
    }

    fun addImage(id: Int, part: MultipartBody.Part): Observable<SewaResponse> {
        return service.addImage(id, part)
    }
}