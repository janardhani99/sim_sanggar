package com.example.admin.presenter.user

import com.example.admin.data.model.user.SanggarListResponse
import com.example.admin.presenter.common.BaseContract
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