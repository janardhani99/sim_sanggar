package com.example.sim_sanggar.presenter.anak

import com.example.sim_sanggar.data.model.anak.AnakListResponse
import com.example.sim_sanggar.data.model.anak.AnakResponse
import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.*


interface AnakContract {

    interface View: BaseContract.View {
        fun anakResponse(response: AnakResponse)
        fun getAnakResponse(response: AnakListResponse)
        fun deleteAnakResponde(response: EmptyResponse)
    }

    interface Presenter {
        fun addAnak(data: HashMap<String, Any?>)
        fun getAnak()
        fun editAnak(id: Int, data: HashMap<String, Any?>)
        fun deleteAnak(id: Int)
    }

    interface Handler {
        @FormUrlEncoded
        @POST("anak")
        fun addAnak(@FieldMap data: HashMap<String, Any?>): Observable<AnakResponse>

        @GET("anak")
        fun getAnak(): Observable<AnakListResponse>

        @FormUrlEncoded
        @PATCH("anak/{id}")
        fun editAnak(@Path("id")id: Int, @FieldMap data: HashMap<String, Any?>):Observable<AnakResponse>

        @DELETE("anak/{id}")
        fun deleteAnak (@Path("id")id: Int):Observable<EmptyResponse>
    }
}