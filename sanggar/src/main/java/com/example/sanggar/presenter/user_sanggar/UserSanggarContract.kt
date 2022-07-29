package com.example.sanggar.presenter.user_sanggar

import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.daftar.DaftarListResponse
import com.example.sanggar.data.model.daftar.DaftarResponse
import com.example.sanggar.data.model.kegiatan.KegiatanResponse
import com.example.sanggar.data.model.user_sanggar.UserSanggarListResponse
import com.example.sanggar.data.model.user_sanggar.UserSanggarResponse
import com.example.sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.*

interface UserSanggarContract {

    interface View: BaseContract.View {

        fun getUserListResponse(response: UserSanggarListResponse)
        fun userResponse(response: UserSanggarResponse)
//        abstract fun LinearLayoutManager(daftarListFragment: DaftarListFragment): LinearLayoutManager
    }

    interface Presenter {

        fun getListUser()
        fun editUser(id:  Int, data: HashMap<String, Any?>)

    }

    interface Handler {

        @GET("user")
        fun getListUser(): Observable<UserSanggarListResponse>

        @FormUrlEncoded
        @PATCH("user/{id}")
        fun editUser(@Path("id")id: Int, @FieldMap data: HashMap<String, Any?>): Observable<UserSanggarResponse>



    }
}