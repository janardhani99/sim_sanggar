package com.example.sim_sanggar.data.model.jam_operasional
import com.example.sim_sanggar.data.model.common.BaseResultData
import com.google.gson.annotations.Expose

class TanggalLiburResponse: BaseResultData() {
        @Expose
        var data: TanggalLiburItem? = null
}
class TanggalLiburListResponse: BaseResultData() {
        @Expose
        var data: List<TanggalLiburItem>? = null
}

class TanggalLiburItem {
        @Expose
        var id: Int? = null

        @Expose
        var tanggal_libur: String? = null

        @Expose
        var keterangan: String? = null

}