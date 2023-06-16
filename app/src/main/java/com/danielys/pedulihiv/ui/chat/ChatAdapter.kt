package com.danielys.pedulihiv.ui.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danielys.pedulihiv.data.Global
import com.danielys.pedulihiv.data.response.DataItemChat
import com.danielys.pedulihiv.databinding.ItemChatBinding

class ChatAdapter(
    private val listChat: List<DataItemChat>,
    private val context: Context,
    private val doctorImage: String
) :
    RecyclerView.Adapter<ChatAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ListViewHolder, position: Int) {

        if (listChat[position].photo != null) {
            viewHolder.binding.ivChat.visibility = View.VISIBLE
            Glide.with(context)
                .load(listChat[position].photo)
                .into(viewHolder.binding.ivChat)
        } else {
            viewHolder.binding.ivChat.visibility = View.GONE
        }
        if (listChat[position].sender_username != Global.user.username) {
            Glide.with(context)
                .load(doctorImage)
                .into(viewHolder.binding.profileImage)
            viewHolder.binding.profileImage
        } else {
            Glide.with(context)
                .load(Global.user.photo)
                .into(viewHolder.binding.profileImage)

        }
        viewHolder.binding.tvName.text = listChat[position].sender_username
        viewHolder.binding.tvChat.text = listChat[position].text
        viewHolder.binding.tvDate.text = Global.dateFormatter(listChat[position].time.toString())
    }

    override fun getItemCount() = listChat.size


    class ListViewHolder(var binding: ItemChatBinding) : RecyclerView.ViewHolder(binding.root)
}