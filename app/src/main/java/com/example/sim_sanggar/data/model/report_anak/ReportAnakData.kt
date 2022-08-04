package com.example.sim_sanggar.data.model.report_anak

import android.os.Parcelable
import com.example.sim_sanggar.data.model.anak.AnakListItem
import com.example.sim_sanggar.data.model.report_anak.PertemuanData
import com.example.sim_sanggar.data.model.common.BaseResultData
import com.example.sim_sanggar.data.model.daftar.PendaftaranAnak
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

data class ReportAnakResponse(@Expose var data: ReportAnakData? = null): BaseResultData()

data class ReportAnakListResponse(@Expose var data: List<ReportAnakData>? = null): BaseResultData()

@Parcelize
data class ReportAnakData (
        @Expose
        var id: Int? = null,

        @Expose
        var pembayaran: String? = null,

        @Expose
        var kehadiran: String? = null,

        @Expose
        var catatan_progress: String? = null,

        @Expose
        var anak: PendaftaranAnak? = null,

        @Expose
        var pertemuan: PertemuanData? = null


):Parcelable