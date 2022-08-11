package com.example.admin.data.handler.user

import com.example.admin.data.handler.common.BaseHandler
import com.example.admin.data.model.common.EmptyResponse
import com.example.admin.data.model.user.UserListResponse
import com.example.admin.data.model.user.UserResponse
import com.example.admin.presenter.user.UserContract
import io.reactivex.Observable

class UserHandler: BaseHandler() {

    val service = getClient().create(UserContract.Handler::class.java)

    fun addUser(data: HashMap<String, Any?>): Observable<UserResponse> {
        return service.addUser(data)
    }

    fun getUser(role: String): Observable<UserListResponse> {
        return service.getUser(role)
    }

    fun editUser(id: Int, data: HashMap<String, Any?>): Observable<UserResponse> {
        return service.editUser(id, data)
    }

    fun deleteUser(id: Int): Observable<EmptyResponse> {
        return service.deleteUser(id)
    }
}