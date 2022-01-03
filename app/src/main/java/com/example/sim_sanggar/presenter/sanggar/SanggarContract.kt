package com.example.sim_sanggar.presenter.sanggar

import com.example.sim_sanggar.data.model.anak.AnakListResponse
import com.example.sim_sanggar.data.model.anak.AnakResponse
import com.example.sim_sanggar.data.model.sanggar.SanggarData
import com.example.sim_sanggar.data.model.sanggar.SanggarListResponse
import com.example.sim_sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface SanggarContract {
    interface View: BaseContract.View {
        fun getSanggarResponse(response: SanggarListResponse)
    }

    interface Presenter {
        fun getSanggar()
    }

    interface Handler {
        @GET("sanggar")
        fun getSanggar(): Observable<SanggarListResponse>
    }
}