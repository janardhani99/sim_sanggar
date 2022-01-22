package com.example.sanggar.data.model.absensi

import android.os.Parcelable
import com.example.sanggar.data.model.anak.AnakListItem
import com.example.sanggar.data.model.absensi.PertemuanData
import com.example.sanggar.data.model.common.BaseResultData
import com.example.sanggar.data.model.daftar.PendaftaranAnak
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

data class ProgressAnakResponse(@Expose var data: ProgressAnakData? = null): BaseResultData()

data class ProgressAnakListResponse(@Expose var data: List<ProgressAnakData>? = null): BaseResultData()

@Parcelize
class ProgressAnakData (
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