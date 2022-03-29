package com.example.sanggar.presenter.jam_operasional

import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.jam_operasional.JamOperasionalListResponse
import com.example.sanggar.data.model.jam_operasional.JamOperasionalResponse
import com.example.sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.*

import kotlin.collections.HashMap

interface JamOperasionalContract {

    interface View: BaseContract.View {
        fun jamOperasionalResponse(response: JamOperasionalResponse)
        fun getJamOperasionalResponse(response: JamOperasionalListResponse)
        fun deleteJamOperasionalResponse(response: EmptyResponse)
    }

    interface Presenter {
        fun tambahJamOperasional(data: HashMap<String, Any?>)
        fun getJamOperasional()
        fun editJamOperasional(id: Int, data: HashMap<String, Any?>)
        fun deleteJamOperasional(id: Int)
    }

    interface Handler {
        @FormUrlEncoded
        @POST("jam-operasional")
        fun tambahJamOperasoional(@FieldMap data: HashMap<String, Any?>): Observable<JamOperasionalResponse>

        @GET("jam-operasional")
        fun getJamOperasional(): Observable<JamOperasionalListResponse>

        @FormUrlEncoded
        @PATCH("jam-operasional/{id}")
        fun editJamOperasional(@Path("id") id: Int, @FieldMap data: HashMap<String, Any?>): Observable<JamOperasionalResponse>

        @DELETE("jam-operasional/{id}")
        fun deleteJamOperasional(@Path("id") id: Int):Observable<EmptyResponse>

    }
}