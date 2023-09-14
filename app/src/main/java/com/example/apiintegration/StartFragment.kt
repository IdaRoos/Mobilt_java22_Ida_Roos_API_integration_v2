package com.example.apiintegration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException


class StartFragment : Fragment() {

    private lateinit var requestQueue: RequestQueue


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestQueue = Volley.newRequestQueue(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_start, container, false)

        val getCurrentWeatherButton = view.findViewById<Button>(R.id.buttonGetCurrentWeather)
        val getJokeButton = view.findViewById<Button>(R.id.buttonGetJoke)

        getCurrentWeatherButton.setOnClickListener {
            makeWeatherApiCall("Malmo")
        }

        getJokeButton.setOnClickListener {
            fetchJoke()
        }

        return view
    }

    private fun makeWeatherApiCall(city: String) {
        val apiKey = "a7e238d25af91b54f6450f60a5849740"
        val url = "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$apiKey"

        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    val temperature =
                        (response.getJSONObject("main").getDouble("temp") - 273.15).toInt()
                    val description =
                        response.getJSONArray("weather").getJSONObject(0).getString("description")

                    val bundle = Bundle()
                    bundle.putString("city", city)
                    bundle.putString("temperature", "$temperature °C")
                    bundle.putString("description", description)
                    findNavController().navigate(R.id.action_startFragment_to_blankFragment, bundle)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error ->
                error.printStackTrace()
            })

        requestQueue.add(jsonRequest)
    }

    private fun fetchJoke() {
        val jokeUrl = "https://italian-jokes.vercel.app/api/jokes"

        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, jokeUrl, null,
            { response ->
                try {
                    val joke = response.getString("joke")

                    val bundle = Bundle()
                    bundle.putString("joke", joke)

                    findNavController().navigate(
                        R.id.action_startFragment_to_blankFragment2,
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



