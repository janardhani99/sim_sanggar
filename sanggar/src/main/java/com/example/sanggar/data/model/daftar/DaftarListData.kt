package com.example.sanggar.data.model.daftar

import android.os.Parcelable
import com.example.sanggar.data.model.common.BaseResultData
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

data class DaftarResponse(@Expose var data: DaftarListItem? = null): BaseResultData()

data class DaftarListResponse(@Expose var data: List<DaftarListItem>? = null): BaseResultData()

@Parcelize
data class DaftarListItem(
        @Expose
        var id: Int? = null,

        @Expose
        var user_name: String? = null,

        @Expose
        var name: String? = null,

        @Expose
        var umur: String? = null,

        @Expose
        var tanggal_lahir: String? = null,

        @Expose
        var no_telp: String? = null
):Parcelable