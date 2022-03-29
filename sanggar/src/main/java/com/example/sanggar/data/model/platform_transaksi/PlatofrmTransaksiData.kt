package com.example.sanggar.data.model.platform_transaksi

import android.os.Parcelable
import com.example.sanggar.data.model.common.BaseResultData
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

data class PlatformTransaksiResponse(@Expose var data: PlatformTransaksiListItem? = null): BaseResultData()

data class PlatformTransaksiListResponse(@Expose var data: List<PlatformTransaksiListItem>? = null): BaseResultData()

@Parcelize
data class PlatformTransaksiListItem(
        @Expose
        var id: Int?= null,
        @Expose
        var nama_platform: String? = null,
        @Expose
        var nama_pemilik: String? = null,
        @Expose
        var nomor_rekening: String? = null
): Parcelable