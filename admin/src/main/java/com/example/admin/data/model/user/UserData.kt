package com.example.admin.data.model.user

import android.os.Parcelable
import com.example.admin.data.model.common.BaseResultData
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

data class UserResponse(@Expose var data: UserData? = null): BaseResultData()

data class UserListResponse(@Expose var data: List<UserData>? = null): BaseResultData()

@Parcelize
data class UserData (
        @Expose
        var id: Int? = null,
        @Expose
        var username: String? = null,
        @Expose
        var email: String? = null
//        @Expose
//        var sanggar : SanggarData? = null

): Parcelable