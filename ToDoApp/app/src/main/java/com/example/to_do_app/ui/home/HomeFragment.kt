package com.example.to_do_app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.to_do_app.R

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        viewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        val textView: TextView = root.findViewById(R.id.home_alert)

        viewModel.getNumber()

        viewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return root
    }
}