package com.example.marosu.secretchat.util

import android.text.Editable
import android.text.TextWatcher

/**
 * Created by Marius-Andrei Rosu on 9/11/2017.
 */
abstract class SimpleTextWatcher : TextWatcher {
    override fun afterTextChanged(p0: Editable?) {
        //Not implemented
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        //Not implemented
    }
}
