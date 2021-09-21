package com.anik.newsapp.ui.searchnews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.anik.newsapp.R
import com.anik.newsapp.databinding.FragmentSearchNewsBinding
import com.anik.newsapp.ui.NewsAdapter
import com.anik.newsapp.ui.NewsViewModel
import com.app.core.base.fragment.AppFragment
import com.app.core.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchNewsFragment : AppFragment<NewsViewModel, FragmentSearchNewsBinding>() {

    //private val viewModel: NewsViewModel by viewModels()
    private val viewModel: NewsViewModel by activityViewModels()
    private var newsAdapter: NewsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun setViewModel(binding: FragmentSearchNewsBinding) {
        binding.viewModel = viewModel
    }

    override val layoutResourceId: Int
        get() = R.layout.fragment_search_news

    override fun init() {
        setUpObservers()
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        setUpRecyclerView()

        var job: Job? = null
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                job?.cancel()
                job = MainScope().launch {
                    delay(Constants.DELAY)
                    query?.let {
                        if(it.isNotEmpty())
                            viewModel.searchNews(it)
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                job?.cancel()
                job = MainScope().launch {
                    delay(Constants.DELAY)
                    newText?.let {
                        if(it.isNotEmpty())
                            viewModel.searchNews(it)
                    }
                }
                return true
            }

        })

    }

    private fun setUpObservers() {

        viewModel.searchNewsLiveData.observe(viewLifecycleOwner, {newsResponse->
            newsAdapter?.differ?.submitList(newsResponse.articles)
        })

        viewModel.toastMessage.observe(viewLifecycleOwner, {
            baseCommunicator?.showToast(it)
        })

        viewModel.showLoader.observe(viewLifecycleOwner, {
            if(it)
                baseCommunicator?.showLoader()
            else
                baseCommunicator?.hideLoader()
        })
    }

    private fun setUpRecyclerView() {
        newsAdapter = NewsAdapter().apply {
            setOnItemClickListener {
                val action = SearchNewsFragmentDirections.actionSearchNewsFragmentToArticleFragment(it)
                findNavController().navigate(action)
            }
        }
        binding.recyclerView.apply {
            adapter = newsAdapter
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = SearchNewsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
