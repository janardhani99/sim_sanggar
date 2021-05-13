package com.example.sanggar.presenter.jadwal_sanggar

import com.example.sanggar.data.model.auth.AuthResponse
import com.example.sanggar.data.model.jadwal_sanggar.JadwalSanggarResponse
import com.example.sanggar.presenter.common.BaseContract
import io.reactivex.Observable
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface JadwalSanggarContract {

    interface View: BaseContract.View {
        fun jadwalSanggarResponse(response: JadwalSanggarResponse)

    }

    interface Presenter {
        fun tambahJadwalSanggar(data: HashMap<String, Any?>)
    }

    interface Handler {
        @FormUrlEncoded
        @POST("jadwal-sanggar")
        fun tambahJadwalSanggar(@FieldMap data: HashMap<String, Any?>): Observable<JadwalSanggarResponse>
    }
}