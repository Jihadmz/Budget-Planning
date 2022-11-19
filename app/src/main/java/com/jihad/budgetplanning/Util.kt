package com.jihad.budgetplanning

import android.util.Log
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.SoftwareKeyboardController

object Util {

    fun logD(msg: String){
        Log.d("Jihad", "logD: ${msg}")
    }

    @OptIn(ExperimentalComposeUiApi::class)
    fun requestFocus(focusRequester: FocusRequester, keyboardController: SoftwareKeyboardController){
        focusRequester.requestFocus()
        keyboardController.show()

    }
}