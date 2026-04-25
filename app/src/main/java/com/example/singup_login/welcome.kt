package com.example.singup_login

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)

        val name = intent.getStringExtra(LoginActivity.KEY1)
        val email = intent.getStringExtra(LoginActivity.KEY2)
        val password = intent.getStringExtra(LoginActivity.KEY3)

        val welcometext = findViewById<TextView>(R.id.textView3)
        val emailt = findViewById<TextView>(R.id.textView4)
        val passwordt = findViewById<TextView>(R.id.textView5)
        welcometext.text = "Welcome : " + name
        emailt.text = "Email : " + email
        passwordt.text = "Password : " + password
    }
}