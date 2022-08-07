package com.example.twochataplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import android.graphics.drawable.Drawable
import android.text.method.PasswordTransformationMethod
import android.widget.ImageView


class logIn : AppCompatActivity() {
//    initial untuk view login
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignup: Button
    private lateinit var vsb_password : ImageView
//    initial untuk firebase authentication
    private lateinit var mAuth : FirebaseAuth

    private var mIsShowPass = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

//        method show hidden password


//        hidden navbar login
        supportActionBar?.hide()

//        memanggil fungsi firebase
        mAuth = FirebaseAuth.getInstance()
//
//        object untuk view
        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        btnLogin = findViewById(R.id.btn_logIn)
        btnSignup = findViewById(R.id.btn_signUp)
        vsb_password = findViewById(R.id.vsb_password)

//    fungsi untuk direct ke form sign up
        btnSignup.setOnClickListener{
            val intent = Intent(this,signUp::class.java)
            startActivity(intent)
        }

//        memanggil fungsi login
        btnLogin.setOnClickListener{
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

        Login(email,password);
        }

        vsb_password.setOnClickListener {
            mIsShowPass = !mIsShowPass
            showPassword(mIsShowPass)
        }
        showPassword(mIsShowPass)
    }

    private fun showPassword(isShow:Boolean){
        if (isShow){
        edtPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            vsb_password.setImageResource(R.drawable.ic_baseline_visibility_24)
        }else{
            edtPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            vsb_password.setImageResource(R.drawable.ic_baseline_visibility_off_24)
        }
        edtPassword.setSelection(edtPassword.text.toString().length)

    }

    //    membuat method login
    private fun Login(email: String, password: String){
//        logic login process
    mAuth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Toast.makeText(this@logIn," Success Login", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@logIn, MainActivity::class.java)
                finish()
                startActivity(intent)
            } else {
                Toast.makeText(this@logIn,"some error accurate", Toast.LENGTH_SHORT).show()

            }
        }



}
}