package com.example.sim_sanggar.data.handler.pembelajaran

import com.example.sim_sanggar.data.handler.common.BaseHandler
import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.pembelajaran.PembelajaranListResponse
import com.example.sim_sanggar.data.model.pembelajaran.PembelajaranResponse
import com.example.sim_sanggar.presenter.pembelajaran.PembelajaranContract
import io.reactivex.Observable

import kotlin.collections.HashMap

class PembelajaranHandler:BaseHandler() {
    val service = getClient().create(PembelajaranContract.Handler::class.java)

    fun getPembelajaran(): Observable<PembelajaranListResponse> {
        return service.getPembelajaran()
    }

}