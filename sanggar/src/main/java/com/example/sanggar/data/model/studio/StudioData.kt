package com.example.sanggar.data.model.studio

import android.os.Parcelable
import com.example.sanggar.data.model.common.BaseResultData
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

data class StudioResponse(@Expose var data: StudioData? = null): BaseResultData()

data class StudioListResponse(@Expose var data: List<StudioData>? = null): BaseResultData()

@Parcelize
data class StudioData(
        @Expose
        var id: Int? = null,
        @Expose
        var nama_studio: String? = null,
        @Expose
        var harga: String? = null,
        @Expose
        var ukuran: String? = null,
        @Expose
        var deskripsi: String? = null,
        @Expose
        var foto: String? = null
): Parcelable