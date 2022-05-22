package com.example.sim_sanggar.data.handler.report_anak

import com.example.sim_sanggar.data.handler.common.BaseHandler
import com.example.sim_sanggar.data.model.report_anak.ReportAnakListResponse
import com.example.sim_sanggar.data.model.report_anak.ReportAnakResponse
import com.example.sim_sanggar.presenter.report_anak.ReportAnakContract
import io.reactivex.Observable
import kotlin.collections.HashMap

class ReportAnakHandler: BaseHandler() {
    val service = getClient().create(ReportAnakContract.Handler::class.java)

    fun addProgressAnak(data: HashMap<String, Any?>): Observable<ReportAnakResponse> {
        return service.addProgressAnak(data)
    }

    fun editProgressAnak(id: Int, data: HashMap<String, Any?>): Observable<ReportAnakResponse> {
        return service.editProgressAnak(id, data)
    }

    fun getProgressAnak(): Observable<ReportAnakListResponse> {
        return service.getProgressAnak()
    }

    fun getDetailProgress(data: HashMap<String, Any?>): Observable<ReportAnakResponse> {
        return service.getDetailProgress(data)
    }

}