package com.example.sim_sanggar.data.model.sanggar

import android.os.Parcelable
import com.example.sim_sanggar.data.model.common.BaseResultData
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

data class SanggarResponse(@Expose var data: SanggarData? = null): BaseResultData()

data class SanggarListResponse(@Expose var data: List<SanggarData>? = null): BaseResultData()

@Parcelize
data class SanggarData (

    @Expose
    var id : Int? = null,
    @Expose
    var nama : String? = null,
    @Expose
    var alamat : String? = null,
    @Expose
    var telepon : String? = null,
    @Expose
    var bank : String? = null,
    @Expose
    var nomor_rekening : String? = null,
    @Expose
    var foto_profil : String? = null,
    @Expose
    var harga_pendaftaran_siswa : Int? = null,
    @Expose
    var harga_penyewaan_siswa : Int? = null
//    @Expose
//    var jam_operasional : String? = null
//    @Expose
//    var jadwal_sanggar : String? = null
): Parcelable