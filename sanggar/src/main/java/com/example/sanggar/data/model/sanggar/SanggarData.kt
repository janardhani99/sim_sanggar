package com.example.sanggar.data.model.sanggar

import android.os.Parcelable
import com.example.sanggar.data.model.common.BaseResultData
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

data class ProfilSanggarResponse(@Expose var data: SanggarData? = null): BaseResultData()

data class ProfilSanggarListResponse(@Expose var data: List<SanggarData>? = null): BaseResultData()

@Parcelize
data class SanggarData (
//    @Expose
//    var accessToken : String? = null
    @Expose
    var id : Int? = null,
    @Expose
    var nama_sanggar : String? = null,
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
    var harga_pendaftaran_siswa : String? = null,
    @Expose
    var harga_penyewaan_perjam : String? = null
//    @Expose
//    var jam_operasional : String? = null
//    @Expose
//    var jadwal_sanggar : String? = null
):Parcelable