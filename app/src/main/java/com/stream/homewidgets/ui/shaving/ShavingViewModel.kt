package com.stream.homewidgets.ui.shaving

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShavingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is shaving Fragment"
    }
    val text: LiveData<String> = _text
}
