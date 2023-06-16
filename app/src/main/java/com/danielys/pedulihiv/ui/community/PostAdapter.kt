package com.danielys.pedulihiv.ui.community

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danielys.pedulihiv.R
import com.danielys.pedulihiv.data.response.DataItemPost
import com.danielys.pedulihiv.databinding.ItemPostBinding

class PostAdapter(
    private val listPost: List<DataItemPost>,
    private val context: Context
) :
    RecyclerView.Adapter<PostAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ListViewHolder, position: Int) {
        Glide.with(context)
            .load("")//TODO ADD
            .into(viewHolder.binding.ivPhoto)
        viewHolder.binding.tvJudul.text = listPost[position].title
        viewHolder.binding.tvContent.text = listPost[position].content?.substring(0, 100)+"..."
        if(listPost[position].is_like == true){
            viewHolder.binding.btnLike.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ica_medicine))
        }
    }

    override fun getItemCount() = listPost.size


    class ListViewHolder(var binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)
}