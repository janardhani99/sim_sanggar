package com.example.sim_sanggar.data.model.anak

import android.os.Parcelable
import com.example.sim_sanggar.data.model.common.BaseResultData
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

data class AnakResponse(@Expose var data: AnakListItem? = null): BaseResultData()

@Parcelize
data class AnakListItem (
        @Expose
        var id: Int? = null,
        @Expose
        var namaAnak: String? = null,
        @Expose
        var tanggalLahir: String? = null,
        @Expose
        var telepon: String? = null
):Parcelable

