package com.example.sim_sanggar.data.handler.report_anak

import com.example.sim_sanggar.data.handler.common.BaseHandler
import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.report_anak.PertemuanDataListResponse
import com.example.sim_sanggar.data.model.report_anak.PertemuanDataResponse
import com.example.sim_sanggar.presenter.report_anak.PertemuanContract

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

    fun getPertemuan(jadwal_sanggar: Int): Observable<PertemuanDataListResponse> {
        return service.getPertemuan(jadwal_sanggar)
    }

    fun deletePertemuan(id: Int): Observable<EmptyResponse> {
        return service.deletePertemuan(id)
    }

}