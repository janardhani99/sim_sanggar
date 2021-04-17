package com.example.sim_sanggar.common

import com.example.sim_sanggar.GlobalClass
import com.example.sim_sanggar.R
import com.google.android.material.textfield.TextInputLayout

object Validations {
    fun isTextValid(
            vararg types: Int,
            textInputLayout: TextInputLayout,
            minimum: Int = 0
    ): Boolean {

        for (type in types) {
            when (type) {
                Constants.Validations.VALIDATION_EMPTY -> {
                    if(textInputLayout.editText?.text!!.trim().isEmpty()) {
                        textInputLayout.error = null
                        textInputLayout.isErrorEnabled = true
                        textInputLayout.error = GlobalClass.applicationContext().getString(
                                R.string.error_cannot_be_empty,
                                textInputLayout.editText?.hint.toString()
                        )

                        return false
                    }
                }
            }
        }

        return true
    }

    fun removeError(vararg textInputLayout: TextInputLayout) {

        for (til in textInputLayout) {
            til.editText?.onTextChanged {
                til.apply {
                    error = null
                    isErrorEnabled = false
                }
            }
        }
    }
}