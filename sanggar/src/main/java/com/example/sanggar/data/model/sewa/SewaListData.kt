package com.example.sanggar.data.model.sewa

import android.os.Parcelable
import com.example.sanggar.data.model.anak.AnakListItem
import com.example.sanggar.data.model.common.BaseResultData
import com.example.sanggar.data.model.user_sanggar.UserSanggarItem
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

data class SewaResponse(@Expose var data: SewaListItem? = null): BaseResultData()

data class SewaListResponse(@Expose var data: List<SewaListItem>? = null): BaseResultData()

//parcelable: kirim banyak data sekaligus saat intent
@Parcelize
data class SewaListItem(
        @Expose
        var id: Int,

//        @Expose
//        var username: String? = null,

        @Expose
        var tanggal: String? = null,

        @Expose
        var jam_mulai: String? = null,

        @Expose
        var jam_selesai: String? = null,

        @Expose
        var status: String? = null,

        @Expose
        var user: UserSanggarItem? = null,

        @Expose
        var transfer_via: String? = null,

        @Expose
        var bukti_pembayaran: String? = null

): Parcelable