package com.example.sim_sanggar.presenter.platform_transaksi

import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.platform_transaksi.PlatformTransaksiListResponse
import com.example.sim_sanggar.data.model.platform_transaksi.PlatformTransaksiResponse
import com.example.sim_sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.*

interface PlatformTransaksiContract {
    interface View: BaseContract.View {
//        fun platformTransaksiResponse(response: PlatformTransaksiResponse)
        fun getPlatformTransaksiResponse(response: PlatformTransaksiListResponse)
    }

    interface Presenter {
        fun getPlatformTransaksi()
    }

    interface Handler {
        @GET("platform-transaksi")
        fun getPlatformTransaksi(): Observable<PlatformTransaksiListResponse>
    }
}