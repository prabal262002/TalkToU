package com.example.talktou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Signup : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var btnSignup: Button

    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        mAuth = FirebaseAuth.getInstance()

        name = findViewById(R.id.name)
        email = findViewById(R.id.Email)
        password = findViewById(R.id.password)
        btnSignup = findViewById(R.id.signin)

        btnSignup.setOnClickListener{
            val name = name.text.toString()
            val email = email.text.toString()
            val pass = password.text.toString()

            signup(email,pass)
        }
    }

    private fun signup(email:String,pass:String){
        mAuth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val intent = Intent(this@Signup,MainActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this@Signup,"Some Error Occurred!!",Toast.LENGTH_SHORT).show()
                }
            }
    }
}