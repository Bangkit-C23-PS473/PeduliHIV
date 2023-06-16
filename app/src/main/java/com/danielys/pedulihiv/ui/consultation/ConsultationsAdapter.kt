package com.danielys.pedulihiv.ui.consultation

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danielys.pedulihiv.R
import com.danielys.pedulihiv.data.response.ConsultationsItem
import com.danielys.pedulihiv.data.response.DataItem
import com.danielys.pedulihiv.databinding.ItemActivityBinding
import com.danielys.pedulihiv.databinding.ItemListConsulBinding
import java.text.SimpleDateFormat
import java.util.Calendar

class ConsultationsAdapter(private val listActivities: List<ConsultationsItem>, private val context: Context) :
    RecyclerView.Adapter<ConsultationsAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemListConsulBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ListViewHolder, position: Int) {
        Glide.with(context)
            .load(listActivities[position].profile_photo)
            .into(viewHolder.binding.profileImage)
        viewHolder.binding.tvNameDoctor.text = listActivities[position].name
        viewHolder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount() = listActivities.size


    class ListViewHolder(var binding: ItemListConsulBinding) : RecyclerView.ViewHolder(binding.root)

}