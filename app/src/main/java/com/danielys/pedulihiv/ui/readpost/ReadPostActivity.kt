package com.danielys.pedulihiv.ui.readpost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.danielys.pedulihiv.databinding.ActivityReadPostBinding

class ReadPostActivity : AppCompatActivity() {
    lateinit var binding : ActivityReadPostBinding
    private var idPost = "1"
    private val readViewModel: ReadPostViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idPost = intent.getStringExtra("KEYIDPOST").toString()
        readViewModel.dataPost.observe(this){readPostResponse->
            binding.tvDate.text = readPostResponse.data?.date
            with(binding){
                tvCreator.text = readPostResponse.data?.name
                tvContent.text = readPostResponse.data?.content
                tvTitle.text = readPostResponse.data?.title
                Glide.with(applicationContext)
                    .load(readPostResponse.data?.photo_header)
                    .into(binding.ivHeader)
            }
        }
        readViewModel.readPost(idPost)
    }
}