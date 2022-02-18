package com.example.to_do_app.ui.edit

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do_app.R
import com.example.to_do_app.database.EventModel
import com.example.to_do_app.ui.insertevent.InsertEventActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    lateinit var viewModel: EditViewModel
    lateinit var editAdapter: EditAdapter
    lateinit var list: List<EventModel>
    private lateinit var dateText: TextView
    private lateinit var rvList: RecyclerView
    private lateinit var addButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        dateText = findViewById(R.id.dateTextView)
        rvList = findViewById(R.id.recyclerView)
        addButton = findViewById(R.id.btnAdd)

        // initialise view model instance
        viewModel =
                ViewModelProvider(this).get(EditViewModel::class.java)

        // initialise adapter to bind data to the recycler view (GeeksforGeeks, 2021)
        // https://www.geeksforgeeks.org/how-to-build-a-grocery-android-app-using-mvvm-and-room-database/
        editAdapter = EditAdapter(listOf(), viewModel)
        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = editAdapter

        // grab intent value from Schedule Fragment
        val dateValue = intent.getStringExtra("Date").toString()
        dateText.apply {
            text = dateValue
        }

        // display items for specific date in recycler view
        viewModel.specificEvents(dateValue).observe(this, Observer {
            editAdapter.list = it
            editAdapter.notifyDataSetChanged()
        })

        // on click listener for floating action button
        addButton.setOnClickListener {
            moveActivity(dateValue)
        }
    }

    /**
     * move to insert event activity if the floating action button has been pressed
     */
    private fun moveActivity(dateValue: String) {
        // pass the date to activity
        val intent = Intent(this@EditActivity, InsertEventActivity::class.java).apply {
            putExtra("Date", dateValue)
        }

        startActivity(intent)
    }
}