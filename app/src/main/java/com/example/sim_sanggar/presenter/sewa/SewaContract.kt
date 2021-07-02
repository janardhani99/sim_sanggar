package com.example.sim_sanggar.presenter.sewa

import com.example.sim_sanggar.data.model.sewa.SewaResponse
import com.example.sim_sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SewaContract {

    interface View: BaseContract.View {
        fun sewaResponse(response: SewaResponse)
    }

    interface Presenter {
        fun addSewa(data: HashMap<String, Any?>)
    }

    interface Handler {
        @FormUrlEncoded
        @POST("penyewaan-sanggar")
        fun addSewa(@FieldMap data: HashMap<String, Any?>): Observable<SewaResponse>
    }
}