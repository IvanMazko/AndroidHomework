package com.example.androidhomework.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomework.R
import com.example.androidhomework.databinding.RecyclerViewExactNoteBinding
import com.example.androidhomework.domain.model.Note

class Adapter(
    private val list: List<Note>,
    private val callback: (view : View, position : Int) -> Unit
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(val binding : RecyclerViewExactNoteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerViewExactNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder.binding){
            rvenHeaderActv.text = list[position].header
            rvenMessageActv.text = list[position].message
            rvenDateActv.text = list[position].date
            root.setOnClickListener {
                callback.invoke(root, position)
            }
        }
    }

    override fun getItemCount(): Int = list.size
}