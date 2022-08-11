package com.example.admin.data.model.user

import android.os.Parcelable
import com.example.admin.data.model.common.BaseResultData
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

data class SanggarResponse(@Expose var data: SanggarData? = null): BaseResultData()

data class SanggarListResponse(@Expose var data: List<SanggarData>? = null): BaseResultData()

@Parcelize
data class SanggarData (
//    @Expose
//    var accessToken : String? = null
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
    var harga_pendaftaran_siswa : String? = null,
    @Expose
    var harga_penyewaan_perjam : String? = null

):Parcelable