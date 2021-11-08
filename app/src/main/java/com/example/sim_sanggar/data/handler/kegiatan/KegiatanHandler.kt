package com.example.sim_sanggar.data.handler.kegiatan

import com.example.sim_sanggar.data.handler.common.BaseHandler
import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.kegiatan.KegiatanListResponse
import com.example.sim_sanggar.data.model.kegiatan.KegiatanResponse
import com.example.sim_sanggar.presenter.kegiatan.KegiatanContract
import io.reactivex.Observable
import okhttp3.MultipartBody

class KegiatanHandler: BaseHandler() {
    val service = getClient().create(KegiatanContract.Handler::class.java)

    fun addKegiatan(data: HashMap<String, Any?>): Observable<KegiatanResponse> {
        return service.addKegiatan(data)
    }

    fun getKegiatan(): Observable<KegiatanListResponse> {
        return service.getKegiatan()
    }

    fun deleteKegiatan(id: Int): Observable<EmptyResponse> {
        return service.deleteKegiatan(id)
    }

    fun editKegiatan(id: Int, data: HashMap<String, Any?>): Observable<KegiatanResponse> {
        return service.editKegiatan(id, data)
    }

    fun addImage(id: Int, part: MultipartBody.Part): Observable<KegiatanResponse> {
        return service.addImage(id, part)
    }
}