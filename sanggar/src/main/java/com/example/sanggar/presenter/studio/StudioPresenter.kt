package com.example.sanggar.presenter.studio

import com.example.sanggar.common.doSubscribe
import com.example.sanggar.data.handler.common.ErrorHandler
import com.example.sanggar.data.handler.studio.StudioHandler
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.studio.StudioListResponse
import com.example.sanggar.data.model.studio.StudioResponse
import com.example.sanggar.presenter.common.BasePresenter
import okhttp3.MultipartBody

class StudioPresenter(val view: StudioContract.View): BasePresenter(view), StudioContract.Presenter {

    val handler = StudioHandler()

    override fun addStudio(data: HashMap<String, Any?>) {
        handler.addStudio(data)
                .doSubscribe(object : ErrorHandler<StudioResponse>(this){
                    override fun onNext(t: StudioResponse) {
                        view.studioResponse(t)
                    }
                })
    }

    override fun getStudio() {
        handler.getStudio()
                .doSubscribe(object : ErrorHandler<StudioListResponse>(this) {
                    override fun onNext(t: StudioListResponse) {
                        view.getStudioResponse(t)
                    }
                })
    }

    override fun deleteStudio(id: Int) {
        handler.deleteStudio(id)
                .doSubscribe(object : ErrorHandler<EmptyResponse>(this){
                    override fun onNext(t: EmptyResponse) {
                        view.deleteStudioResponse(t)
                    }
                })
    }

    override fun editStudio(id: Int, data: HashMap<String, Any?>) {
        handler.editStudio(id, data)
                .doSubscribe(object : ErrorHandler<StudioResponse>(this) {
                    override fun onNext(t: StudioResponse) {
                        view.studioResponse(t)
                    }
                })
    }

    override fun addImage(id: Int, part: MultipartBody.Part) {
        handler.addImage(id, part)
                .doSubscribe(object : ErrorHandler<StudioResponse>(this) {
                    override fun onNext(t: StudioResponse) {
                        view.uploadImageResponse()
                    }
                })
    }
}