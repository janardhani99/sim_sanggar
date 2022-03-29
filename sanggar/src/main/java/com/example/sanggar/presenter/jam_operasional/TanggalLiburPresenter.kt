package com.example.sanggar.presenter.jam_operasional

import com.example.sanggar.common.doSubscribe
import com.example.sanggar.data.handler.common.ErrorHandler
import com.example.sanggar.data.handler.jam_operasional.TanggalLiburHandler
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.jam_operasional.TanggalLiburListResponse
import com.example.sanggar.data.model.jam_operasional.TanggalLiburResponse
import com.example.sanggar.presenter.common.BasePresenter
import com.example.sanggar.presenter.jam_operasional.TanggalLiburContract

class TanggalLiburPresenter(val view: TanggalLiburContract.View): BasePresenter(view), TanggalLiburContract.Presenter {

    val handler = TanggalLiburHandler()

    override fun tambahTanggalLibur(data: HashMap<String, Any?>) {
        handler.tambahTanggalLibur(data)
                .doSubscribe(object: ErrorHandler<TanggalLiburResponse>(this) {
                    override fun onNext(t: TanggalLiburResponse) {
                        view.tanggalLiburResponse(t)
                    }
                })
    }

    override fun getTanggalLibur() {
        handler.getTanggalLibur()
                .doSubscribe(object: ErrorHandler<TanggalLiburListResponse>(this){
                    override fun onNext(t: TanggalLiburListResponse) {
                        view.getTanggalLiburResponse(t)
                    }

                })
    }

    override fun editTanggalLibur(id: Int, data: HashMap<String, Any?>) {
        handler.editTanggalLibur(id, data)
                .doSubscribe(object : ErrorHandler<TanggalLiburResponse>(this){
                    override fun onNext(t: TanggalLiburResponse) {
                        view.tanggalLiburResponse(t)
                    }

                })
    }

    override fun deleteTanggalLibur(id: Int) {
        handler.deleteTanggalLibur(id)
                .doSubscribe(object : ErrorHandler<EmptyResponse>(this){
                    override fun onNext(t: EmptyResponse) {
                        view.deleteTanggalLiburResponse(t)
                    }
                })
    }


}