package com.danielys.pedulihiv.ui.listdoctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.danielys.pedulihiv.data.response.DataItemDoctor
import com.danielys.pedulihiv.databinding.ActivityListDoctorBinding
import com.danielys.pedulihiv.ui.chat.ChatActivity

class ListDoctorActivity : AppCompatActivity() {
    private lateinit var binding : ActivityListDoctorBinding
    private val listDoctorViewModel: ListDoctorViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listDoctorViewModel.dataDoctor.observe(this){doctorResponse->
            if(doctorResponse.message=="Doctors retrieved successfully"){
                setDoctorData(doctorResponse.data as List<DataItemDoctor>)
            }
        }
        listDoctorViewModel.getDoctor()

        listDoctorViewModel.responseConsul.observe(this){makeConsulResponse->
            if(makeConsulResponse.message=="Consultation created successfully"){
                val intent = Intent(this,ChatActivity::class.java)
                intent.putExtra("KEYIDCONSUL",makeConsulResponse.consultationId.toString())
                startActivity(intent)
            }
        }
    }

    private fun setDoctorData(activities: List<DataItemDoctor>) {
        val layoutManager = LinearLayoutManager(this)
        binding.rvDoctor.layoutManager = layoutManager
        val adapter = ListDoctorAdapter(activities, this, listDoctorViewModel)
        binding.rvDoctor.adapter = adapter
    }
}