package com.example.sim_sanggar.presenter.report_anak

import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.report_anak.PertemuanDataListResponse
import com.example.sim_sanggar.data.model.report_anak.PertemuanDataResponse
import com.example.sim_sanggar.presenter.common.BaseContract
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
        fun deletePertemuan(@Path("id") id: Int): Observable<EmptyResponse>
    }

}