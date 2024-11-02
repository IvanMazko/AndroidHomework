package com.example.androidhomework.ht19

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomework.R

class Adapter(val list: List<Note>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val header = itemView.findViewById<AppCompatTextView>(R.id.rven_header_actv)
        val message = itemView.findViewById<AppCompatTextView>(R.id.rven_message_actv)
        val time = itemView.findViewById<AppCompatTextView>(R.id.rven_time_actv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_exact_note, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.header.text = list[position].header
        holder.message.text = list[position].message
        holder.time.text = list[position].time
    }

    override fun getItemCount(): Int = list.size
}