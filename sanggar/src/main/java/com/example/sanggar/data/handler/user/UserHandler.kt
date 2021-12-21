package com.example.sanggar.data.handler.user

import com.example.sanggar.data.handler.common.BaseHandler
import com.example.sanggar.data.model.sanggar.ProfilSanggarListResponse
import com.example.sanggar.data.model.user_sanggar.UserSanggarListResponse
import com.example.sanggar.presenter.user_sanggar.UserSanggarContract
import io.reactivex.Observable

class UserHandler: BaseHandler() {

    val service = getClient().create(UserSanggarContract.Handler::class.java)

    fun getUser(): Observable<UserSanggarListResponse> {
        return service.getListUser()
    }
}