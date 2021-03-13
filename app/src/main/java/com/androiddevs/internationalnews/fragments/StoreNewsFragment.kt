package com.androiddevs.internationalnews.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.androiddevs.internationalnews.MainNewsActivity
import com.androiddevs.internationalnews.R
import com.androiddevs.internationalnews.adapter.NewsAdapter
import com.androiddevs.internationalnews.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.save_news_fragment.*

class StoreNewsFragment: Fragment(R.layout.save_news_fragment) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainNewsActivity).viewModel
        setRecyclerView()


        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_storeNewsFragment_to_articlePreviewFragment,
                bundle
            )
        }

        viewModel.getSavedNews().observe(viewLifecycleOwner, Observer { articles ->
            newsAdapter.differ.submitList(articles)

        })

    }

    private fun setRecyclerView(){
        newsAdapter = NewsAdapter()
        SavedNewsRecyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}