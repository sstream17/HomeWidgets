package com.stream.homewidgets.ui.shaving

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShavingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "How many times have you shaved?"
    }
    val text: LiveData<String> = _text
}
