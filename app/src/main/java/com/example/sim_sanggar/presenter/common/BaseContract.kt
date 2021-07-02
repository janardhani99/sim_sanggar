package com.example.sim_sanggar.presenter.common

interface BaseContract {
    interface View {
        fun showError(title: String, message: String)
    }

    interface Presenter {
        fun showError(title: String, message: String)
//        fun updateFirebaseToken(token: String)
    }
    
}

open class BasePresenter(private val baseView: BaseContract.View) : BaseContract.Presenter {
    override fun showError(title: String, message: String) = baseView.showError(title, message)
}

