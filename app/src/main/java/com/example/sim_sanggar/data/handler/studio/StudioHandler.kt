package com.example.sim_sanggar.data.handler.studio

import com.example.sim_sanggar.data.handler.common.BaseHandler
import com.example.sim_sanggar.data.model.studio.StudioListResponse
import com.example.sim_sanggar.presenter.studio.StudioContract
import io.reactivex.Observable
import okhttp3.MultipartBody

class StudioHandler:BaseHandler() {
    val service = getClient().create(StudioContract.Handler::class.java)

    fun getStudio(): Observable<StudioListResponse> {
        return service.getStudio()
    }

}