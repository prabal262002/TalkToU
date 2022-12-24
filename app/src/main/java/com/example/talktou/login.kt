package com.example.talktou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class login : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var btnlogin: Button
    private lateinit var btnSignup: Button

    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()
        email = findViewById(R.id.Email)
        password = findViewById(R.id.password)
        btnlogin = findViewById(R.id.login)
        btnSignup = findViewById(R.id.signin)

        btnSignup.setOnClickListener {
            val intent  = Intent(this,Signup::class.java)
            startActivity(intent)
        }

        btnlogin.setOnClickListener{
            val email = email.text.toString()
            val pass = password.text.toString()

            login(email,pass)

        }
    }


    private fun login (email:String,pass:String){
        mAuth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this,"User Doesn't exist!!",Toast.LENGTH_SHORT).show()
                }
            }
    }
}