package com.example.sim_sanggar.presenter.sewa

import com.example.sim_sanggar.data.model.sewa.SewaResponse
import com.example.sim_sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*

interface SewaContract {

    interface View: BaseContract.View {
        fun sewaResponse(response: SewaResponse)
        fun uploadImageResponse()
    }

    interface Presenter {
        fun addSewa(data: HashMap<String, Any?>)
        fun addImage(id: Int, part: MultipartBody.Part)
    }

    interface Handler {
        @FormUrlEncoded
        @POST("penyewaan-sanggar")
        fun addSewa(@FieldMap data: HashMap<String, Any?>): Observable<SewaResponse>

        @Multipart
        @POST("penyewaan-sanggar/{id}/update-image")
        fun addImage(@Path("id") Id: Int, @Part part: MultipartBody.Part): Observable<SewaResponse>
    }


}