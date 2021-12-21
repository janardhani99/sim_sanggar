package com.example.sanggar.presenter.user_sanggar

import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.daftar.DaftarListResponse
import com.example.sanggar.data.model.daftar.DaftarResponse
import com.example.sanggar.data.model.user_sanggar.UserSanggarListResponse
import com.example.sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.*

interface UserSanggarContract {

    interface View: BaseContract.View {

        fun getUserListResponse(response: UserSanggarListResponse)

//        abstract fun LinearLayoutManager(daftarListFragment: DaftarListFragment): LinearLayoutManager
    }

    interface Presenter {

        fun getListUser()

    }

    interface Handler {

        @GET("user")
        fun getListUser(): Observable<UserSanggarListResponse>


    }
}