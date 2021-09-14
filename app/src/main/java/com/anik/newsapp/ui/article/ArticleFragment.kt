package com.anik.newsapp.ui.article

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.anik.newsapp.R
import com.anik.newsapp.databinding.FragmentArticleBinding
import com.anik.newsapp.ui.NewsViewModel
import com.app.core.base.fragment.BaseFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : BaseFragment<FragmentArticleBinding>() {

    private val viewModel: NewsViewModel by activityViewModels()
    private val args: ArticleFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override val layoutResourceId: Int
        get() = R.layout.fragment_article

    override fun init() {
        binding.webView.apply {
            webViewClient = WebViewClient()
            args.article.url?.let {url->
                loadUrl(url)
            }
        }

        binding.fab.setOnClickListener {
            viewModel.saveArticle(args.article)
            Snackbar.make(binding.root, "Article saved successfully", Snackbar.LENGTH_SHORT).show()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = ArticleFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}