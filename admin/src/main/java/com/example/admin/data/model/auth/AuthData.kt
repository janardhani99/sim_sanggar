package com.example.admin.data.model.auth

import com.example.admin.data.model.common.BaseResultData
import com.google.gson.annotations.Expose

class AuthResponse : BaseResultData() {
    @Expose
    var data : AuthData? = null
}

class AuthData {
    @Expose
    var accessToken : String? = null
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
}