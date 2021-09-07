package com.anik.newsapp.ui.searchnews

import android.os.Bundle
import com.anik.newsapp.R
import com.anik.newsapp.databinding.FragmentSearchNewsBinding
import com.app.core.base.fragment.BaseFragment

class SearchNewsFragment : BaseFragment<FragmentSearchNewsBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override val layoutResourceId: Int
        get() = R.layout.fragment_search_news

    override fun init() {

    }

    companion object {

        @JvmStatic
        fun newInstance() = SearchNewsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}