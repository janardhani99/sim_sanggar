package com.example.sanggar.data.model.jam_operasional

import com.google.gson.annotations.Expose

data class JamOperasionalData(
        @Expose
        var data: List<JamOperasionalItem>? = null
)

data class JamOperasionalItem(
        @Expose
        var hari: String? = null,
        @Expose
        var jamMulai: String? = null,
        @Expose
        var jamSelesai: String? = null,
        @Expose
        var status: Boolean? =false
)