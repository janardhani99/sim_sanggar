package com.example.sanggar.presenter.sanggar

import com.example.sanggar.common.doSubscribe
import com.example.sanggar.data.handler.common.ErrorHandler
import com.example.sanggar.data.handler.sanggar.ProfilSanggarHandler
import com.example.sanggar.data.model.auth.AuthResponse
import com.example.sanggar.data.model.sanggar.ProfilSanggarResponse
import com.example.sanggar.presenter.common.BasePresenter


class ProfilSanggarPresenter(val view: ProfilSanggarContract.View): BasePresenter(view), ProfilSanggarContract.Presenter {
    val handler = ProfilSanggarHandler()

    override fun addProfilSanggar(data: HashMap<String, Any?>) {
        handler.addProfilSanggar(data)
                .doSubscribe(object : ErrorHandler<ProfilSanggarResponse>(this) {
                    override fun onNext(t: ProfilSanggarResponse) {
                        view.addProfilSanggarResponse(t)
                    }
                })
    }
}