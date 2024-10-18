package com.example.demoweatherapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoweatherapp.databinding.LayoutRecentItemBinding
import com.example.demoweatherapp.models.History
import com.example.demoweatherapp.utils.convertMillisToTime
import com.example.demoweatherapp.view.holders.HistoryViewHolder

class HistoryAdapter : RecyclerView.Adapter<HistoryViewHolder>() {


    val originalList = ArrayList<History>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder =
        HistoryViewHolder(
            LayoutRecentItemBinding.inflate(LayoutInflater.from(parent.context))
        )


    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val model = originalList[position]
        holder.binding.model = model
        holder.binding.tvTime.setText(convertMillisToTime(model.time))

    }


    fun addItem(history: History) {
        originalList.add(history)
        notifyDataSetChanged()
    }

    fun setOriginalList(list: ArrayList<History>) {
        originalList.clear()
        originalList.addAll(list)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return originalList.size
    }
}