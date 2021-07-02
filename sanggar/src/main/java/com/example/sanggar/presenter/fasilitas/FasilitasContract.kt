package com.example.sanggar.presenter.fasilitas

import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.fasilitas.FasilitasListResponse
import com.example.sanggar.data.model.fasilitas.FasilitasResponse
import com.example.sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*

interface FasilitasContract {

    interface View: BaseContract.View {
        fun fasilitasResponse(response: FasilitasResponse)
        fun getFasilitasResponse(response: FasilitasListResponse)
        fun deleteFasilitasResponse(response: EmptyResponse)
        fun uploadImageResponse()
    }

    interface Presenter {
        fun addFasilitas(data: HashMap<String, Any?>)
        fun getFasilitas()
        fun deleteFasilitas(id: Int)
        fun editFasilitas(id: Int, data: HashMap<String, Any?>)
        fun addImage(id: Int, part: MultipartBody.Part)
    }

    interface Handler {
        @FormUrlEncoded
        @POST("fasilitas")
        fun addFasilitas(@FieldMap data: HashMap<String, Any?>): Observable<FasilitasResponse>

        @GET("fasilitas")
        fun getFasilitas(): Observable<FasilitasListResponse>

        @DELETE("fasilitas/{id}")
        fun deleteFasilitas(@Path("id")id: Int): Observable<EmptyResponse>

        @FormUrlEncoded
        @PATCH("fasilitas/{id}")
        fun editFasilitas(@Path("id")id: Int, @FieldMap data: HashMap<String, Any?>): Observable<FasilitasResponse>

        @Multipart
        @POST("fasilitas/{id}/update-image")
        fun addImage(@Path("id") Id: Int, @Part part: MultipartBody.Part): Observable<FasilitasResponse>
    }
}