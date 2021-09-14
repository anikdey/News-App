package com.anik.newsapp.ui.savednews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.anik.network.util.Resource
import com.anik.newsapp.R
import com.anik.newsapp.databinding.FragmentSavedNewsBinding
import com.anik.newsapp.ui.NewsAdapter
import com.anik.newsapp.ui.NewsViewModel
import com.app.core.base.fragment.AppFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedNewsFragment : AppFragment<NewsViewModel, FragmentSavedNewsBinding>() {

    private val viewModel: NewsViewModel by activityViewModels()
    private var newsAdapter: NewsAdapter? = null

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
        setUpObservers()
        setUpRecyclerView()
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

    private fun setUpRecyclerView() {
        newsAdapter = NewsAdapter().apply {
            setOnItemClickListener {
                val action = SavedNewsFragmentDirections.actionSavedNewsFragment2ToArticleFragment(it)
                findNavController().navigate(action)
            }
        }
        binding.recyclerView.apply {
            adapter = newsAdapter
        }
    }

    private fun setUpObservers() {
        viewModel.breakingNews.observe(viewLifecycleOwner,  {response->
            baseCommunicator?.hideLoader()
            when(response) {
                is Resource.Success -> {
                    response.data?.let { newsResponse ->
                        newsAdapter?.differ?.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    baseCommunicator?.showToast(response.message)
                }
                is Resource.Loading -> {
                    baseCommunicator?.showLoader()
                }
            }
        })
    }

    companion object {

        @JvmStatic
        fun newInstance() = SavedNewsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}