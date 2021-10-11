package com.example.sanggar.presenter.sewa

import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.sewa.SewaListResponse
import com.example.sanggar.data.model.sewa.SewaResponse
import com.example.sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.*

interface SewaListContract {

    interface View: BaseContract.View {
        fun sewaListResponse(response: SewaResponse)
        fun getSewaListResponse(response: SewaListResponse)
        fun deleteSewaListResponse(response: EmptyResponse)
    }

    interface Presenter {
        fun addListSewa(data: HashMap<String, Any?>)
        fun getListSewa()
        fun deleteListSewa(id: Int)
    }

    interface Handler {
        @FormUrlEncoded
        @POST("penyewaan-sanggar")
        fun addListSewa(@FieldMap data: HashMap<String, Any?>): Observable<SewaResponse>

        @GET("penyewaan-sanggar")
        fun getListSewa(): Observable<SewaListResponse>

        @DELETE("penyewaan-sanggar/{id}")
        fun deleteListSewa(@Path("id")id: Int): Observable<EmptyResponse>
    }
}