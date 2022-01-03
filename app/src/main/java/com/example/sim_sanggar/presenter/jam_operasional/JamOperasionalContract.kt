package com.example.sim_sanggar.presenter.jam_operasional

import com.example.sim_sanggar.data.model.jam_operasional.JamOperasionalResponse
import com.example.sim_sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.GET


interface JamOperasionalContract {

    interface View: BaseContract.View {
        fun getJamOperasionalResponse(response: JamOperasionalResponse)
    }

    interface Presenter {
        fun getJamOperasional()
    }

    interface Handler {
        @GET("jam-operasional")
        fun getJamOperasional(): Observable<JamOperasionalResponse>
    }
}