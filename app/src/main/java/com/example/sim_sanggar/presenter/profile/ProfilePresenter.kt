package com.example.sim_sanggar.presenter.profile

import com.example.sim_sanggar.common.doSubscribe
import com.example.sim_sanggar.data.handler.common.ErrorHandler
import com.example.sim_sanggar.data.handler.profile.ProfileHandler
import com.example.sim_sanggar.data.model.profile.ProfileResponse
import com.example.sim_sanggar.presenter.common.BasePresenter
import okhttp3.MultipartBody

class ProfilePresenter(val view: ProfileContract.View): BasePresenter(view), ProfileContract.Presenter {

    val handler = ProfileHandler()

    override fun getProfile() {
        handler.getProfile()
                .doSubscribe(object : ErrorHandler<ProfileResponse>(this) {
                    override fun onNext(t: ProfileResponse) {
                        view.getProfileResponse(t)
                    }
                })
    }

    override fun editProfile( data: HashMap<String, Any?>) {
        handler.editProfile( data)
                .doSubscribe(object : ErrorHandler<ProfileResponse>(this) {
                    override fun onNext(t: ProfileResponse) {
                        view.editProfileResponse(t)
                    }
                })
    }

    override fun updateProfilePhot(data: MultipartBody.Part) {
        handler.updateProfilePhoto(data)
                .doSubscribe(object : ErrorHandler<ProfileResponse>(this) {
                    override fun onNext(t: ProfileResponse) {
                        view.editProfileResponse(t)
                    }
                })
    }
}