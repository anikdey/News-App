package com.anik.newsapp.ui.savednews

import android.os.Bundle
import com.anik.newsapp.R
import com.anik.newsapp.databinding.FragmentBreakingNewsBinding
import com.anik.newsapp.databinding.FragmentSavedNewsBinding
import com.app.core.base.fragment.BaseFragment

class SavedNewsFragment : BaseFragment<FragmentSavedNewsBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override val layoutResourceId: Int
        get() = R.layout.fragment_saved_news

    override fun init() {

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) = SavedNewsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}