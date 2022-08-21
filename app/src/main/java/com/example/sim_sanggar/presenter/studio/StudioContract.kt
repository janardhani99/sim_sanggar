package com.example.sim_sanggar.presenter.studio

import com.example.sim_sanggar.data.model.studio.StudioListResponse
import com.example.sim_sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.*

interface StudioContract {

    interface View: BaseContract.View {
        fun getStudioResponse(response: StudioListResponse)
    }

    interface Presenter {
        fun getStudio()
    }
    interface Handler {

        @GET("studio")
        fun getStudio(): Observable<StudioListResponse>

    }
}