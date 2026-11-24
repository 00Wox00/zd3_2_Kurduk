package com.example.tv3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

class ChannelsActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_channels)
    }

    fun OpenChannel(view: View) {
        val intent = Intent(this, ChannelActivity::class.java)
        intent.putExtra("genre", view.tag.toString())
        startActivity(intent)
    }

    fun OpenQuests(view: View) {
        val intent = Intent(this, QuestsActivity::class.java)
        startActivity(intent)
    }
}
