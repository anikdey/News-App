package com.anik.newsapp.ui.breakingnews

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.anik.newsapp.R
import com.anik.newsapp.databinding.FragmentBreakingNewsBinding
import com.anik.newsapp.ui.stateadapter.LoadStateAdapter
import com.app.core.base.fragment.AppFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreakingNewsFragment : AppFragment<BreakingNewsViewModel, FragmentBreakingNewsBinding>() {

    private val viewModel: BreakingNewsViewModel by viewModels()

    private var newsAdapter = BreakingNewsPagedAdapter().apply {
        setOnItemClickListener {
            val action = BreakingNewsFragmentDirections.actionBreakingNewsFragmentToArticleFragment(it)
            findNavController().navigate(action)
        }
    }
    private val headerAdapter = LoadStateAdapter(newsAdapter::retry)
    private val footerAdapter = LoadStateAdapter(newsAdapter::retry)
    private val concatAdapter = newsAdapter.withLoadStateHeaderAndFooter(
        header = headerAdapter,
        footer = footerAdapter
    )

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

        binding.retryButton.setOnClickListener {
            newsAdapter.retry()
        }

        if(viewModel.breakingNewsLiveData.value == null) {
            viewModel.getBreakingNews("us")
        }

    }

    private fun setUpRecyclerView() {
        newsAdapter.addLoadStateListener { combinedLoadStates ->
            binding.progressBar.isVisible = combinedLoadStates.refresh is LoadState.Loading
            binding.recyclerView.isVisible = combinedLoadStates.refresh is LoadState.NotLoading
            binding.retryContainer.isVisible = combinedLoadStates.refresh is LoadState.Error
            if(combinedLoadStates.refresh is LoadState.Error) {
                binding.errorMessageTextView.text = (combinedLoadStates.refresh as LoadState.Error).error.localizedMessage
            }
            if(combinedLoadStates.refresh is LoadState.NotLoading && combinedLoadStates.append.endOfPaginationReached && newsAdapter.itemCount<1) {
                binding.recyclerView.isVisible = false
                binding.emptyDataTextView.visibility = View.VISIBLE
            } else {
                binding.emptyDataTextView.visibility = View.GONE
            }
        }

        binding.recyclerView.apply {
            adapter = concatAdapter
        }
    }

    private fun setUpObservers() {

        viewModel.breakingNewsLiveData.observe(viewLifecycleOwner, {
            newsAdapter.submitData(viewLifecycleOwner.lifecycle, it)
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

}