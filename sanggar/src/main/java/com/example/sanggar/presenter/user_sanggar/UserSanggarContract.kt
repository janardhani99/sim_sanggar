package com.example.sanggar.presenter.user_sanggar

import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.daftar.DaftarListResponse
import com.example.sanggar.data.model.daftar.DaftarResponse
import com.example.sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.*

interface UserSanggarContract {

    interface View: BaseContract.View {
        fun daftarListResponse(response: DaftarResponse)
        fun getDaftarListResponse(response: DaftarListResponse)
        fun deleteDaftarListResponse(response: EmptyResponse)
//        abstract fun LinearLayoutManager(daftarListFragment: DaftarListFragment): LinearLayoutManager
    }

    interface Presenter {
        fun addListDaftar(data: HashMap<String, Any?>)
        fun getListDaftar()
        fun deleteListDaftar(id: Int)
    }

    interface Handler {
        @FormUrlEncoded
        @POST("anak")
        fun addListDaftar(@FieldMap data: HashMap<String, Any?>): Observable<DaftarResponse>

        @GET("anak")
        fun getListDaftar(): Observable<DaftarListResponse>

        @DELETE("anak/{id}")
        fun deleteListDaftar(@Path("id")id: Int): Observable<EmptyResponse>
    }
}