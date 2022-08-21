package com.example.sanggar.data.handler.studio

import com.example.sanggar.data.handler.common.BaseHandler
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.studio.StudioListResponse
import com.example.sanggar.data.model.studio.StudioResponse
import com.example.sanggar.presenter.studio.StudioContract
import io.reactivex.Observable
import okhttp3.MultipartBody

class StudioHandler:BaseHandler() {
    val service = getClient().create(StudioContract.Handler::class.java)

    fun addStudio(data: HashMap<String, Any?>): Observable<StudioResponse> {
        return service.addStudio(data)
    }

    fun getStudio(): Observable<StudioListResponse> {
        return service.getStudio()
    }

    fun deleteStudio(id: Int): Observable<EmptyResponse> {
        return service.deleteStudio(id)
    }

    fun editStudio(id: Int, data: HashMap<String, Any?>): Observable<StudioResponse> {
        return service.editStudio(id, data)
    }

    fun addImage(id: Int, part: MultipartBody.Part): Observable<StudioResponse> {
        return service.addImage(id, part)
    }
}