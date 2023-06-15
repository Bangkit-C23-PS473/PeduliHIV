package com.danielys.pedulihiv.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.danielys.pedulihiv.R
import com.danielys.pedulihiv.data.response.RegisterResponse
import com.danielys.pedulihiv.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val registerViewModel: RegisterViewModel by viewModels()

        registerViewModel.dataRegister.observe(this) { RegisterResponse ->
            if (RegisterResponse.message == "Registration successful") {
                Toast.makeText(this, "Register berhasil silahkan login", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Gagal melakukan register", Toast.LENGTH_SHORT).show()
            }
        }

        var selectedSex: String = ""
        binding.rgSex.setOnCheckedChangeListener { group, checkedId ->
            selectedSex = if (checkedId == binding.rbMale.id) "Male" else "Female"
        }
        binding.btnRegister.setOnClickListener {
            with(binding)
            {
                if (etEmail.text!!.isEmpty() ||
                    etName.text!!.isEmpty() ||
                    etPassword.text!!.isEmpty() ||
                    etConfirmPassword.text!!.isEmpty() ||
                    etUsername.text!!.isEmpty()
                ) {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Isi semua data terlebih dahulu",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (selectedSex == "") {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Pilih jenis kelamin terlebih dahulu",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (etPassword.text.toString()!=etConfirmPassword.text.toString()){
                    Toast.makeText(this@RegisterActivity, "Password konfirmasi berbeda", Toast.LENGTH_SHORT).show()
                } else {
                    registerViewModel.register(
                        etUsername.text.toString(),
                        etName.text.toString(),
                        etEmail.text.toString(),
                        etPassword.text.toString(),
                        selectedSex
                    )
                }
            }
        }
    }
}