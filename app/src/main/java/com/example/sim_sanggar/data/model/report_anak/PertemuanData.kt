package com.example.sim_sanggar.data.model.report_anak

import android.os.Parcelable
import com.example.sim_sanggar.data.model.common.BaseResultData
import com.example.sim_sanggar.data.model.jadwal_sanggar.JadwalSanggarItem
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

data class PertemuanDataResponse(@Expose var data: PertemuanData? = null ): BaseResultData()

data class PertemuanDataListResponse(@Expose var data: List<PertemuanData>? = null): BaseResultData()

@Parcelize
class PertemuanData (
        @Expose
        var id: Int? = null,

        @Expose
        var pertemuan_ke: String? = null,

        @Expose
        var tanggal: String? = null,

        @Expose
        var jadwal_sanggar: JadwalSanggarItem? = null

):Parcelable
