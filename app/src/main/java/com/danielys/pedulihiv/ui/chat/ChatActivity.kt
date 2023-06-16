package com.danielys.pedulihiv.ui.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.danielys.pedulihiv.R
import com.danielys.pedulihiv.data.response.DataItem
import com.danielys.pedulihiv.data.response.DataItemChat
import com.danielys.pedulihiv.databinding.ActivityChatBinding
import com.danielys.pedulihiv.ui.consultation.ConsultationsAdapter.Companion.KEYIDCONSUL
import com.danielys.pedulihiv.ui.home.ActivitiesAdapter

class ChatActivity : AppCompatActivity() {
    lateinit var binding : ActivityChatBinding
    private val chatViewModel:ChatViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chatViewModel.dataChat.observe(this){chatResponse->
            if(chatResponse.message=="success"){
                setActivitiesData(chatResponse.data as List<DataItemChat>,"https://storage.googleapis.com/capstone-bucket-2023/userdoctor.jpg")
            }
            Glide.with(this)
                .load("https://storage.googleapis.com/capstone-bucket-2023/userdoctor.jpg")
                .into(binding.ivPhotoProfile)
            binding.tvName.text = chatResponse.doctor?.name
        }

        val idConsul = intent.getStringExtra("KEYIDCONSUL")
        if (idConsul != null) {
            chatViewModel.getConsultation(idConsul)
        }else
        {
            Toast.makeText(this, "kosong", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setActivitiesData(activities: List<DataItemChat>, doctorImg: String) {
        val layoutManager = LinearLayoutManager(this)
        binding.rvChat.layoutManager = layoutManager
        val adapter = ChatAdapter(activities, this,doctorImg)
        binding.rvChat.adapter = adapter
    }
}