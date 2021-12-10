package com.example.sanggar.presenter.daftar

import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.daftar.DaftarListResponse
import com.example.sanggar.data.model.daftar.DaftarResponse
import com.example.sanggar.presenter.common.BaseContract
import retrofit2.http.*
import io.reactivex.Observable


interface DaftarListContract {

    interface View: BaseContract.View {
        fun daftarListResponse(response: DaftarResponse)
        fun getDaftarListResponse(response: DaftarListResponse)
        fun deleteDaftarListResponse(response: EmptyResponse)
//        abstract fun LinearLayoutManager(daftarListFragment: DaftarListFragment): LinearLayoutManager
    }

    interface Presenter {
        fun addListDaftar(data: HashMap<String, Any?>)
        fun getListDaftar(status: String)
        fun editStatusDaftar(id: String, data: HashMap<String, Any?>)
        fun deleteListDaftar(id: Int)
    }

    interface Handler {
        @FormUrlEncoded
        @POST("pendaftaran-siswa")
        fun addListDaftar(@FieldMap data: HashMap<String, Any?>): Observable<DaftarResponse>

        @GET("pendaftaran-siswa")
        fun getListDaftar(@Query("status") status: String): Observable<DaftarListResponse>

        @FormUrlEncoded
        @PATCH("pendaftaran-siswa/{id}")
        fun editStatusDaftar(@Path("id") id: String, @FieldMap data: HashMap<String, Any?>):Observable<DaftarResponse>

        @DELETE("pendaftaran-siswa/{id}")
        fun deleteListDaftar(@Path("id")id: Int): Observable<EmptyResponse>
    }
}