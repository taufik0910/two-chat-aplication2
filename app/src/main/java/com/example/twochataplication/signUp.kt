package com.example.twochataplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class signUp : AppCompatActivity() {
    //    initial untuk view SignUp
    private lateinit var edtName : EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnSignup: Button
    //    initial untuk firebase authentication
    private lateinit var mAuth : FirebaseAuth
    private lateinit var mDbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

//        hidden navbar login
        supportActionBar?.hide()

//        memanggil fungsi view
        edtName = findViewById(R.id.edt_name)
        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        btnSignup = findViewById(R.id.btn_signUp)

//        memanggil fungsi firebase
        mAuth = FirebaseAuth.getInstance()

        btnSignup.setOnClickListener {
            val name = edtName.text.toString()
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            signup(name,email, password )
        }
    }
//    membuat method signUp
    private fun signup(name:String, email: String, password: String ){
//        logic signUp
    mAuth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                addUserDataBase(name, email,mAuth.currentUser?.uid!!)
                Toast.makeText(this@signUp,"Account Success Created", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@signUp, MainActivity::class.java)
                finish()
                startActivity(intent)

            } else {
                // If sign in fails, display a message to the user.
                Toast.makeText(this@signUp,"some error accurate", Toast.LENGTH_SHORT).show()


            }
        }
    }

    private fun addUserDataBase(name: String,email: String,uid:String){
        mDbRef = FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid).setValue(User(name, email, uid))
    }
}