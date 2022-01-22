package com.example.sanggar.data.handler.absensi

import com.example.sanggar.data.handler.common.BaseHandler
import com.example.sanggar.data.model.absensi.ProgressAnakListResponse
import com.example.sanggar.data.model.absensi.ProgressAnakResponse
import com.example.sanggar.presenter.absensi.ProgressAnakContract
import io.reactivex.Observable
import java.util.*
import kotlin.collections.HashMap

class ProgressAnakHandler: BaseHandler() {
    val service = getClient().create(ProgressAnakContract.Handler::class.java)

    fun addProgressAnak(data: HashMap<String, Any?>): Observable<ProgressAnakResponse> {
        return service.addProgressAnak(data)
    }

    fun editProgressAnak(id: Int, data: HashMap<String, Any?>): Observable<ProgressAnakResponse> {
        return service.editProgressAnak(id, data)
    }

    fun getProgressAnak(): Observable<ProgressAnakListResponse> {
        return service.getProgressAnak()
    }

}