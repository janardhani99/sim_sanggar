package com.example.sim_sanggar.data.model

class HomeCardview  {
    private var foto: String = ""
    private var judul: String = ""
    private var deskripsi: String = ""

    constructor(foto: String, judul: String, deskripsi: String) {
        this.foto = foto
        this.judul = judul
        this.deskripsi = deskripsi
    }

    fun getFoto(): String {
        return foto
    }

    fun setFoto(foto: String) {
        this.foto = foto
    }

    fun getJudul(): String {
        return judul
    }

    fun setJudul(judul: String) {
        this.judul = judul
    }

    fun getDeskripsi(): String {
        return deskripsi
    }

    fun setDeskripsi(deskripsi: String) {
        this.deskripsi = deskripsi
    }

}