package com.anik.newsapp.ui.stateadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anik.newsapp.databinding.LoadStateFooterBinding
import com.anik.newsapp.ui.stateadapter.LoadStateAdapter.LoadStateViewHolder

class LoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = LoadStateFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateViewHolder(binding)
    }

    inner class LoadStateViewHolder(private val binding: LoadStateFooterBinding): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                loadStateRetry.setOnClickListener {
                    retry.invoke()
                }
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                loadStateRetry.isVisible = loadState is LoadState.Error
                errorTextView.isVisible = loadState is LoadState.Error
                if (loadState is LoadState.Error) {
                    errorTextView.text = loadState.error.localizedMessage
                }
            }
        }
    }
}