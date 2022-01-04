package com.example.sanggar.data.model.pembelajaran

import android.os.Parcelable
import com.example.sanggar.data.model.common.BaseResultData
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

data class PembelajaranResponse(@Expose var data: PembelajaranData? = null): BaseResultData()

data class PembelajaranListResponse(@Expose var data: List<PembelajaranData>? = null): BaseResultData()

@Parcelize
data class PembelajaranData (
        @Expose
        var id: Int? = null,

        @Expose
        var judul: String? = null,

        @Expose
        var deskripsi: String? = null,

        @Expose
        var link_video: String? = null
):Parcelable