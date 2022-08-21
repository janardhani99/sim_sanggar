package com.example.sanggar.presenter.studio

import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.studio.StudioListResponse
import com.example.sanggar.data.model.studio.StudioResponse
import com.example.sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*

interface StudioContract {

    interface View: BaseContract.View {
        fun studioResponse(response: StudioResponse)
        fun getStudioResponse(response: StudioListResponse)
        fun deleteStudioResponse(response: EmptyResponse)
        fun uploadImageResponse()
    }

    interface Presenter {
        fun addStudio(data: HashMap<String, Any?>)
        fun getStudio()
        fun deleteStudio(id: Int)
        fun editStudio(id:  Int, data: HashMap<String, Any?>)
        fun addImage(id: Int, part: MultipartBody.Part)
    }

    interface Handler {
        @FormUrlEncoded
        @POST("studio")
        fun addStudio(@FieldMap data: HashMap<String, Any?>): Observable<StudioResponse>

        @GET("studio")
        fun getStudio(): Observable<StudioListResponse>

        @DELETE("studio/{id}")
        fun deleteStudio(@Path("id") id: Int): Observable<EmptyResponse>

        @FormUrlEncoded
        @PATCH("studio/{id}")
        fun editStudio(@Path("id")id: Int, @FieldMap data: HashMap<String, Any?>): Observable<StudioResponse>

        @Multipart
        @POST("studio/{id}/update-foto")
        fun addImage(@Path("id") Id: Int, @Part part: MultipartBody.Part): Observable<StudioResponse>

    }
}