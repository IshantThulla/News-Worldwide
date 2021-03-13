package com.androiddevs.internationalnews.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.androiddevs.internationalnews.MainNewsActivity
import com.androiddevs.internationalnews.R
import com.androiddevs.internationalnews.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.article_preview_fragment.*

class ArticlePreviewFragment: Fragment(R.layout.article_preview_fragment) {

    lateinit var viewModel: NewsViewModel
    val args: ArticlePreviewFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainNewsActivity).viewModel

        val article = args.article

        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }

        saveButton.setOnClickListener{
            viewModel.saveNewsArticle(article)
            Snackbar.make(view, "Article saved successfully", Snackbar.LENGTH_SHORT).show()
        }
    }
}