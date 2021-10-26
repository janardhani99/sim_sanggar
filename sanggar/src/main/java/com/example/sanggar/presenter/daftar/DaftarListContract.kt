package com.example.sanggar.presenter.daftar

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.daftar.DaftarListResponse
import com.example.sanggar.data.model.daftar.DaftarResponse
import com.example.sanggar.presenter.common.BaseContract
import com.example.sanggar.view.fragment.daftar.DaftarListFragment
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
        fun getListDaftar()
        fun deleteListDaftar(id: Int)
    }

    interface Handler {
        @FormUrlEncoded
        @POST("pendaftaran-siswa")
        fun addListDaftar(@FieldMap data: HashMap<String, Any?>): Observable<DaftarResponse>

        @GET("pendaftaran-siswa")
        fun getListDaftar(): Observable<DaftarListResponse>

        @DELETE("pendaftaran-siswa/{id}")
        fun deleteListDaftar(@Path("id")id: Int): Observable<EmptyResponse>
    }
}