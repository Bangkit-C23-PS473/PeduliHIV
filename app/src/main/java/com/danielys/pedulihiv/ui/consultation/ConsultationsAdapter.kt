package com.danielys.pedulihiv.ui.consultation

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danielys.pedulihiv.data.response.ConsultationsItem
import com.danielys.pedulihiv.databinding.ItemListConsulBinding
import com.danielys.pedulihiv.ui.chat.ChatActivity


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
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("KEYIDCONSUL",listActivities[position].consultations_id.toString())
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = listActivities.size


    class ListViewHolder(var binding: ItemListConsulBinding) : RecyclerView.ViewHolder(binding.root)
    companion object{
        const val KEYIDCONSUL = "id_consul"
    }
}
