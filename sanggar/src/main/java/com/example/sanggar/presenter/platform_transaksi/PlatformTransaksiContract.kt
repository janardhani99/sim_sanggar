package com.example.sanggar.presenter.platform_transaksi

import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.platform_transaksi.PlatformTransaksiListResponse
import com.example.sanggar.data.model.platform_transaksi.PlatformTransaksiResponse
import com.example.sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.*

interface PlatformTransaksiContract {
    interface View: BaseContract.View {
        fun platformTransaksiResponse(response: PlatformTransaksiResponse)
        fun getPlatformTransaksiResponse(response: PlatformTransaksiListResponse)
        fun deletePlatformTransaksiResponse(response: EmptyResponse)
    }

    interface Presenter {
        fun addPlatformTransaksi(data: HashMap<String, Any?>)
        fun getPlatformTransaksi()
        fun deletePlatformTransaksi(id: Int)
        fun editPlatformTransaksi(id:  Int, data: HashMap<String, Any?>)
    }

    interface Handler {
        @FormUrlEncoded
        @POST("platform-transaksi")
        fun addPlatformTransaksi(@FieldMap data: HashMap<String, Any?>): Observable<PlatformTransaksiResponse>

        @GET("platform-transaksi")
        fun getPlatformTransaksi(): Observable<PlatformTransaksiListResponse>

        @DELETE("platform-transaksi/{id}")
        fun deletePlatformTransaksi(@Path("id") id: Int): Observable<EmptyResponse>

        @FormUrlEncoded
        @PATCH("platform-transaksi/{id}")
        fun editPlatformTransaksi(@Path("id")id: Int, @FieldMap data: HashMap<String, Any?>): Observable<PlatformTransaksiResponse>

    }
}