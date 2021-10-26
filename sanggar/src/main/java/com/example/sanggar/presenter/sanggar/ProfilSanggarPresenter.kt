package com.example.sanggar.presenter.sanggar

import com.example.sanggar.common.doSubscribe
import com.example.sanggar.data.handler.common.ErrorHandler
import com.example.sanggar.data.handler.sanggar.ProfilSanggarHandler
import com.example.sanggar.data.model.auth.AuthResponse
import com.example.sanggar.data.model.sanggar.ProfilSanggarListResponse
import com.example.sanggar.data.model.sanggar.ProfilSanggarResponse
import com.example.sanggar.presenter.common.BasePresenter


class ProfilSanggarPresenter(val view: ProfilSanggarContract.View): BasePresenter(view), ProfilSanggarContract.Presenter {
    val handler = ProfilSanggarHandler()

    override fun addProfilSanggar(data: HashMap<String, Any?>) {
        handler.addProfilSanggar(data)
                .doSubscribe(object : ErrorHandler<ProfilSanggarResponse>(this) {
                    override fun onNext(t: ProfilSanggarResponse) {
                        view.profilSanggarResponse(t)
                    }
                })
    }

    override fun getProfilSanggar() {
        handler.getProfilSanggar()
                .doSubscribe(object : ErrorHandler<ProfilSanggarListResponse>(this){
                    override fun onNext(t: ProfilSanggarListResponse) {
                        view.getProfilSanggarResponse(t)
                    }
                })
    }

    override fun editProfilSanggar(id: Int, data: HashMap<String, Any?>) {
        handler.editProfilSanggar(id, data)
                .doSubscribe(object :ErrorHandler<ProfilSanggarResponse>(this){
                    override fun onNext(t: ProfilSanggarResponse) {
                        view.profilSanggarResponse(t)
                    }
                })
    }
}