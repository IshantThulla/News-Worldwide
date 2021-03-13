package com.androiddevs.internationalnews.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.androiddevs.internationalnews.R
import kotlinx.android.synthetic.main.first_screen_fragment.*

class FirstScreenFragment : Fragment(R.layout.first_screen_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        topheadlinesbutton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_firstScreenFragment_to_topHeadlinesFragment)
        }

        searchnewsbutton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_firstScreenFragment_to_searchFragment)
        }

        savednewsbutton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_firstScreenFragment_to_storeNewsFragment)
        }
    }
}