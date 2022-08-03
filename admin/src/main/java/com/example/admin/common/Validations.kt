package com.example.admin.common

import com.example.admin.GlobalClass
import com.example.admin.R
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
                    if (textInputLayout.editText?.text!!.trim().isEmpty()) {
                        textInputLayout.error = null
                        textInputLayout.isErrorEnabled = true
                        textInputLayout.error = GlobalClass.applicationContext().getString(
                                R.string.error_cannot_be_empty,
                                textInputLayout.editText?.hint.toString()
                        )
                        return false
                    }
                }

//                Constants.VALIDATION_PHONE -> {
//                    val pattern = Pattern.compile(Constant.REGEX_PHONE)
//                    if (!pattern.matcher(editText.text.toString().trim()).find()) {
//                        if (textInputLayout.error != null) {
//                            textInputLayout.error = null
//                        }
//                        textInputLayout.error = GlobalClass.applicationContext().getString(R.string.kolom_tidak_valid, editText.hint.toString())
//                        return false
//                    }
//                }

                Constants.Validations.VALIDATION_MINIMUM -> {
                    if (textInputLayout.editText?.text.toString().trim().count() < minimum) {
                        if (textInputLayout.error != null) {
                            textInputLayout.error = null
                        }
                        textInputLayout.isErrorEnabled = true
                        textInputLayout.error = GlobalClass.applicationContext()
                                .getString(R.string.error_input_must_more_than, minimum)

                        return false
                    }
                }

                Constants.Validations.VALIDATION_VALUE_ZERO -> {
                    if (textInputLayout.editText?.text.toString() == "0") {
                        if (textInputLayout.error != null) {
                            textInputLayout.error = null
                        }
                        textInputLayout.isErrorEnabled = true
                        textInputLayout.error = GlobalClass.applicationContext().getString(
                                R.string.error_cannot_be_zero,
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