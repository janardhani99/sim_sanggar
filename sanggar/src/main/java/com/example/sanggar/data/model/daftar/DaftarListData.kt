package com.example.sanggar.data.model.daftar

import android.os.Parcelable
import com.example.sanggar.data.model.anak.AnakListItem
import com.example.sanggar.data.model.common.BaseResultData
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

data class DaftarResponse(@Expose var data: PendaftaranAnak? = null): BaseResultData()

data class DaftarListResponse(@Expose var data: List<PendaftaranAnak>? = null): BaseResultData()

@Parcelize
data class PendaftaranAnak (
        @Expose
        var id: Int,

        @Expose
        var transfer_via: String? = null,

        @Expose
        var bukti_pembayaran: String? = null,

        @Expose
        var status: String? = null,

        @Expose
        var anak: AnakListItem? = null
):Parcelable


