package com.example.admin.presenter.user

import com.example.admin.data.model.user.UserListResponse
import com.example.admin.data.model.user.UserResponse
import com.example.admin.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.*

interface UserContract {

    interface View: BaseContract.View {
        fun userResponse(response: UserResponse)
        fun getUserResponse(response: UserListResponse)

    }

    interface Presenter {
        fun addUser(data: HashMap<String, Any?>)
        fun getUser()
        fun editUser(id: Int, data: HashMap<String, Any?>)
    }

    interface Handler {
        @FormUrlEncoded
        @POST("user")
        fun addUser(@FieldMap data: HashMap<String, Any?>): Observable<UserResponse>

        @GET("user")
        fun getUser(): Observable<UserListResponse>

        @FormUrlEncoded
        @PATCH("user/{id}")
        fun editUser(@Path("id")id: Int, @FieldMap data: HashMap<String, Any?>): Observable<UserResponse>
    }
}