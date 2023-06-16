package com.danielys.pedulihiv.ui.chat

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.danielys.pedulihiv.data.Global
import com.danielys.pedulihiv.data.Global.createCustomTempFile
import com.danielys.pedulihiv.data.Global.reduceFileImage
import com.danielys.pedulihiv.data.response.DataItemChat
import com.danielys.pedulihiv.databinding.ActivityChatBinding
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class ChatActivity : AppCompatActivity() {
    lateinit var binding : ActivityChatBinding
    private val chatViewModel:ChatViewModel by viewModels()
    private var idConsul : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chatViewModel.dataChat.observe(this){chatResponse->
            if(chatResponse.message=="success"){
                setChatData(chatResponse.data as List<DataItemChat>,chatResponse.doctor?.photo ?:"https://storage.googleapis.com/capstone-bucket-2023/userdoctor.jpg")
            }
            Glide.with(this)
                .load(chatResponse.doctor?.photo ?:"https://storage.googleapis.com/capstone-bucket-2023/userdoctor.jpg")
                .into(binding.ivPhotoProfile)
            binding.tvName.text = chatResponse.doctor?.name
        }

        idConsul = intent.getStringExtra("KEYIDCONSUL").toString()
        if (idConsul != null) {
            chatViewModel.getChat(idConsul)
        }else
        {
            Toast.makeText(this, "kosong", Toast.LENGTH_SHORT).show()
        }



        binding.btnCamera.setOnClickListener {
            if (!allPermissionsGranted()) {
                ActivityCompat.requestPermissions(
                    this,
                    REQUIRED_PERMISSIONS,
                    REQUEST_CODE_PERMISSIONS
                )
            }else{
                startTakePhoto()
            }
        }

        binding.btnSend.setOnClickListener {
            chatViewModel.sendChat(idConsul,Global.user.username,binding.etText.text.toString())
        }

        chatViewModel.responseUpload.observe(this){
            chatViewModel.getChat(idConsul)
        }
    }

    private fun setChatData(activities: List<DataItemChat>, doctorImg: String) {
        val layoutManager = LinearLayoutManager(this)
        binding.rvChat.layoutManager = layoutManager
        val adapter = ChatAdapter(activities, this,doctorImg)
        binding.rvChat.adapter = adapter
    }


    private fun startTakePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(packageManager)
        createCustomTempFile(application).also {
            val photoURI: Uri = FileProvider.getUriForFile(
                this@ChatActivity,
                "com.danielys.pedulihiv.ui.chat",
                it
            )
            currentPhotoPath = it.absolutePath
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            launcherIntentCamera.launch(intent)
        }
    }


    private var getFile: File? = null

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    "Ijin harus diberikan",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }else{
                startTakePhoto()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }


    private lateinit var currentPhotoPath: String
    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val myFile = File(currentPhotoPath)
            myFile.let { file ->
                getFile = file
            }
            uploadImage()
        }
    }

    private fun uploadImage() {
        if (getFile != null) {
            val file = reduceFileImage(getFile as File)

            val text = "".toRequestBody("text/plain".toMediaType())
            val sender = Global.user.username.toRequestBody("text/plain".toMediaType())
            val requestImageFile = file.asRequestBody("image/jpeg".toMediaType())
            val id = idConsul.toRequestBody("text/plain".toMediaType())

//            val text = ""
//            val sender = Global.user.username
//            val requestImageFile = file.asRequestBody("image/jpeg".toMediaType())
//            val id = idConsul

            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "photo",
                file.name,
                requestImageFile
            )
            chatViewModel.uploadFileImg( imageMultipart, id, sender, text)

        } else {
            Toast.makeText(
                this,
                "gagal mengirim gambar",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}