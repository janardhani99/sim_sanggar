package com.example.sanggar.data.model.jadwal_sanggar

import com.example.sanggar.data.model.common.BaseResultData
import com.google.gson.annotations.Expose

class JadwalSanggarResponse: BaseResultData() {
        @Expose
        var data: JadwalSanggarItem? = null
}

class JadwalSanggarListResponse : BaseResultData() {
        @Expose
        var data: List<JadwalSanggarItem>? = null
}

class JadwalSanggarItem {
        @Expose
        var id: Int? = null

        @Expose
        var kategori_latihan: String? = null

        @Expose
        var hari: String? = null

        @Expose
        var jam_mulai: String? = null

        @Expose
        var jam_selesai: String? = null
}