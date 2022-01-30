package com.example.sanggar.data.model.jam_operasional

import com.example.sanggar.data.model.common.BaseResultData
import com.google.gson.annotations.Expose

class JamOperasionalResponse: BaseResultData() {
        @Expose
        var data: JamOperasionalItem? = null
}
class JamOperasionalListResponse: BaseResultData() {
        @Expose
        var data: List<JamOperasionalItem>? = null
}

class JamOperasionalItem {
        @Expose
        var id: Int? = null

        @Expose
        var hari: String? = null

        @Expose
        var jam_mulai: String? = null

        @Expose
        var jam_selesai: String? = null

        @Expose
        var status: String? = null
}