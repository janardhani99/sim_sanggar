package com.example.sim_sanggar.presenter.pembelajaran

import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.pembelajaran.PembelajaranListResponse
import com.example.sim_sanggar.data.model.pembelajaran.PembelajaranResponse
import com.example.sim_sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.*
import kotlin.collections.HashMap

interface PembelajaranContract {

    interface View: BaseContract.View {

        fun getPembelajaranResponse(response: PembelajaranListResponse)

    }

    interface Presenter {

        fun getPembelajaran()

    }

    interface Handler {
        @GET("pembelajaran")
        fun getPembelajaran(): Observable<PembelajaranListResponse>
    }
}