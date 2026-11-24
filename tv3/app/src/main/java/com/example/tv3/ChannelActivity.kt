package com.example.tv3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class ChannelActivity : Activity() {

    val list = ArrayList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_channel)

        val genre = intent.getStringExtra("genre")
        val name: TextView = findViewById(R.id.channelName)
        name.text = genre

        val recycler: RecyclerView = findViewById(R.id.recycler)
        recycler.layoutManager = GridLayoutManager(this, 4)
        val adapter = MovieAdapter(list) { movie ->
            val intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra("imdbID", movie.imdbID)
            startActivity(intent)
        }
        recycler.adapter = adapter

        val url = "https://www.omdbapi.com/?apikey=fe6af98e&type=movie&s=" +
            java.net.URLEncoder.encode(genre ?: "", "UTF-8")

        val request = StringRequest(Request.Method.GET, url,
            object : Response.Listener<String> {
                override fun onResponse(response: String) {
                    val json = JSONObject(response)
                    if (json.getString("Response") == "True") {
                        val array = json.getJSONArray("Search")
                        for (i in 0 until array.length()) {
                            val item = array.getJSONObject(i)
                            val title = item.getString("Title")
                            val year = item.getString("Year")
                            val poster = item.getString("Poster")
                            val imdbID = item.getString("imdbID")
                            list.add(Movie(title, year, poster, imdbID))
                        }
                        adapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(this@ChannelActivity, "Фильмы не найдены", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError) {
                    Toast.makeText(this@ChannelActivity, "Ошибка сети", Toast.LENGTH_SHORT).show()
                }
            })

        Volley.newRequestQueue(this).add(request)
    }
}
