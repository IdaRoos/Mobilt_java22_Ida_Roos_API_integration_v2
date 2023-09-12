package com.example.apiintegration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController


class DetailedWeatherFragment : Fragment() {

    private lateinit var humidityTextView: TextView
    private lateinit var windTextView: TextView


    // ...

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detailed_weather, container, false)

        // Hitta dina TextViews i layouten
        humidityTextView = view.findViewById(R.id.humidityTextView)
        windTextView = view.findViewById(R.id.windTextView)
        val backButton = view.findViewById<Button>(R.id.backToMainButton)


        // Hämta data från argumenten (Bundle)
        val humidity = arguments?.getInt("humidity")
        val windSpeed = arguments?.getDouble("windSpeed")

        // Visa väderinformation
        humidityTextView.text = "Humidity: $humidity"
        windTextView.text = "Wind Speed: $windSpeed m/s"



        backButton.setOnClickListener {
            // Hämta NavController
            val navController = findNavController()
            // Använd popBackStack() med rätt flaggor för att rensa backstacken och gå till första sidan
            navController.popBackStack(R.id.startFragment, true)
        }

        return view
    }


}