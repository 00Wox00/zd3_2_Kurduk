package com.example.tv3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class SignInActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
    }

    fun Login(view: View) {
        val email: EditText = findViewById(R.id.email)
        val password: EditText = findViewById(R.id.password)

        if (email.text.toString() == "" || password.text.toString() == "") {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(this, ChannelsActivity::class.java)
            startActivity(intent)
        }
    }
}
