package com.example.sanggar.data.model.user_sanggar

import android.os.Parcelable
import com.example.sanggar.data.model.common.BaseResultData
import com.example.sanggar.data.model.sanggar.SanggarData
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

data class UserSanggarResponse(@Expose var data: UserSanggarItem? = null): BaseResultData()

data class UserSanggarListResponse(@Expose var data: List<UserSanggarItem>? = null): BaseResultData()

@Parcelize
data class UserSanggarItem(
        @Expose
        var id: Int? = null,

        @Expose
        var username: String? = null,

        @Expose
        var email : String? = null,

        @Expose
        var role: String? = null,

        @Expose
        var telepon: String? = null,

        @Expose
        var sanggar : SanggarData? = null

): Parcelable