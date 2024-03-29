package com.example.admin.presenter.user

import com.example.admin.data.model.common.EmptyResponse
import com.example.admin.data.model.user.UserListResponse
import com.example.admin.data.model.user.UserResponse
import com.example.admin.presenter.common.BaseContract
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*

interface UserContract {

    interface View: BaseContract.View {
        fun userResponse(response: UserResponse)
        fun getUserResponse(response: UserListResponse)
        fun deleteUserResponse(response: EmptyResponse)
        fun getProfileResponse(response: UserResponse)
        fun editProfileResponse(response: UserResponse)
    }

    interface Presenter {
        fun addUser(data: HashMap<String, Any?>)
        fun getUser(role: String)
        fun editUser(id: Int, data: HashMap<String, Any?>)
        fun deleteUser(id: Int)
        fun getProfile()
        fun editProfile( data: HashMap<String, Any?>)
        fun updateProfilePhot(data: MultipartBody.Part)
    }

    interface Handler {
        @FormUrlEncoded
        @POST("user")
        fun addUser(@FieldMap data: HashMap<String, Any?>): Observable<UserResponse>

        @GET("user")
        fun getUser(@Query("role") role: String): Observable<UserListResponse>

        @FormUrlEncoded
        @PATCH("user/{id}")
        fun editUser(@Path("id")id: Int, @FieldMap data: HashMap<String, Any?>): Observable<UserResponse>

        @DELETE("user/{id}")
        fun deleteUser(@Path("id") id: Int):Observable<EmptyResponse>

        @GET("getProfile")
        fun getProfile(): Observable<UserResponse>

        @FormUrlEncoded
        @POST("updateProfile")
        fun editProfile( @FieldMap data: HashMap<String, Any?> ):Observable<UserResponse>

        @Multipart
        @POST("updateProfilePhoto")
        fun updateProfilePhoto(@Part part: MultipartBody.Part):Observable<UserResponse>
    }
}