package com.example.sanggar.data.model.kegiatan

import com.google.gson.annotations.Expose

data class KegiatanListResponse(@Expose var data: KegiatanListItem? = null)

data class KegiatanListItem(
        @Expose var id: Int?=0,
        @Expose var judul: String? = null,
        @Expose var deskripsi: String? = null,
        @Expose var foto: String? = null
)