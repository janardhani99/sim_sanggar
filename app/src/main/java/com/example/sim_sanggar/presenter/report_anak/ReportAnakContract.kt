package com.example.sim_sanggar.presenter.report_anak

import com.example.sim_sanggar.data.model.report_anak.ReportAnakListResponse
import com.example.sim_sanggar.data.model.report_anak.ReportAnakResponse
import com.example.sim_sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.*
import kotlin.collections.HashMap

interface ReportAnakContract {

    interface View:BaseContract.View {
        fun progressAnakResponse(response: ReportAnakResponse)
        fun getProgressAnakResponse(response: ReportAnakListResponse)
        fun getDetailProgress(response: ReportAnakResponse)
        fun loadDataSearch(response: ReportAnakListResponse)
    }

    interface Presenter {
        fun addProgressAnak(data: HashMap<String, Any?>)
        fun editProgressAnak(id: Int, data: HashMap<String, Any?>)
        fun getProgressAnak()
        fun getDetailProgress(data: HashMap<String, Any?>)
        fun loadDataSearch(anak: Int)
    }

    interface Handler {
        @FormUrlEncoded
        @POST("progressanak")
        fun addProgressAnak(@FieldMap data: HashMap<String, Any?>): Observable<ReportAnakResponse>

        @FormUrlEncoded
        @PATCH("progressanak/{id}")
        fun editProgressAnak(@Path("id")id: Int, @FieldMap data: HashMap<String, Any?>): Observable<ReportAnakResponse>

        @GET("progressanak")
        fun getProgressAnak(): Observable<ReportAnakListResponse>

        @GET("detailprogress")
        fun getDetailProgress(@QueryMap map:HashMap<String, Any?>):Observable<ReportAnakResponse>

        @GET("progressanak/{anak}")
        fun loadDataSearch(@Path("anak") key: Int):Observable<ReportAnakListResponse>

    }
}