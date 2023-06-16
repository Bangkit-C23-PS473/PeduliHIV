package com.danielys.pedulihiv.ui.listdoctor

import android.content.Context
import android.content.Intent
import android.service.autofill.OnClickAction
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danielys.pedulihiv.data.response.DataItemDoctor
import com.danielys.pedulihiv.databinding.ItemDoctorBinding
import com.danielys.pedulihiv.ui.chat.ChatActivity

class ListDoctorAdapter(
    private val listDoctor: List<DataItemDoctor>,
    private val context: Context, private val doctorViewModel: ListDoctorViewModel
) :
    RecyclerView.Adapter<ListDoctorAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemDoctorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ListViewHolder, position: Int) {
        Glide.with(context)
            .load(listDoctor[position].profile_photo)
            .into(viewHolder.binding.profileImage)
        viewHolder.binding.tvNameDoctor.text = listDoctor[position].name
        viewHolder.binding.tvPrice.text = "Rp."+listDoctor[position].price+"."
        viewHolder.binding.tvSpesialis.text = listDoctor[position].specialist

        viewHolder.binding.btnKonsul.setOnClickListener {
            listDoctor[position].username?.let { it1 -> doctorViewModel.makeConsul(it1) }
        }
    }

    override fun getItemCount() = listDoctor.size


    class ListViewHolder(var binding: ItemDoctorBinding) : RecyclerView.ViewHolder(binding.root)
}