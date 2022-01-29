package com.example.sim_sanggar.presenter.sewa

import com.example.sim_sanggar.common.createImageMultipart
import com.example.sim_sanggar.common.createPartFromString
import com.example.sim_sanggar.common.doSubscribe
import com.example.sim_sanggar.data.handler.common.ErrorHandler
import com.example.sim_sanggar.data.handler.sewa.SewaHandler
import com.example.sim_sanggar.data.model.sewa.SewaListResponse
import com.example.sim_sanggar.data.model.sewa.SewaResponse
import com.example.sim_sanggar.presenter.common.BasePresenter
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


class SewaPresenter(val view: SewaContract.View) : BasePresenter(view), SewaContract.Presenter {

    val handler = SewaHandler()

    override fun addSewa(data: HashMap<String, Any?>) {
        handler.addSewa(data)
            .doSubscribe(object : ErrorHandler<SewaResponse>(this) {
                override fun onNext(t: SewaResponse) {
                    view.sewaResponse(t)
                }
            })
    }


    override fun getSewa() {
        handler.getSewa()
            .doSubscribe(object : ErrorHandler<SewaListResponse>(this) {
                override fun onNext(t: SewaListResponse) {
                    view.getSewaResponse(t)
                }
            })
    }

    override fun getTanggalTersewa(tanggal: String) {
        tanggal.let {
            handler.getTanggalTersewa(it)
                .doSubscribe(object : ErrorHandler<SewaListResponse>(this){
                    override fun onNext(t: SewaListResponse) {
                        view.getSewaResponse(t)
                    }
                })
        }
    }

    override fun uploadBukti(id: Int, data: HashMap<String, Any?>) {
        handler.uploadBukti(id, data)
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

    override fun uploadBuktiPembayaran(id: Int, image: File, tf_via: String) {
        super.uploadBuktiPembayaran(id, image, tf_via)
        val imagePart = image.createImageMultipart()
        val map = hashMapOf<String, RequestBody>(
            "transfer_via" to tf_via.createPartFromString()
        )
        handler.uploadBuktiPembayaran(id, imagePart, map)
            .doSubscribe(object : ErrorHandler<SewaResponse>(this) {
                override fun onNext(t: SewaResponse) {
                    view.sewaResponse(t)
                }
            })
    }
}