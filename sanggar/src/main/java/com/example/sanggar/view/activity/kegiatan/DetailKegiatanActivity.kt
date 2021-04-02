package com.example.sanggar.view.activity.kegiatan

import android.os.Bundle
import android.widget.Toast
import com.example.sanggar.R
import com.example.sanggar.common.Constants
import com.example.sanggar.common.Validations
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.view.activity.common.BaseActivity
import kotlinx.android.synthetic.main.activity_detail_kegiatan.*
import kotlinx.android.synthetic.main.fragment_toolbar.*

class DetailKegiatanActivity : BaseActivity() {
    var action = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_kegiatan)
        action = intent.getIntExtra(Constants.Intent.ACTION, 0)
        setView(action)

        //set Toolbar
        setToolbar()
        toolbar_title?.text = getString(R.string.kegiatan)

        //init listener
        initListener()

        btn_simpan_kegiatan?.clickWithDebounce {
            addOrEditKegiatan()
        }
    }

    private fun initListener() {
        Validations.removeError(til_judul_kegiatan, til_deskripsi_kegiatan)
    }

    private fun setView(action: Int?) {
        when (action) {
            Constants.Action.CREATE -> createMode()
            else -> editMode()
        }
    }

    private fun editMode() {}

    private fun createMode() {}

    private fun addOrEditKegiatan() {
        Toast.makeText(this, "${isAllValid()}", Toast.LENGTH_SHORT).show()
        if (isAllValid()) {
            when (action) {
                Constants.Action.CREATE -> {

                }
                else -> {

                }
            }
        }
    }

    private fun isAllValid(): Boolean {
        val valid = mutableListOf<Boolean>()
        valid.apply {
            add(Validations.isTextValid(Constants.Validations.VALIDATION_EMPTY, textInputLayout = til_judul_kegiatan))
            add(Validations.isTextValid(Constants.Validations.VALIDATION_EMPTY, textInputLayout = til_deskripsi_kegiatan))
        }
        return !valid.contains(false)
    }
}