package com.example.sanggar.data.model.auth

import com.example.sanggar.data.model.common.BaseResultData
import com.example.sanggar.data.model.sanggar.SanggarData
import com.google.gson.annotations.Expose

class AuthResponse : BaseResultData() {
    @Expose
    var data : AuthData? = null
}

class AuthData {
    @Expose
    var accesToken : String? = null
    @Expose
    var fcmToken : String? = null
    @Expose
    var email : String? = null
    @Expose
    var username : String? = null
    @Expose
    var photoURL : String? = null
    @Expose
    var phone : String? = null
    @Expose
    var sanggar : SanggarData? = null
}