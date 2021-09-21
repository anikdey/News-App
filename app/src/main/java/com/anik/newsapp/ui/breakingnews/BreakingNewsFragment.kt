package com.anik.newsapp.ui.breakingnews

import android.os.Bundle
import android.widget.AbsListView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anik.newsapp.R
import com.anik.newsapp.databinding.FragmentBreakingNewsBinding
import com.anik.newsapp.ui.NewsAdapter
import com.anik.newsapp.ui.NewsViewModel
import com.anik.newsapp.util.Constants
import com.app.core.base.fragment.AppFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreakingNewsFragment : AppFragment<NewsViewModel, FragmentBreakingNewsBinding>() {

    private val viewModel: NewsViewModel by activityViewModels()
    private var newsAdapter: NewsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun setViewModel(binding: FragmentBreakingNewsBinding) {
        binding.viewModel = viewModel
    }

    override val layoutResourceId: Int
        get() = R.layout.fragment_breaking_news

    override fun init() {
        setUpObservers()
        setUpRecyclerView()
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

    private fun setUpRecyclerView() {
        newsAdapter = NewsAdapter().apply {
            setOnItemClickListener {
                val action = BreakingNewsFragmentDirections.actionBreakingNewsFragmentToArticleFragment(it)
                findNavController().navigate(action)
            }
        }
        binding.recyclerView.apply {
            adapter = newsAdapter
            addOnScrollListener(this@BreakingNewsFragment.scrollListener)
        }
    }

    private fun setUpObservers() {

        viewModel.breakingNewsLiveData.observe(viewLifecycleOwner, { newsResponse ->
            newsAdapter?.differ?.submitList(newsResponse.articles.toList())
            val totalPages = newsResponse.totalResults / Constants.QUERY_PAGE_SIZE + 2
            isLastPage = viewModel.breakingNewsPage == totalPages
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

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    var scrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager

            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = visibleItemCount + firstVisibleItemPosition >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= Constants.QUERY_PAGE_SIZE

            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning && isTotalMoreThanVisible && isScrolling

            if(shouldPaginate) {
                viewModel.getBreakingNews("us")
                isScrolling = false
            } else {
                binding.recyclerView.setPadding(0,0,0,0)
            }

        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = BreakingNewsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}