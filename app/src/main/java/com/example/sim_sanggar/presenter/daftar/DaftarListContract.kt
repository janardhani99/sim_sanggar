package com.example.sim_sanggar.presenter.daftar

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.daftar.DaftarListResponse
import com.example.sim_sanggar.data.model.daftar.DaftarResponse
import com.example.sim_sanggar.data.model.sewa.SewaResponse
import com.example.sim_sanggar.presenter.common.BaseContract
import retrofit2.http.*
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


interface DaftarListContract {

    interface View: BaseContract.View {
        fun daftarListResponse(response: DaftarResponse)
        fun getDaftarListResponse(response: DaftarListResponse)
        fun deleteDaftarListResponse(response: EmptyResponse)
        fun uploadImageResponse()
//        abstract fun LinearLayoutManager(daftarListFragment: DaftarListFragment): LinearLayoutManager
    }

    interface Presenter {
        fun addListDaftar(data: HashMap<String, Any?>)
        fun getListDaftar()
        fun deleteListDaftar(id: Int)
        fun addImage(id: Int, part: MultipartBody.Part)
        fun uploadBuktiPembayaran( image: File, tf_via: String) {}
    }

    interface Handler {
        @FormUrlEncoded
        @POST("pendaftaran-siswa")
        fun addListDaftar(@FieldMap data: HashMap<String, Any?>): Observable<DaftarResponse>

        @GET("pendaftaran-siswa")
        fun getListDaftar(): Observable<DaftarListResponse>

        @DELETE("pendaftaran-siswa/{id}")
        fun deleteListDaftar(@Path("id")id: Int): Observable<EmptyResponse>

        @Multipart
        @POST("pendaftaran-siswa/{id}/update-image")
        fun addImage(@Path("id") Id: Int, @Part part: MultipartBody.Part): Observable<DaftarResponse>

        @Multipart
        @POST("pendaftaran-siswa/{id}/update-image")
        fun uploadBuktiPembayaran(
//                @Path("id") Id: Int,
                @Part imageData: MultipartBody.Part,
                @PartMap map: HashMap<String, RequestBody>
        ): Observable<DaftarResponse>
    }
}