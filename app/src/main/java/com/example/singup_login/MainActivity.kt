package com.example.singup_login

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {
    lateinit var collect : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val singButton = findViewById<Button>(R.id.button)
        val username = findViewById<TextInputEditText>(R.id.Etusername)
        val mail = findViewById<TextInputEditText>(R.id.Etmail)
        val password = findViewById<TextInputEditText>(R.id.Etpassword)
        val login = findViewById<TextView>(R.id.textView)

        singButton.setOnClickListener {
            val name = username.text.toString().trim()
            val gmail = mail.text.toString().trim()
            val pin = password.text.toString().trim()

            if (name.isEmpty()) {
                username.error = "Enter username"
                username.requestFocus()
                return@setOnClickListener
            }
            if (gmail.isEmpty()) {
                mail.error = "Enter email"
                mail.requestFocus()
                return@setOnClickListener
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(gmail).matches()) {
                mail.error = "Invalid email"
                mail.requestFocus()
                return@setOnClickListener
            }
            if (pin.isEmpty()) {
                password.error = "Enter password"
                password.requestFocus()
                return@setOnClickListener
            }
            if (pin.length < 6) {
                password.error = "Minimum 6 characters"
                password.requestFocus()
                return@setOnClickListener
            }


            val user = User(name, gmail, pin)
            collect = FirebaseDatabase.getInstance().getReference("Users")
            collect.child(name).setValue(user).addOnSuccessListener {
                username.text?.clear()
                mail.text?.clear()
                password.text?.clear()
                intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                Toast.makeText(this,"User Registered",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
            }
        }

        login.setOnClickListener {
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}