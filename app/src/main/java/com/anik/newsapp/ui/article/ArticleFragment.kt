package com.anik.newsapp.ui.article

import android.os.Bundle
import com.anik.newsapp.R
import com.anik.newsapp.databinding.FragmentArticleBinding
import com.anik.newsapp.databinding.FragmentBreakingNewsBinding
import com.app.core.base.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : BaseFragment<FragmentArticleBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override val layoutResourceId: Int
        get() = R.layout.fragment_article

    override fun init() {

    }

    companion object {

        @JvmStatic
        fun newInstance() = ArticleFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}