package com.example.sanggar.data.model.profile

import android.os.Parcelable
import com.example.sanggar.data.model.common.BaseResultData
import com.example.sanggar.data.model.sanggar.SanggarData
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

data class ProfileResponse(@Expose var data: ProfileData? = null): BaseResultData()

data class ProfileListResponse(@Expose var data: List<ProfileData>? = null): BaseResultData()

@Parcelize
data class ProfileData (
        @Expose
        var id: Int? = null,
        @Expose
        var username: String? = null,
        @Expose
        var email: String? = null,
        @Expose
        var telepon: String? = null,
        @Expose
        var photoUrl: String? = null,
        @Expose
        var sanggar : SanggarData? = null

): Parcelable