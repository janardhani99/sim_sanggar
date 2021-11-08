package com.example.sim_sanggar.presenter.jam_operasional

import com.example.sim_sanggar.data.model.jam_operasional.JamOperasionalResponse
import com.example.sim_sanggar.presenter.common.BaseContract
import io.reactivex.Observable
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
        @POST("jam-operasional")
        fun tambahJamOperasional(@FieldMap data: HashMap<String, Any?>): Observable<JamOperasionalResponse>
    }
}