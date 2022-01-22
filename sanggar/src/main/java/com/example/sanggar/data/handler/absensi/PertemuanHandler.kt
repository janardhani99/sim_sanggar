package com.example.sanggar.data.handler.absensi

import com.example.sanggar.data.handler.common.BaseHandler
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.absensi.PertemuanDataListResponse
import com.example.sanggar.data.model.absensi.PertemuanDataResponse
import com.example.sanggar.presenter.absensi.PertemuanContract
import io.reactivex.Observable
import kotlin.collections.HashMap

class PertemuanHandler:BaseHandler() {

    val service = getClient().create(PertemuanContract.Handler::class.java)


    fun addPertemuan(data: HashMap<String, Any?>): Observable<PertemuanDataResponse> {
       return service.addPertemuan(data)
    }

    fun editPertemuan(id: Int, data: HashMap<String, Any?>): Observable<PertemuanDataResponse> {
        return service.editPertemuan(id, data)
    }

    fun getPertemuan(): Observable<PertemuanDataListResponse> {
        return service.getPertemuan()
    }

    fun deletePertemuan(id: Int): Observable<EmptyResponse> {
        return service.deletePertemuan(id)
    }

}