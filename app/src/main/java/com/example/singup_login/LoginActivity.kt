package com.example.singup_login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {
    lateinit var finding : DatabaseReference

    companion object{
        const val KEY1 = "com.example.singup_login.MainActivity.name"
        const val KEY2 = "com.example.singup_login.MainActivity.email"
        const val KEY3 = "com.example.singup_login.MainActivity.password"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val findname = findViewById<TextInputEditText>(R.id.username)
        val login = findViewById<Button>(R.id.button)
        val progressbar = findViewById<ProgressBar>(R.id.progressBar)

        login.setOnClickListener {
            val userid = findname.text.toString().trim()
            if (userid.isEmpty()) {
                findname.error = "Enter username"
                findname.requestFocus()
                return@setOnClickListener
            }

            progressbar.visibility = View.VISIBLE

            Handler(Looper.getMainLooper()).postDelayed({
                progressbar.visibility = View.GONE


                finding = FirebaseDatabase.getInstance().getReference("Users")
                finding.child(userid).get().addOnSuccessListener {
                    if (it.exists()) {
                        val mail = it.child("email").value
                        val user = it.child("name").value
                        val password = it.child("pin").value

                        val welcome = Intent(this, welcome::class.java)
                        welcome.putExtra(KEY1, user.toString())
                        welcome.putExtra(KEY2, mail.toString())
                        welcome.putExtra(KEY3, password.toString())
                        startActivity(welcome)
                    }
                    else {
                        Toast.makeText(this, "User is not exist", Toast.LENGTH_SHORT).show()
                    }

                }.addOnFailureListener {
                Toast.makeText(this,"Sing_up first", Toast.LENGTH_SHORT).show()
                }
            },2000)
        }

    }
}