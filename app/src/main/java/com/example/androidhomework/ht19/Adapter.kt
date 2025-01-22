package com.example.androidhomework.ht19

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomework.R

class Adapter(
    val list: List<Custom>,
    val callback: (view : View, position : Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val header = itemView.findViewById<AppCompatTextView>(R.id.rven_header_actv)
        val message = itemView.findViewById<AppCompatTextView>(R.id.rven_message_actv)
        val date = itemView.findViewById<AppCompatTextView>(R.id.rven_date_actv)
    }
    class IconViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val icon = itemView.findViewById<AppCompatImageView>(R.id.rvin_icon_aciv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            AdapterType.NOTE_TYPE.ordinal ->
                NoteViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.recycler_view_exact_note, parent, false)
            )

            AdapterType.ICON_TYPE.ordinal ->
                IconViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.recycler_view_icon_note, parent, false)
                )
            else -> throw IllegalArgumentException("No such type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(val item = list[position]){
            is Custom.Note -> (holder as NoteViewHolder).apply {
                header.text = item.header
                message.text = item.message
                date.text = item.date
            }
            is Custom.Icon -> (holder as IconViewHolder).apply {
                icon.setImageResource(R.drawable.baseline_perm_identity_24)
            }
        }
        holder.itemView.setOnClickListener{
            callback.invoke(holder.itemView, position)
        }

    }

    override fun getItemViewType(position: Int): Int = when(list[position]) {
        is Custom.Note -> AdapterType.NOTE_TYPE.ordinal
        is Custom.Icon -> AdapterType.ICON_TYPE.ordinal
    }

    override fun getItemCount(): Int = list.size

    private enum class AdapterType{
        NOTE_TYPE, ICON_TYPE
    }
}