package com.example.sim_sanggar.data.model.daftar

import android.os.Parcelable
import com.example.sim_sanggar.data.model.anak.AnakListItem
import com.example.sim_sanggar.data.model.common.BaseResultData
import com.example.sim_sanggar.data.model.jadwal_sanggar.JadwalSanggarItem
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

data class DaftarResponse(@Expose var data: PendaftaranAnak? = null): BaseResultData()

data class DaftarListResponse(@Expose var data: List<PendaftaranAnak>? = null): BaseResultData()

@Parcelize
data class PendaftaranAnak (
        @Expose
        var id: Int? = null,

        @Expose
        var transfer_via: String? = null,

        @Expose
        var bukti_pembayaran: String? = null,

        @Expose
        var status: String? = null,

        @Expose
        var anak_id: AnakListItem? = null,

        @Expose
        var sanggar_id: Int? = null,

        @Expose
        var jadwal_sanggar_id: JadwalSanggarItem? = null
):Parcelable


