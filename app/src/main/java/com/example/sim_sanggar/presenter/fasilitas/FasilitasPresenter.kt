package com.example.sim_sanggar.presenter.fasilitas


import com.example.sim_sanggar.common.doSubscribe
import com.example.sim_sanggar.data.handler.common.ErrorHandler
import com.example.sim_sanggar.data.handler.fasilitas.FasilitasHandler
import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.fasilitas.FasilitasListResponse
import com.example.sim_sanggar.data.model.fasilitas.FasilitasResponse
import com.example.sim_sanggar.presenter.common.BasePresenter
import okhttp3.MultipartBody

class FasilitasPresenter(val view: FasilitasContract.View): BasePresenter(view), FasilitasContract.Presenter {

    val handler = FasilitasHandler()

    override fun addFasilitas(data: HashMap<String, Any?>) {
        handler.addFasilitas(data)
                .doSubscribe(object : ErrorHandler<FasilitasResponse>(this){
                    override fun onNext(t: FasilitasResponse) {
                        view.fasilitasResponse(t)
                    }
                })
    }

    override fun getFasilitas() {
        handler.getFasilitas()
                .doSubscribe(object : ErrorHandler<FasilitasListResponse>(this) {
                    override fun onNext(t: FasilitasListResponse) {
                        view.getFasilitasResponse(t)
                    }
                })
    }

    override fun deleteFasilitas(id: Int) {
        handler.deleteFasilitas(id)
                .doSubscribe(object : ErrorHandler<EmptyResponse>(this){
                    override fun onNext(t: EmptyResponse) {
                        view.deleteFasilitasResponse(t)
                    }
                })
    }

    override fun editFasilitas(id: Int, data: HashMap<String, Any?>) {
        handler.editFasilitas(id, data)
                .doSubscribe(object : ErrorHandler<FasilitasResponse>(this) {
                    override fun onNext(t: FasilitasResponse) {
                        view.fasilitasResponse(t)
                    }
                })
    }

    override fun addImage(id: Int, part: MultipartBody.Part) {
        handler.addImage(id, part)
                .doSubscribe(object : ErrorHandler<FasilitasResponse>(this) {
                    override fun onNext(t: FasilitasResponse) {
                        view.uploadImageResponse()
                    }
                })
    }
}