package com.example.admin.view.activity.common

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.admin.R
import kotlinx.android.synthetic.main.fragment_toolbar.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.yesButton

open class BaseActivity : AppCompatActivity() {
    fun setToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24)
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            setDisplayShowTitleEnabled(false)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    fun showErrorAlert(mTitle: String, message: String){
        alert(message) {
            title = mTitle
            yesButton{
                it.dismiss()
            }
        }.show()
    }

    fun showCustomDialog(mTitle: String, message: String) {
        alert(message) {
            title = mTitle
            yesButton {
                it.dismiss()
            }
        } .show()
    }


    fun showCustomDialogNext(mTitle: String, message: String, intent: Intent) {
        alert(message) {
            title = mTitle
            yesButton {

                it.dismiss()
                startActivity(intent)

            }

        } .show()


    }

    fun showCustomDialogBack(mTitle: String, message: String) {
        alert(message) {
            title = mTitle
            yesButton {
                onBackPressed()
                it.dismiss()
            }
        } .show()
    }

    fun showConfirmationDialog(mTitle: String, message: String, listener: ButtonDialogListener) {
        alert(message) {
            title = mTitle
            yesButton {
                listener.onOkButton(it)
            }
            noButton {
                it.dismiss()
            }
        }.show()
    }

}

interface ButtonDialogListener {
    fun onOkButton(dialog: DialogInterface)
}