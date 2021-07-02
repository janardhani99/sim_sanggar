package com.example.sim_sanggar.presenter.anak

import com.example.sim_sanggar.data.model.anak.AnakResponse
import com.example.sim_sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import kotlin.collections.HashMap

interface AnakContract {

    interface View: BaseContract.View {
        fun anakResponse(response: AnakResponse)

    }

    interface Presenter {
        fun addAnak(data: HashMap<String, Any?>)
    }

    interface Handler {
        @FormUrlEncoded
        @POST("anak")
        fun addAnak(@FieldMap data: HashMap<String, Any?>): Observable<AnakResponse>
    }
}