package com.example.sanggar.common

import android.content.Context
import android.content.SharedPreferences

class Preferences(context: Context) {

    private var sharedPreferences: SharedPreferences =
            context.getSharedPreferences("sharedPreference", Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor
    private val ACCESS_TOKEN = "access_token"
    private val FIRST_LAUNCH = "first_launch"
    private val USER_LOGGED_IN = "logged_in"
    private val USER_PHONE = "phone_number"
    private val TUTORIAL_LAUNCH = "first_tutorial_launch"
    private val USER_ID = "user_id"
    private val FCM_TOKEN = "fcm_token"

    init {
        editor = sharedPreferences.edit()
    }

    fun userLoggedOut() {
        editor.clear()
        userLoggedIn = false
    }

    var firebaseToken: String?
        set(value) = editor.putString(FCM_TOKEN, value).apply()
        get() = sharedPreferences.getString(FCM_TOKEN, "")

    var userId: Int
        set(value) = editor.putInt(USER_ID, value).apply()
        get() = sharedPreferences.getInt(USER_ID, 0)

    var accessToken: String?
        set(value) = editor.putString(ACCESS_TOKEN, value).apply()
        get() = sharedPreferences.getString(ACCESS_TOKEN, "")

    var firstLaunch: Boolean
        set(value) = editor.putBoolean(FIRST_LAUNCH, value).apply()
        get() = sharedPreferences.getBoolean(FIRST_LAUNCH, true)

    var tutorialLaunch: Boolean
        set(value) = editor.putBoolean(TUTORIAL_LAUNCH, value).apply()
        get() = sharedPreferences.getBoolean(TUTORIAL_LAUNCH, true)

    var userLoggedIn: Boolean
        set(value) = editor.putBoolean(USER_LOGGED_IN, value).apply()
        get() = sharedPreferences.getBoolean(USER_LOGGED_IN, false)

    var phone: String?
        set(value) = editor.putString(USER_PHONE, "0$value").apply()
        get() = sharedPreferences.getString(USER_PHONE, "")
}