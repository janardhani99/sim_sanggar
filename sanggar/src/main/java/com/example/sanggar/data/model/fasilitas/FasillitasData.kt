package com.example.sanggar.data.model.fasilitas

import android.os.Parcelable
import com.example.sanggar.data.model.common.BaseResultData
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

data class FasilitasResponse(@Expose var data: FasilitasListItem? = null): BaseResultData()

data class FasilitasListResponse(@Expose var data: List<FasilitasListItem>? = null): BaseResultData()

@Parcelize
data class FasilitasListItem(
    @Expose
    var id: Int? = null,
    @Expose
    var judul: String? = null,
    @Expose
    var deskripsi: String? = null,
    @Expose
    var foto: String? = null
): Parcelable