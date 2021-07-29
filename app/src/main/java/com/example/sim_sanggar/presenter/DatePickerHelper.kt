package com.example.sim_sanggar.presenter

import android.app.DatePickerDialog
import android.content.Context
import java.time.Month
import java.time.Year

import java.util.*
import javax.security.auth.callback.Callback


class DatePickerHelper(context: Context, isSpinnerType: Boolean = false) {
    private var dialog: DatePickerDialog
    private var callback: Callback? = null

    private var listener = DatePickerDialog.OnDateSetListener { datePicker, year, monthOfYear, dayOfMonth ->
        callback?.onDateSelected(year, monthOfYear, dayOfMonth)
    }

    init {
        val cal = Calendar.getInstance()
        dialog = DatePickerDialog(context, listener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
    }

    fun showDialog(dayOfMonth: Int, month: Int, year: Int, callback: Callback?) {
        this.callback = callback
        dialog.datePicker.init(year, month, dayOfMonth, null)
        dialog.show()
    }

    fun setMinDate(minDate: Long) {
        dialog.datePicker.minDate = minDate
    }

    fun setMaxDate(maxDate: Long) {
        dialog.datePicker.maxDate = maxDate
    }

    interface Callback {
        fun onDateSelected(dayOfMonth: Int, month: Int, year: Int)
    }
}