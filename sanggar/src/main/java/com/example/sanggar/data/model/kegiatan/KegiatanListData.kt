package com.example.sanggar.data.model.kegiatan

import android.os.Parcelable
import com.example.sanggar.data.model.common.BaseResultData
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

data class KegiatanResponse(@Expose var data: KegiatanListItem? = null): BaseResultData()

data class KegiatanListResponse(@Expose var data: List<KegiatanListItem>? = null): BaseResultData()

//parcelable: kirim banyak data sekaligus saat intent
@Parcelize
data class KegiatanListItem(
    @Expose
    var id: Int?= null,
    @Expose
    var judul: String? = null,
    @Expose
    var deskripsi: String? = null,
    @Expose
    var foto: String? = null
): Parcelable
