package com.example.sanggar.data.handler.pembelajaran

import com.example.sanggar.data.handler.common.BaseHandler
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.pembelajaran.PembelajaranListResponse
import com.example.sanggar.data.model.pembelajaran.PembelajaranResponse
import com.example.sanggar.presenter.pembelajaran.PembelajaranContract
import io.reactivex.Observable

import kotlin.collections.HashMap

class PembelajaranHandler:BaseHandler() {
    val service = getClient().create(PembelajaranContract.Handler::class.java)

    fun addPembelajaran(data: HashMap<String, Any?>): Observable<PembelajaranResponse> {
        return service.addPembelajaran(data)
    }

    fun editPembelajaran(id: Int, data: HashMap<String, Any?>): Observable<PembelajaranResponse> {
        return service.editPembelajaran(id, data)
    }

    fun getPembelajaran(): Observable<PembelajaranListResponse> {
        return service.getPembelajaran()
    }

    fun deletePembelajaran(id: Int): Observable<EmptyResponse> {
        return service.deletePembelajaran(id)
    }

}