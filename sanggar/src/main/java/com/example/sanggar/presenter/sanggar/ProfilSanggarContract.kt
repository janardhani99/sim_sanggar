package com.example.sanggar.presenter.sanggar

import com.example.sanggar.data.model.sanggar.ProfilSanggarListResponse
import com.example.sanggar.data.model.sanggar.ProfilSanggarResponse
import com.example.sanggar.data.model.sanggar.SanggarData
import com.example.sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.*
import kotlin.collections.HashMap

interface ProfilSanggarContract {

    interface View: BaseContract.View {
        fun profilSanggarResponse(response: ProfilSanggarResponse)
        fun getProfilSanggarResponse(response: ProfilSanggarListResponse)
    }

    interface Presenter {
        fun addProfilSanggar(data: HashMap<String, Any?>)
        fun getProfilSanggar()
        fun editProfilSanggar(id: Int, data: HashMap<String, Any?>)
    }

    interface Handler {
        @FormUrlEncoded
        @POST("sanggar")
        fun addProfilSanggar(@FieldMap data: HashMap<String, Any?>): Observable<ProfilSanggarResponse>

        @GET("sanggar")
        fun getProfilSanggar():Observable<ProfilSanggarListResponse>

        @FormUrlEncoded
        @PATCH("sanggar/{id}")
        fun editProfilSanggar(@Path("id")id: Int, @FieldMap data: HashMap<String, Any?>):Observable<ProfilSanggarResponse>
    }
}