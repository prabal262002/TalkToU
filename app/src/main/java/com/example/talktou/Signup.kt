package com.example.talktou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Signup : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var btnSignup: Button
    private lateinit var mDbRef: DatabaseReference
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        mAuth = FirebaseAuth.getInstance()

        name = findViewById(R.id.name)
        email = findViewById(R.id.Email)
        password = findViewById(R.id.password)
        btnSignup = findViewById(R.id.sign_in)

        btnSignup.setOnClickListener{
            val name = name.text.toString()
            val email = email.text.toString()
            val pass = password.text.toString()

            signup(name,email,pass)
        }
    }

    private fun signup(name:String, email:String, pass:String){
        mAuth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addUserToDatabase(name,email, mAuth.currentUser?.uid!!)
                    val intent = Intent(this@Signup,MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    val exception = task.exception
                    Toast.makeText(this@Signup, "Error: ${exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addUserToDatabase(name: String, email: String, uid: String) {
        mDbRef = FirebaseDatabase.getInstance().reference
        mDbRef.child("user").child(uid).setValue(User(name,email,uid))
        Toast.makeText(this@Signup, "Hello uncle", Toast.LENGTH_SHORT).show()
    }
}