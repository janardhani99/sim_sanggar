package com.example.sim_sanggar.data.handler.fasilitas

import com.example.sim_sanggar.data.handler.common.BaseHandler
import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.fasilitas.FasilitasListResponse
import com.example.sim_sanggar.data.model.fasilitas.FasilitasResponse
import com.example.sim_sanggar.presenter.fasilitas.FasilitasContract
import io.reactivex.Observable
import okhttp3.MultipartBody

class FasilitasHandler: BaseHandler() {
    val service = getClient().create(FasilitasContract.Handler::class.java)

    fun addFasilitas(data: HashMap<String, Any?>): Observable<FasilitasResponse> {
        return service.addFasilitas(data)
    }

    fun getFasilitas(): Observable<FasilitasListResponse> {
        return service.getFasilitas()
    }

    fun deleteFasilitas(id: Int): Observable<EmptyResponse> {
        return service.deleteFasilitas(id)
    }

    fun editFasilitas(id: Int, data: HashMap<String, Any?>): Observable<FasilitasResponse> {
        return service.editFasilitas(id, data)
    }

    fun addImage(id: Int, part: MultipartBody.Part): Observable<FasilitasResponse> {
        return service.addImage(id, part)
    }
}