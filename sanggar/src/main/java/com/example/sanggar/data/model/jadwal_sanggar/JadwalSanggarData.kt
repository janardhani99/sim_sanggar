package com.example.sanggar.data.model.jadwal_sanggar

import com.example.sanggar.data.model.common.BaseResultData
import com.google.gson.annotations.Expose

class JadwalSanggarResponse: BaseResultData() {
        @Expose
        var data: JadwalSanggarItem? = null
}

class JadwalSanggarItem {
        @Expose
        var accesToken: String? = null

        @Expose
        var hari: String? = null

        @Expose
        var jamMulai: String? = null

        @Expose
        var jamSelesai: String? = null
}