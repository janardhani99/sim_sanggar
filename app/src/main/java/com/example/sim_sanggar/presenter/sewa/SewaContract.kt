package com.example.sim_sanggar.presenter.sewa

import com.example.sim_sanggar.data.model.sewa.SewaListResponse
import com.example.sim_sanggar.data.model.sewa.SewaResponse
import com.example.sim_sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*

interface SewaContract {

    interface View: BaseContract.View {
        fun sewaResponse(response: SewaResponse)
        fun getSewaResponse(response: SewaListResponse)
        fun uploadImageResponse()
    }

    interface Presenter {
        fun addSewa(data: HashMap<String, Any?>)
        fun getSewa()
        fun addImage(id: Int, part: MultipartBody.Part)
    }

    interface Handler {
        @FormUrlEncoded
        @POST("penyewaan-sanggar")
        fun addSewa(@FieldMap data: HashMap<String, Any?>): Observable<SewaResponse>

        @GET("penyewaan-sanggar")
        fun getSewa():Observable<SewaListResponse>

        @Multipart
        @POST("penyewaan-sanggar/{id}/update-image")
        fun addImage(@Path("id") Id: Int, @Part part: MultipartBody.Part): Observable<SewaResponse>
    }


}