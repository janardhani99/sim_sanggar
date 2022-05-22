package com.example.sim_sanggar.data.model.jadwal_sanggar

import android.os.Parcelable
import com.example.sim_sanggar.data.model.common.BaseResultData
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

class JadwalSanggarResponse: BaseResultData() {
        @Expose
        var data: JadwalSanggarItem? = null
}

class JadwalSanggarListResponse : BaseResultData() {
        @Expose
        var data: List<JadwalSanggarItem>? = null
}

@Parcelize
class JadwalSanggarItem (
        @Expose
        var id: Int? = null,

        @Expose
        var kategori_latihan: String? = null,

        @Expose
        var hari: String? = null,

        @Expose
        var jam_mulai: String? = null,

        @Expose
        var jam_selesai: String? = null,

        @Expose
        var biaya: String? = null
):Parcelable