package com.example.sim_sanggar.presenter.sewa

import com.example.sim_sanggar.data.model.sewa.SewaListResponse
import com.example.sim_sanggar.data.model.sewa.SewaResponse
import com.example.sim_sanggar.data.model.studio.StudioData
import com.example.sim_sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import java.io.File

interface SewaContract {

    interface View : BaseContract.View {
        fun sewaResponse(response: SewaResponse)
        fun getSewaResponse(response: SewaListResponse)
        fun getTanggalSewaResponse(response: SewaListResponse)
        fun uploadImageResponse()
    }

    interface Presenter {
        fun addSewa(data: HashMap<String, Any?>)
        fun getSewa()
        fun getTanggalTersewa(studio: Int, tanggal: String)
        fun uploadBukti(id: Int, data: HashMap<String, Any?>)
        fun addImage(id: Int, part: MultipartBody.Part)
        fun uploadBuktiPembayaran(id: Int, image: File, tf_via: String, status: String) {}
        fun getSewaStatus1(status: String)
    }

    interface Handler {
        @FormUrlEncoded
        @POST("penyewaan-sanggar")
        fun addSewa(@FieldMap data: HashMap<String, Any?>): Observable<SewaResponse>

        @GET("penyewaan-sanggar")
        fun getSewa(): Observable<SewaListResponse>

//        @GET("penyewaan/tanggal")
//        fun getTanggalTersewa(@Query ("date") tanggal: String): Observable<SewaListResponse>

        @GET("penyewaan/tanggal")
        fun getTanggalTersewa(
            @Query("studio") studio: Int,
            @Query ("date") tanggal: String
        ): Observable<SewaListResponse>

        @FormUrlEncoded
        @PATCH("penyewaan-sanggar/{id}")
        fun uploadBukti(
            @Path("id") id: Int,
            @FieldMap data: HashMap<String, Any?>
        ): Observable<SewaResponse>

        @Multipart
        @POST("penyewaan-sanggar/{id}/update-image")
        fun addImage(@Path("id") Id: Int, @Part part: MultipartBody.Part): Observable<SewaResponse>

        @Multipart
        @POST("penyewaan-sanggar/{id}/update-image")
        fun uploadBuktiPembayaran(
            @Path("id") Id: Int,
            @Part imageData: MultipartBody.Part,
            @PartMap map: HashMap<String, RequestBody>
        ): Observable<SewaResponse>

        @GET("penyewaanstatus1")
        fun getSewaStatus1(@Query("status") status: String): Observable<SewaListResponse>

    }


}