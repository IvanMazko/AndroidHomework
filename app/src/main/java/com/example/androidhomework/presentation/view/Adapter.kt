package com.example.androidhomework.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomework.R
import com.example.androidhomework.domain.model.Note

class Adapter(
    val list: List<Note>,
    val callback: (view : View, position : Int) -> Unit
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val header = itemView.findViewById<AppCompatTextView>(R.id.rven_header_actv)
        val message = itemView.findViewById<AppCompatTextView>(R.id.rven_message_actv)
        val date = itemView.findViewById<AppCompatTextView>(R.id.rven_date_actv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_exact_note, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            callback.invoke(holder.itemView, position)
        }
        holder.header.text = list[position].header
        holder.message.text = list[position].message
        holder.date.text = list[position].date
    }

    override fun getItemCount(): Int = list.size
}