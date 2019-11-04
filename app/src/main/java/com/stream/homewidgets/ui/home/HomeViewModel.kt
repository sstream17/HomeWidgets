package com.stream.homewidgets.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stream.homewidgets.WidgetPreferences

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Which floor did you park on?"
    }
    val text: LiveData<String> = _text
}