package com.jihad.budgetplanning

import android.util.Log
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.SoftwareKeyboardController
import java.text.SimpleDateFormat
import java.util.*

object Util {

    fun logD(msg: String){
        Log.d("Jihad", "logD: ${msg}")
    }

    @OptIn(ExperimentalComposeUiApi::class)
    fun requestFocus(focusRequester: FocusRequester, keyboardController: SoftwareKeyboardController){
        focusRequester.requestFocus()
        keyboardController.show()
    }

    fun getDate(): String{
        var date = ""
        val calender = Calendar.getInstance()
        val dateFormatter = SimpleDateFormat("MMMM, yyyy", Locale.getDefault())
        date = dateFormatter.format(calender.time)
        return date
    }
}