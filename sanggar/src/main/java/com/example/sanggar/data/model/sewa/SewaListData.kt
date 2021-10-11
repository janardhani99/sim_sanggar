package com.example.sanggar.data.model.sewa

import android.os.Parcelable
import com.example.sanggar.data.model.common.BaseResultData
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

data class SewaResponse(@Expose var data: SewaListItem? = null): BaseResultData()

data class SewaListResponse(@Expose var data: List<SewaListItem>? = null): BaseResultData()

//parcelable: kirim banyak data sekaligus saat intent
@Parcelize
data class SewaListItem(
        @Expose
        var id: Int? = null,

//        @Expose
//        var username: String? = null,

        @Expose
        var tanggal: String? = null

): Parcelable