package com.anik.newsapp.ui.breakingnews

import android.os.Bundle
import com.anik.newsapp.R
import com.anik.newsapp.databinding.FragmentBreakingNewsBinding
import com.app.core.base.fragment.BaseFragment

class BreakingNewsFragment : BaseFragment<FragmentBreakingNewsBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override val layoutResourceId: Int
        get() = R.layout.fragment_breaking_news

    override fun init() {

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) = BreakingNewsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}