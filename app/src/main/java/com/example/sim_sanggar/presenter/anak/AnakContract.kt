package com.example.sim_sanggar.presenter.anak

import com.example.sim_sanggar.data.model.anak.AnakListResponse
import com.example.sim_sanggar.data.model.anak.AnakResponse
import com.example.sim_sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.*
import kotlin.collections.HashMap

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