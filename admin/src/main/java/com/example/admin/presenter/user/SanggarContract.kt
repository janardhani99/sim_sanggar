package com.example.admin.presenter.user

import com.example.admin.data.model.user.SanggarListResponse
import com.example.admin.data.model.user.SanggarResponse
import com.example.admin.data.model.user.UserResponse
import com.example.admin.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.*

interface SanggarContract {
    interface View: BaseContract.View {
        fun getSanggarResponse(response: SanggarListResponse)
        fun sanggarResponse(response: SanggarResponse)
    }

    interface Presenter {
        fun getSanggar()
        fun addSanggar(data: HashMap<String, Any?>)
        fun editProfilSanggar(id: Int, data: HashMap<String, Any?>)
    }

    interface Handler {
        @GET("sanggar")
        fun getSanggar(): Observable<SanggarListResponse>

        @FormUrlEncoded
        @POST("sanggar")
        fun addSanggar(@FieldMap data: HashMap<String, Any?>): Observable<SanggarResponse>

        @FormUrlEncoded
        @PATCH("sanggar/{id}")
        fun editProfilSanggar(@Path("id")id: Int, @FieldMap data: HashMap<String, Any?>):Observable<SanggarResponse>
    }
}