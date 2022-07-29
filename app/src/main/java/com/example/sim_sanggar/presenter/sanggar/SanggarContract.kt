package com.example.sim_sanggar.presenter.sanggar

import com.example.sim_sanggar.data.model.sanggar.SanggarListResponse
import com.example.sim_sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.GET

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