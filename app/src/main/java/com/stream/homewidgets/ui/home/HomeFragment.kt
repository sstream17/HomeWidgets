package com.stream.homewidgets.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.stream.homewidgets.R
import com.stream.homewidgets.WidgetPreferences
import com.stream.homewidgets.intents.BroadcastManager

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    lateinit var preferences: WidgetPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        context?.let {
            preferences = WidgetPreferences(it)
        }
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(this, Observer {
            textView.text = it
        })
        val spinner: Spinner = root.findViewById(R.id.floor_spinner)
        val spinnerAdapter = spinner.adapter as ArrayAdapter<String>
        val currentFloor = preferences.getParkingFloor()
        spinner.setSelection(spinnerAdapter.getPosition(currentFloor))

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                preferences.setParkingFloor(spinner.getItemAtPosition(position) as String)
                val broadcastManager = BroadcastManager()
                broadcastManager.broadcastParkingIntent(context, preferences.getParkingFloor())
            }
        }
        return root
    }
}