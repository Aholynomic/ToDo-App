package com.example.to_do_app.ui.edit

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do_app.R
import com.example.to_do_app.database.EventModel
import kotlinx.android.synthetic.main.events_adapter.view.*

/**
 * (GeeksforGeeks, 2021)
 * https://www.geeksforgeeks.org/how-to-build-a-grocery-android-app-using-mvvm-and-room-database/
 */
class EditAdapter(var list: List<EventModel>, val viewModel: EditViewModel) :
        RecyclerView.Adapter<EditAdapter.EventViewHolder>() {

    /**
     * add events_adapter.xml to this class
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.events_adapter, parent, false)
        return EventViewHolder(view)
    }

    /**
     * bind item views with adapter
     */
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        var currentPosition = list[position]
        holder.itemView.titleText.text = "Title: " + currentPosition.title
        holder.itemView.startTimeText.text = "Start: " + currentPosition.timeStart
        holder.itemView.endTimeText.text = "End: " + currentPosition.timeEnd
        holder.itemView.descText.text = "Description: " + currentPosition.description
        holder.itemView.buttonDelete.setOnClickListener {
            viewModel.delete(currentPosition)
        }
    }

    /**
     * return total number of size of list
     */
    override fun getItemCount(): Int {
        return list.size
    }

    /**
     * inner class for view holder
     */
    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}