package com.example.sim_sanggar.presenter.daftar

import com.example.sim_sanggar.common.createImageMultipart
import com.example.sim_sanggar.common.createPartFromString
import com.example.sim_sanggar.common.doSubscribe
import com.example.sim_sanggar.data.handler.common.ErrorHandler
import com.example.sim_sanggar.data.handler.daftar.DaftarListHandler
import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.daftar.DaftarListResponse
import com.example.sim_sanggar.data.model.daftar.DaftarResponse
import com.example.sim_sanggar.presenter.common.BasePresenter
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class DaftarListPresenter(val view: DaftarListContract.View): BasePresenter(view), DaftarListContract.Presenter {

    val handler = DaftarListHandler()

    override fun addListDaftar(data: HashMap<String, Any?>) {
        handler.addListDaftar(data)
                .doSubscribe(object: ErrorHandler<DaftarResponse>(this){
                    override fun onNext(t: DaftarResponse) {
                        view.daftarListResponse(t)
                    }
                })
    }

    override fun getListDaftar() {
        handler.getListDaftar()
                .doSubscribe(object: ErrorHandler<DaftarListResponse>(this){
                    override fun onNext(t: DaftarListResponse) {
                        view.getDaftarListResponse(t)
                    }
                })
    }

    override fun getBiayaPendaftaran(biaya_pendaftaran: String) {
        biaya_pendaftaran.let {
            handler.getBiayaPendaftaran(it)
                    .doSubscribe(object : ErrorHandler<DaftarListResponse>(this){
                        override fun onNext(t: DaftarListResponse) {
                            view.getDaftarListResponse(t)
                        }
                    })
        }
    }

    override fun deleteListDaftar(id: Int) {
        handler.deleteListDaftar(id)
                .doSubscribe(object: ErrorHandler<EmptyResponse>(this){
                    override fun onNext(t: EmptyResponse) {
                        view.deleteDaftarListResponse(t)
                    }
                })
    }

    override fun addImage(id: Int, part: MultipartBody.Part) {
        handler.addImage(id, part)
                .doSubscribe(object : ErrorHandler<DaftarResponse>(this){
                    override fun onNext(t: DaftarResponse) {
                        view.uploadImageResponse()
                    }
                })
    }

    override fun uploadBuktiPembayaran(id: Int, image: File, tf_via: String, status: String) {
        super.uploadBuktiPembayaran(id, image, tf_via, status)
        val imagePart = image.createImageMultipart()
        val map = hashMapOf<String, RequestBody>()
        map.put("transfer_via", tf_via.createPartFromString())
        map.put("status", status.createPartFromString())
        handler.uploadBuktiPembayaran(id, imagePart, map)
                .doSubscribe(object : ErrorHandler<DaftarResponse>(this) {
                    override fun onNext(t: DaftarResponse) {
                        view.daftarListResponse(t)
                    }
                })
    }
}