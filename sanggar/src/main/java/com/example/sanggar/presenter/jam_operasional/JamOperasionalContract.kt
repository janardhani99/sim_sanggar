package com.example.sanggar.presenter.jam_operasional

import com.example.sanggar.data.model.jam_operasional.JamOperasionalResponse
import com.example.sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

import kotlin.collections.HashMap

interface JamOperasionalContract {

    interface View: BaseContract.View {
        fun jamOperasionalResponse(response: JamOperasionalResponse)
    }

    interface Presenter {
        fun tambahJamOperasional(data: HashMap<String, Any?>)
    }

    interface Handler {
        @FormUrlEncoded
        @POST("sanggar")
        fun tambahJamOperasoional(@FieldMap data: HashMap<String, Any?>): Observable<JamOperasionalResponse>
    }
}