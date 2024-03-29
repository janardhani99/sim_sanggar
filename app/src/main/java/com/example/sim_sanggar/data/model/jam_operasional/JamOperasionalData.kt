package com.example.sim_sanggar.data.model.jam_operasional

import com.example.sim_sanggar.data.model.common.BaseResultData
import com.google.gson.annotations.Expose

class JamOperasionalResponse: BaseResultData() {
        @Expose
        var data: List<JamOperasionalItem>? = null
}

class JamOperasionalItem(
        @Expose
        var hari: String? = null,
        @Expose
        var jam_mulai: String? = null,
        @Expose
        var jam_selesai: String? = null,
        @Expose
        var status: Boolean? =false
)