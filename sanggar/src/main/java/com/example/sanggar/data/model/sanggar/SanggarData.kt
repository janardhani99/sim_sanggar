package com.example.sanggar.data.model.sanggar

import com.example.sanggar.data.model.common.BaseResultData
import com.google.gson.annotations.Expose

class ProfilSanggarResponse: BaseResultData() {
    var data: SanggarData? = null
}

class SanggarData {
//    @Expose
//    var accessToken : String? = null
    @Expose
    var id : Int? = null
    @Expose
    var nama_sanggar : String? = null
    @Expose
    var alamat : String? = null
    @Expose
    var telepon : String? = null
    @Expose
    var bank : String? = null
    @Expose
    var nomor_rekening : String? = null
    @Expose
    var foto_profil : String? = null
    @Expose
    var harga_pendaftaran_siswa : Int? = null
    @Expose
    var harga_penyewaan_siswa : Int? = null
//    @Expose
//    var jam_operasional : String? = null
//    @Expose
//    var jadwal_sanggar : String? = null
}