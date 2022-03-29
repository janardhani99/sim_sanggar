package com.example.sanggar.presenter.jam_operasional

import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.jam_operasional.TanggalLiburListResponse
import com.example.sanggar.data.model.jam_operasional.TanggalLiburResponse
import com.example.sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.*

import kotlin.collections.HashMap

interface TanggalLiburContract {

    interface View: BaseContract.View {
        fun tanggalLiburResponse(response: TanggalLiburResponse)
        fun getTanggalLiburResponse(response: TanggalLiburListResponse)
        fun deleteTanggalLiburResponse(response: EmptyResponse)
    }

    interface Presenter {
        fun tambahTanggalLibur(data: HashMap<String, Any?>)
        fun getTanggalLibur()
        fun editTanggalLibur(id: Int, data: HashMap<String, Any?>)
        fun deleteTanggalLibur(id: Int)
    }

    interface Handler {
        @FormUrlEncoded
        @POST("tanggal-libur")
        fun tambahTanggalLibur(@FieldMap data: HashMap<String, Any?>): Observable<TanggalLiburResponse>

        @GET("tanggal-libur")
        fun getTanggalLibur(): Observable<TanggalLiburListResponse>

        @FormUrlEncoded
        @PATCH("tanggal-libur/{id}")
        fun editTanggalLibur(@Path("id") id: Int, @FieldMap data: HashMap<String, Any?>): Observable<TanggalLiburResponse>

        @DELETE("tanggal-libur/{id}")
        fun deleteTanggalLibur(@Path("id") id: Int):Observable<EmptyResponse>
    }
}