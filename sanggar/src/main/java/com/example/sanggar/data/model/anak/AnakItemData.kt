package com.example.sanggar.data.model.anak

import android.os.Parcelable
import com.example.sanggar.data.model.common.BaseResultData
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

data class AnakResponse(@Expose var data: AnakListItem? = null): BaseResultData()

data class AnakListResponse(@Expose var data: List<AnakListItem>? = null): BaseResultData()

@Parcelize
data class AnakListItem (
        @Expose
        var id: String,
        @Expose
        var nama: String? = null,
        @Expose
        var alamat: String? = null,
        @Expose
        var tanggal_lahir: String? = null,
        @Expose
        var telepon: String? = null
):Parcelable

