package com.dsckiet.hackathonhelpapp.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.dsckiet.hackathonhelpapp.R
import com.dsckiet.hackathonhelpapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
   private lateinit var  binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_login)
    }
}