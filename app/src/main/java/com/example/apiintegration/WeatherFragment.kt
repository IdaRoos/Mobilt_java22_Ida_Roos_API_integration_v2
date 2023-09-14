package com.example.apiintegration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import org.json.JSONException
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley


class WeatherFragment : Fragment() {

    private lateinit var currentWeatherTextView: TextView
    private lateinit var cityTextView: TextView
    private lateinit var temperatureTextView: TextView
    private lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestQueue = Volley.newRequestQueue(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)

        cityTextView = view.findViewById(R.id.cityTextView)
        temperatureTextView = view.findViewById(R.id.temperatureTextView)
        currentWeatherTextView = view.findViewById(R.id.currentWeatherTextView)

        // Hämta data från argumenten (Bundle)
        val city = arguments?.getString("city")
        val temperature = arguments?.getString("temperature")
        val description = arguments?.getString("description")


        cityTextView.text = "City: $city"
        temperatureTextView.text = "Temperature: $temperature"
        currentWeatherTextView.text = "Weather: $description"


        val getDetailsButton = view.findViewById<Button>(R.id.getDetailsButton)

        getDetailsButton.setOnClickListener {
            getDetailedWeather("Malmo")

        }

        return view
    }


    private fun getDetailedWeather(city: String) {
        val apiKey = "a7e238d25af91b54f6450f60a5849740"
        val url = "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$apiKey"

        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    val humidity = response.getJSONObject("main").getInt("humidity")
                    val windSpeed = response.getJSONObject("wind").getDouble("speed")

                    val bundle = Bundle()
                    bundle.putInt("humidity", humidity)
                    bundle.putDouble("windSpeed", windSpeed)

                    findNavController().navigate(
                        R.id.action_fragment1layout_to_detailedWeatherFragment,
                        bundle
                    )
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error ->
                error.printStackTrace()
            })

        requestQueue.add(jsonRequest)
    }


}
