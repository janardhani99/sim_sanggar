package com.example.admin.presenter.user

import com.example.admin.common.doSubscribe
import com.example.admin.data.handler.common.ErrorHandler
import com.example.admin.data.handler.user.UserHandler
import com.example.admin.data.model.common.EmptyResponse
import com.example.admin.data.model.user.UserListResponse
import com.example.admin.data.model.user.UserResponse
import com.example.admin.presenter.common.BasePresenter


class UserPresenter(val view: UserContract.View): BasePresenter(view), UserContract.Presenter {
    val handler = UserHandler()

    override fun addUser(data: HashMap<String, Any?>) {
        handler.addUser(data)
                .doSubscribe(object : ErrorHandler<UserResponse>(this){
                    override fun onNext(t: UserResponse) {
                        view.userResponse(t)
                    }
                })
    }

    override fun getUser(role: String){
        handler.getUser(role)
                .doSubscribe(object : ErrorHandler<UserListResponse>(this){
                    override fun onNext(t: UserListResponse) {
                        view.getUserResponse(t)
                    }
                })
    }

    override fun editUser(id: Int, data: HashMap<String, Any?>) {
        handler.editUser(id, data)
                .doSubscribe(object: ErrorHandler<UserResponse>(this){
                    override fun onNext(t: UserResponse) {
                        view.userResponse(t)
                    }
                })
    }

    override fun deleteUser(id: Int) {
        handler.deleteUser(id)
                .doSubscribe(object: ErrorHandler<EmptyResponse>(this) {
                    override fun onNext(t: EmptyResponse) {
                        view.deleteUserResponse(t)
                    }
                })
    }
}