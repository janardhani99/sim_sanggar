package com.example.sim_sanggar.view.activity.edit_profil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sim_sanggar.GlobalClass
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Preferences
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.view.activity.auth.LoginActivity
import kotlinx.android.synthetic.main.fragment_profil.*

class EditProfilActivity : AppCompatActivity() {

    var preferences = Preferences(GlobalClass.context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profil)

        initListener()
    }

    private fun initListener() {

        btn_logout?.clickWithDebounce {
            preferences.userLoggedOut()
            finishAffinity()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}