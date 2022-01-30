package com.example.sim_sanggar.presenter.jam_operasional

import com.example.sim_sanggar.data.model.jam_operasional.TanggalLiburListResponse
import com.example.sim_sanggar.data.model.jam_operasional.TanggalLiburResponse
import com.example.sim_sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.*

import kotlin.collections.HashMap

interface TanggalLiburContract {

    interface View: BaseContract.View {
        fun tanggalLiburResponse(response: TanggalLiburResponse)
        fun getTanggalLiburResponse(response: TanggalLiburListResponse)
    }

    interface Presenter {
        fun tambahTanggalLibur(data: HashMap<String, Any?>)
        fun getTanggalLibur()
        fun editTanggalLibur(id: Int, data: HashMap<String, Any?>)
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


    }
}