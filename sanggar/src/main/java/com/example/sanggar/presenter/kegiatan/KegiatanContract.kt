package com.example.sanggar.presenter.kegiatan

import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.kegiatan.KegiatanListResponse
import com.example.sanggar.data.model.kegiatan.KegiatanResponse
import com.example.sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*

interface KegiatanContract {

    interface View: BaseContract.View {
        fun kegiatanResponse(response: KegiatanResponse)
        fun getKegiatanResponse(response: KegiatanListResponse)
        fun deleteKegiatanResponse(response: EmptyResponse)
        fun uploadImageResponse()
    }

    interface Presenter {
        fun addKegiatan(data: HashMap<String, Any?>)
        fun getKegiatan()
        fun deleteKegiatan(id: Int)
        fun editKegiatan(id:  Int, data: HashMap<String, Any?>)
        fun addImage(id: Int, part: MultipartBody.Part)
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

        @Multipart
        @POST("kegiatan/{id}/update-image")
        fun addImage(@Path("id") Id: Int, @Part part: MultipartBody.Part): Observable<KegiatanResponse>

    }
}