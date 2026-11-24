package com.example.tv3

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import org.json.JSONObject

class MovieDetailsActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val poster: ImageView = findViewById(R.id.detailPoster)
        val info: TextView = findViewById(R.id.detailInfo)
        val imdbID = intent.getStringExtra("imdbID") ?: return
        val url = "https://www.omdbapi.com/?apikey=fe6af98e&i=$imdbID&plot=full"

        val request = StringRequest(Request.Method.GET, url,
            { response ->
                val movie = JSONObject(response)
                if (movie.optString("Response") == "True") {
                    Glide.with(this).load(movie.optString("Poster")).into(poster)
                    info.text = movie.optString("Title") + " (" + movie.optString("Year") + ")\n\n" +
                        "Жанр: " + movie.optString("Genre") + "\n" +
                        "Рейтинг: " + movie.optString("imdbRating") + "\n\n" +
                        movie.optString("Plot")
                } else {
                    Toast.makeText(this, "Информация не найдена", Toast.LENGTH_SHORT).show()
                }
            },
            {
                Toast.makeText(this, "Ошибка сети", Toast.LENGTH_SHORT).show()
            })
        Volley.newRequestQueue(this).add(request)
    }
}
