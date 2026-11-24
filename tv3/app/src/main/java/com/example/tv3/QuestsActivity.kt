package com.example.tv3

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class QuestsActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quests)

        val list = ArrayList<Quest>()
        for (i in 1..6) {
            list.add(Quest("Welcome to Kazan", "Now you must go to Kazan Expo and find next key. And there are many more interesting information."))
        }

        val recycler: RecyclerView = findViewById(R.id.recycler)
        recycler.layoutManager = GridLayoutManager(this, 3)
        recycler.adapter = QuestAdapter(list)
    }
}