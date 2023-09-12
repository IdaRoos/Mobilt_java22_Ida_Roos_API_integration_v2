package com.example.apiintegration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class BlankFragment2 : Fragment() {

    private lateinit var jokeTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_blank2, container, false)

        jokeTextView = view.findViewById(R.id.jokeTextView)

        // Hämta data från argumenten (Bundle)
        val joke = arguments?.getString("joke")

        // Visa skämtet i TextView
        jokeTextView.text = joke

        return view
    }
}
