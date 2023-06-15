package com.danielys.pedulihiv.ui.home

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danielys.pedulihiv.R
import com.danielys.pedulihiv.data.response.DataItem
import com.danielys.pedulihiv.databinding.ItemActivityBinding
import com.danielys.pedulihiv.ui.addpost.AddPostActivity

class ActivitiesAdapter(private val listActivities: List<DataItem>, private val context: Context) :
    RecyclerView.Adapter<ActivitiesAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemActivityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ListViewHolder, position: Int) {
        var vector:Drawable = ContextCompat.getDrawable(context, R.drawable.ica_clock)!!
        when(listActivities[position].logo)
        {
            "clock"->vector = ContextCompat.getDrawable(context, R.drawable.ica_clock)!!
            "sport"->vector = ContextCompat.getDrawable(context, R.drawable.ica_sport)!!
            "eat"->vector = ContextCompat.getDrawable(context, R.drawable.ica_eat)!!
            "medicine"->vector = ContextCompat.getDrawable(context, R.drawable.ica_medicine)!!
        }

        viewHolder.binding.imgActivity.setImageDrawable(vector)
        viewHolder.binding.tvNameActivity.text = listActivities[position].name
        viewHolder.binding.tvTime.text = listActivities[position].time

        viewHolder.itemView.setOnClickListener {
            val customDialog = context?.let { it1 -> Dialog(it1) }
            customDialog?.setContentView(R.layout.dialog_activity)

            customDialog?.show()

            val nameAct = customDialog?.findViewById<TextView>(R.id.tv_name_dialogact)
            val imgAct = customDialog?.findViewById<ImageView>(R.id.img_logo_dialogact)
            val desc = customDialog?.findViewById<TextView>(R.id.tv_desc_dialogact)
            val button = customDialog?.findViewById<Button>(R.id.btn_ok_dialogact)

            if (listActivities[position].description.toString()!="null"){
                desc?.text = listActivities[position].description.toString()
            }
            nameAct?.text = listActivities[position].name
            imgAct?.setImageDrawable(vector)

            button?.setOnClickListener {
                customDialog.dismiss()
            }
        }
    }

    override fun getItemCount() = listActivities.size


    class ListViewHolder(var binding: ItemActivityBinding) : RecyclerView.ViewHolder(binding.root)

}