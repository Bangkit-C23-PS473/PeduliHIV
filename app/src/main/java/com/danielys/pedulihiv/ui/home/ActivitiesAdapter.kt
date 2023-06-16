package com.danielys.pedulihiv.ui.home

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.danielys.pedulihiv.R
import com.danielys.pedulihiv.data.response.DataItem
import com.danielys.pedulihiv.databinding.ItemActivityBinding
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.Calendar

class ActivitiesAdapter(private val listActivities: List<DataItem>, private val context: Context) :
    RecyclerView.Adapter<ActivitiesAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemActivityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
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
        viewHolder.binding.tvTime.text = listActivities[position].time?.substring(0, 8 - 3)

        val calendar = Calendar.getInstance()
        val currentTime = calendar.time
        val dateFormat = SimpleDateFormat("HHmm")
        val formattedTime: String = dateFormat.format(currentTime)
        val timeSchedule = listActivities[position].time?.substring(0, 8 - 3)
        val timeScheduleFormatted = timeSchedule?.replace(":","")

        if(timeScheduleFormatted?.toInt()!! >formattedTime.toInt())
        {
            viewHolder.binding.cardView2.setCardBackgroundColor(Color.GREEN)
        }
        else{
            viewHolder.binding.cardView2.setCardBackgroundColor(Color.YELLOW)
        }

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