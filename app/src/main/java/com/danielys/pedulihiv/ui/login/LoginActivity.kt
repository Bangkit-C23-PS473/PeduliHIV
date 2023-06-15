package com.danielys.pedulihiv.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.danielys.pedulihiv.data.UserPreferences
import com.danielys.pedulihiv.databinding.ActivityLoginBinding
import com.danielys.pedulihiv.ui.PrefViewModelProvider
import com.danielys.pedulihiv.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = UserPreferences.getInstance(dataStore)
        loginViewModel = ViewModelProvider(this, PrefViewModelProvider(pref)).get(
            LoginViewModel::class.java
        )

        loginViewModel.dataLogin.observe(this) { LoginResponse ->

            if (LoginResponse.message == "Login successful") {
                var loginData = LoginResponse.user

                loginViewModel.setGlobal(
                    loginData!!.username!!,
                    loginData.name!!,
                    loginData.profile_photo!!
                )

                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("login", true)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        binding.btnLogin.setOnClickListener {
            with(binding) {
                loginViewModel.login(etEmail.text.toString(), etPassword.text.toString())
            }
        }
    }
}