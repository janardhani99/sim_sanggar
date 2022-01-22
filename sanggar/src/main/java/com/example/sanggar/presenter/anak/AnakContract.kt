package com.example.sanggar.presenter.anak

import com.example.sanggar.data.model.anak.AnakListResponse
import com.example.sanggar.data.model.anak.AnakResponse
import com.example.sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.*


interface AnakContract {

    interface View: BaseContract.View {
        fun anakResponse(response: AnakResponse)
        fun getAnakResponse(response: AnakListResponse)

    }

    interface Presenter {
        fun addAnak(data: HashMap<String, Any?>)
        fun getAnak()
    }

    interface Handler {
        @FormUrlEncoded
        @POST("anak")
        fun addAnak(@FieldMap data: HashMap<String, Any?>): Observable<AnakResponse>

        @GET("anak")
        fun getAnak(): Observable<AnakListResponse>
    }
}