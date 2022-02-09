package com.example.sanggar.presenter.absensi

import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.absensi.PertemuanDataListResponse
import com.example.sanggar.data.model.absensi.PertemuanDataResponse
import com.example.sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.*

interface PertemuanContract {

    interface View:BaseContract.View{
        fun pertemuanResponse(response: PertemuanDataResponse)
        fun getPertemuanResponse(response: PertemuanDataListResponse)
        fun deletePertemuanResponse(response: EmptyResponse)
    }

    interface Presenter {
        fun addPertemuan(data: HashMap<String, Any?>)
        fun editPertemuan(id: Int, data: HashMap<String, Any?>)
        fun getPertemuan(jadwal_sanggar: Int)
        fun deletePertemuan(id: Int)
    }

    interface Handler {
        @FormUrlEncoded
        @POST("pertemuan")
        fun addPertemuan(@FieldMap data: HashMap<String, Any?>): Observable<PertemuanDataResponse>

        @FormUrlEncoded
        @PATCH("pertemuan/{id}")
        fun editPertemuan(@Path("id")id: Int, @FieldMap data: HashMap<String, Any?>): Observable<PertemuanDataResponse>

        @GET("pertemuan")
        fun getPertemuan(@Query("jadwal_sanggar") jadwal_sanggar: Int): Observable<PertemuanDataListResponse>

        @DELETE("pertemuan/{id}")
        fun deletePertemuan(@Path("value") id: Int): Observable<EmptyResponse>
    }

}