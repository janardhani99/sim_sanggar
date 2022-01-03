package com.example.sim_sanggar.data.handler.daftar

import com.example.sim_sanggar.presenter.daftar.DaftarListContract
import com.example.sim_sanggar.data.handler.common.BaseHandler
import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.daftar.DaftarListResponse
import com.example.sim_sanggar.data.model.daftar.DaftarResponse
import com.example.sim_sanggar.data.model.sewa.SewaResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody

class DaftarListHandler: BaseHandler() {

    val service = getClient().create(DaftarListContract.Handler::class.java)

    fun addListDaftar(data: HashMap<String, Any?>): Observable<DaftarResponse> {
        return service.addListDaftar(data)
    }

    fun getListDaftar(): Observable<DaftarListResponse> {
        return service.getListDaftar()
    }

    fun deleteListDaftar(id: Int): Observable<EmptyResponse> {
        return service.deleteListDaftar(id)
    }

    fun uploadBuktiPembayaran(
//            id: Int,
            imagePart: MultipartBody.Part,
            mapPart: HashMap<String, RequestBody>
    ): Observable<DaftarResponse> {
        return service.uploadBuktiPembayaran(imagePart, mapPart)
    }

    fun addImage(id: Int, part: MultipartBody.Part): Observable<DaftarResponse> {
        return service.addImage(id, part)
    }
}