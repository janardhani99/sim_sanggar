package com.example.sanggar.presenter.pembelajaran

import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.pembelajaran.PembelajaranListResponse
import com.example.sanggar.data.model.pembelajaran.PembelajaranResponse
import com.example.sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.*
import kotlin.collections.HashMap

interface PembelajaranContract {

    interface View: BaseContract.View {
        fun pembelajaranResponse(response: PembelajaranResponse)
        fun getPembelajaranResponse(response: PembelajaranListResponse)
        fun deletePembelajaranResponse(response: EmptyResponse)
    }

    interface Presenter {
        fun addPembelajaran(data: HashMap<String, Any?>)
        fun editPembelajaran(id: Int, data: HashMap<String, Any?>)
        fun getPembelajaran()
        fun deletePembelajaran(id: Int)
    }

    interface Handler {
        @FormUrlEncoded
        @POST("pembelajaran")
        fun addPembelajaran(@FieldMap data: HashMap<String, Any?>): Observable<PembelajaranResponse>

        @FormUrlEncoded
        @PATCH("pembelajaran/{id}")
        fun editPembelajaran(@Path("id")id: Int, @FieldMap data: HashMap<String, Any?>):Observable<PembelajaranResponse>

        @GET("pembelajaran")
        fun getPembelajaran(): Observable<PembelajaranListResponse>

        @DELETE("pembelajaran/{id}")
        fun deletePembelajaran(@Path("id") id: Int):Observable<EmptyResponse>

    }
}