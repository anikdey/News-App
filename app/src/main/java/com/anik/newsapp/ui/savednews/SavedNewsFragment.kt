package com.anik.newsapp.ui.savednews

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.anik.newsapp.R
import com.anik.newsapp.databinding.FragmentSavedNewsBinding
import com.anik.newsapp.ui.NewsViewModel
import com.app.core.base.fragment.AppFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedNewsFragment : AppFragment<NewsViewModel, FragmentSavedNewsBinding>() {

    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun setViewModel(binding: FragmentSavedNewsBinding) {
        binding.viewModel = viewModel
    }

    override val layoutResourceId: Int
        get() = R.layout.fragment_saved_news

    override fun init() {

    }

    companion object {

        @JvmStatic
        fun newInstance() = SavedNewsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}