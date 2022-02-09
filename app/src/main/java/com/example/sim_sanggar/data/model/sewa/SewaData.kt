package com.example.sim_sanggar.data.model.sewa

import android.os.Parcelable
import com.example.sim_sanggar.data.model.common.BaseResultData
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

data class SewaResponse(@Expose var data: SewaListItem? = null): BaseResultData()
data class SewaListResponse(@Expose var data: List<SewaListItem>? = null): BaseResultData()

@Parcelize
data class SewaListItem(
        @Expose
        var id: Int? = null,
        @Expose
        var user_id: Int? = null,
        @Expose
        var tanggal: String? = null,
        @Expose
        var jam_mulai: String? = null,
        @Expose
        var jam_selesai: String? = null,
        @Expose
        var metodePembayaran: String? = null,
        @Expose
        var transferVia: String? = null,
        @Expose
        var foto: String? = null,
        @Expose
        var status: String? = null

):Parcelable