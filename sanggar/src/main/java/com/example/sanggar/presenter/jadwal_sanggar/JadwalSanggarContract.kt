package com.example.sanggar.presenter.jadwal_sanggar

import com.example.sanggar.data.model.auth.AuthResponse
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.jadwal_sanggar.JadwalSanggarListResponse
import com.example.sanggar.data.model.jadwal_sanggar.JadwalSanggarResponse
import com.example.sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.*

interface JadwalSanggarContract {

    interface View: BaseContract.View {
        fun jadwalSanggarResponse(response: JadwalSanggarResponse)
        fun getJadwalSanggarResponse(response: JadwalSanggarListResponse)
        fun deleteJadwalResponse(response: EmptyResponse)
//        fun editJadwalResponse(response: JadwalSanggarResponse)
    }

    interface Presenter {
        fun tambahJadwalSanggar(data: HashMap<String, Any?>)
        fun getJadwal()
        fun deleteJadwal(id: Int)
        fun editJadwal(id:Int, data: HashMap<String, Any?>)
    }

    interface Handler {
        @FormUrlEncoded
        @POST("jadwal-sanggar")
        fun tambahJadwalSanggar(@FieldMap data: HashMap<String, Any?>): Observable<JadwalSanggarResponse>

        @GET("jadwal-sanggar")
        fun getJadwal(): Observable<JadwalSanggarListResponse>

        @DELETE("jadwal-sanggar/{id}")
        fun deleteJadwal(@Path("id") id: Int): Observable<EmptyResponse>

        @FormUrlEncoded
        @PATCH("jadwal-sanggar/{id}")
        fun editJadwal(@Path("id") id: Int, @FieldMap data: HashMap<String, Any?>) : Observable<JadwalSanggarResponse>
    }

}