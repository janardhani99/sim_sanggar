package com.example.sanggar.presenter.sanggar

import com.example.sanggar.data.model.sanggar.ProfilSanggarResponse
import com.example.sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import kotlin.collections.HashMap

interface ProfilSanggarContract {

    interface View: BaseContract.View {
        fun addProfilSanggarResponse(response: ProfilSanggarResponse)
    }

    interface Presenter {
        fun addProfilSanggar(data: HashMap<String, Any?>)
    }

    interface Handler {
        @FormUrlEncoded
        @POST("sanggar")
        fun addProfilSanggar(@FieldMap data: HashMap<String, Any?>): Observable<ProfilSanggarResponse>

    }
}