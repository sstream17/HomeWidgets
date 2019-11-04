package com.stream.homewidgets.ui.shaving

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.stream.homewidgets.R

class ShavingFragment : Fragment() {

    private lateinit var shavingViewModel: ShavingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        shavingViewModel =
            ViewModelProviders.of(this).get(ShavingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_shaving, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        shavingViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}