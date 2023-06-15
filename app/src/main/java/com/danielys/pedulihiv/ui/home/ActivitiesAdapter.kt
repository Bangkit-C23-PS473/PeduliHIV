package com.danielys.pedulihiv.ui.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danielys.pedulihiv.R
import com.danielys.pedulihiv.data.response.DataItem
import com.danielys.pedulihiv.databinding.ItemActivityBinding

class ActivitiesAdapter(private val listActivities: List<DataItem>, private val context: Context) :
    RecyclerView.Adapter<ActivitiesAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemActivityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ListViewHolder, position: Int) {
        viewHolder.binding.imgActivity.setImageDrawable(R.drawable.ica_clock.toDrawable())
        viewHolder.binding.tvNameActivity.text = listActivities[position].name
        viewHolder.binding.tvTime.text = listActivities[position].time

//        viewHolder.itemView.setOnClickListener {
//
//            val optionsCompat: ActivityOptionsCompat =
//                ActivityOptionsCompat.makeSceneTransitionAnimation(
//                    it.context as Activity,
//                    androidx.core.util.Pair(viewHolder.binding.textViewNama, "name"),
//                    androidx.core.util.Pair(viewHolder.binding.imageViewStory, "photo"),
//                )
//
//            val intent = Intent(context, DetailActivity::class.java)
//            intent.putExtra("id_story", listActivities[position].id)
//            ContextCompat.startActivity(context, intent, optionsCompat.toBundle())
//        }
    }

    override fun getItemCount() = listActivities.size


    class ListViewHolder(var binding: ItemActivityBinding) : RecyclerView.ViewHolder(binding.root)

}