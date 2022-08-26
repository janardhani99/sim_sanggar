package com.example.sim_sanggar.data.handler.profile

import com.example.sim_sanggar.data.handler.common.BaseHandler
import com.example.sim_sanggar.data.model.profile.ProfileListResponse
import com.example.sim_sanggar.data.model.profile.ProfileResponse
import com.example.sim_sanggar.presenter.profile.ProfileContract
import io.reactivex.Observable
import okhttp3.MultipartBody

class ProfileHandler: BaseHandler() {
    val service = getClient().create(ProfileContract.Handler::class.java)

    fun getProfile(): Observable<ProfileResponse> {
        return service.getProfile()
    }

    fun editProfile(data: HashMap<String, Any?>):Observable<ProfileResponse> {
        return service.editProfile( data)
    }

    fun updateProfilePhoto(data: MultipartBody.Part): Observable<ProfileResponse> {
        return service.updateProfilePhoto(data)
    }
}