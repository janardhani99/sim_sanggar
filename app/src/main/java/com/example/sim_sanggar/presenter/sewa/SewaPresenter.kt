package com.example.sim_sanggar.presenter.sewa

import com.example.sim_sanggar.common.doSubscribe
import com.example.sim_sanggar.data.handler.common.ErrorHandler
import com.example.sim_sanggar.data.handler.sewa.SewaHandler
import com.example.sim_sanggar.data.model.sewa.SewaResponse
import com.example.sim_sanggar.presenter.common.BasePresenter
import okhttp3.MultipartBody


class SewaPresenter(val view: SewaContract.View):BasePresenter(view), SewaContract.Presenter {

    val handler = SewaHandler()

    override fun addSewa(data: HashMap<String, Any?>) {
        handler.addSewa(data)
                .doSubscribe(object : ErrorHandler<SewaResponse>(this) {
                    override fun onNext(t: SewaResponse) {
                        view.sewaResponse(t)
                    }
                })
    }

    override fun addImage(id: Int, part: MultipartBody.Part) {
        handler.addImage(id, part)
                .doSubscribe(object : ErrorHandler<SewaResponse>(this) {
                    override fun onNext(t: SewaResponse) {
                        view.uploadImageResponse()
                    }
                })
    }
}