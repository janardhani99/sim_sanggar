package com.example.sanggar.presenter.profile

import com.example.sanggar.data.model.profile.ProfileResponse
import com.example.sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*


interface ProfileContract {

    interface View : BaseContract.View {
        fun getProfileResponse(response: ProfileResponse)
        fun editProfileResponse(response: ProfileResponse)

    }

    interface Presenter : BaseContract.Presenter {
        fun getProfile()
        fun editProfile( data: HashMap<String, Any?>)
        fun updateProfilePhot(data: MultipartBody.Part)
    }

    interface Handler {
        @GET("getProfile")
        fun getProfile(): Observable<ProfileResponse>

        @FormUrlEncoded
        @POST("updateProfile")
        fun editProfile( @FieldMap data: HashMap<String, Any?> ):Observable<ProfileResponse>

        @Multipart
        @POST("updateProfilePhoto")
        fun updateProfilePhoto(@Part part: MultipartBody.Part):Observable<ProfileResponse>
    }
}