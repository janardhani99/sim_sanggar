package com.example.sanggar.data.model.jadwal_sanggar

import com.google.gson.annotations.Expose

data class JadwalSanggarResponse(@Expose var data: List<JadwalSanggarItem>? = null)

data class JadwalSanggarItem(
        @Expose
        var hari: String? = null,
        @Expose
        var jamMulai: String? = null,
        @Expose
        var jamSelesai: String? = null
)