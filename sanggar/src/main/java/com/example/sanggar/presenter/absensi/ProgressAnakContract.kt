package com.example.sanggar.presenter.absensi

import com.example.sanggar.data.model.absensi.ProgressAnakListResponse
import com.example.sanggar.data.model.absensi.ProgressAnakResponse
import com.example.sanggar.presenter.common.BaseContract
import com.example.sanggar.presenter.common.BasePresenter
import io.reactivex.Observable
import retrofit2.http.*
import kotlin.collections.HashMap

interface ProgressAnakContract {

    interface View:BaseContract.View {
        fun progressAnakResponse(response: ProgressAnakResponse)
        fun getProgressAnakResponse(response: ProgressAnakListResponse)
    }

    interface Presenter {
        fun addProgressAnak(data: HashMap<String, Any?>)
        fun editProgressAnak(id: Int, data: HashMap<String, Any?>)
        fun getProgressAnak()
    }

    interface Handler {
        @FormUrlEncoded
        @POST("progressanak")
        fun addProgressAnak(@FieldMap data: HashMap<String, Any?>): Observable<ProgressAnakResponse>

        @FormUrlEncoded
        @PATCH("progressanak/{id}")
        fun editProgressAnak(@Path("id")id: Int, @FieldMap data: HashMap<String, Any?>): Observable<ProgressAnakResponse>

        @GET("progressanak")
        fun getProgressAnak(): Observable<ProgressAnakListResponse>

    }
}