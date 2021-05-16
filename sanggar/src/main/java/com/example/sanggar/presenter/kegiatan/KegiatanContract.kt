package com.example.sanggar.presenter.kegiatan

import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.kegiatan.KegiatanListResponse
import com.example.sanggar.data.model.kegiatan.KegiatanResponse
import com.example.sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.*

interface KegiatanContract {

    interface View: BaseContract.View {
        fun kegiatanResponse(response: KegiatanResponse)
        fun getKegiatanResponse(response: KegiatanListResponse)
        fun deleteKegiatanResponse(response: EmptyResponse)
    }

    interface Presenter {
        fun addKegiatan(data: HashMap<String, Any?>)
        fun getKegiatan()
        fun deleteKegiatan(id: Int)
        fun editKegiatan(id:  Int, data: HashMap<String, Any?>)
    }

    interface Handler {
        @FormUrlEncoded
        @POST("kegiatan")
        fun addKegiatan(@FieldMap data: HashMap<String, Any?>): Observable<KegiatanResponse>

        @GET("kegiatan")
        fun getKegiatan(): Observable<KegiatanListResponse>

        @DELETE("kegiatan/{id}")
        fun deleteKegiatan(@Path("id") id: Int): Observable<EmptyResponse>

        @FormUrlEncoded
        @PATCH("kegiatan/{id}")
        fun editKegiatan(@Path("id")id: Int, @FieldMap data: HashMap<String, Any?>): Observable<KegiatanResponse>
    }
}