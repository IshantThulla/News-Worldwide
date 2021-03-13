package com.androiddevs.internationalnews.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.androiddevs.internationalnews.MainNewsActivity
import com.androiddevs.internationalnews.R
import com.androiddevs.internationalnews.Resource
import com.androiddevs.internationalnews.adapter.NewsAdapter
import com.androiddevs.internationalnews.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.top_headlines_fragment.*

class TopHeadlinesFragment: Fragment(R.layout.top_headlines_fragment) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    val TAG = "TopHeadlinesFragment"


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainNewsActivity).viewModel

        setRecyclerView()

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, Id: Long) {
                val message1 = adapterView?.getItemAtPosition(position).toString()
                val message = "you selected ${adapterView?.getItemAtPosition(position).toString()}"
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                viewModel.getTopHeadlines(message1)
            }
        }

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_topHeadlinesFragment_to_articlePreviewFragment,
                bundle
            )
        }

        viewModel.topHeadlines.observe(viewLifecycleOwner, Observer {response ->
            when(response){
                is Resource.Success ->{
                    response.data?.let{newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    response.message?.let{message->
                        Log.e(TAG, "AN error occured: $message")
                    }
                }
                is Resource.Loading ->{
                }
            }
        })
    }

    private fun setRecyclerView(){
        newsAdapter = NewsAdapter()
        topHeadlinesRecyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}