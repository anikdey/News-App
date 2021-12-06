package com.anik.newsapp.ui.breakingnews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.anik.network.response.Article
import com.anik.newsapp.databinding.ItemNewsBinding
import com.bumptech.glide.Glide

class BreakingNewsPagedAdapter : PagingDataAdapter<Article, BreakingNewsPagedAdapter.ArticleViewHolder>(ArticleComparator) {

    private var onItemClickListener: ((Article) -> Unit)? = null

    object ArticleComparator : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.setUpView(getItem(position))
    }

    fun setOnItemClickListener( listener: (Article) -> Unit ) {
        onItemClickListener = listener
    }

    inner class ArticleViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setUpView(article: Article?) {
            article?.let {
                Glide.with(binding.root).load(article.urlToImage).into(binding.ivArticleImage)
                binding.tvSource.text = article.source?.name
                binding.tvTitle.text = article.title
                binding.tvDescription.text = article.description
                binding.tvPublishedAt.text = article.publishedAt
                binding.root.setOnClickListener {
                    onItemClickListener?.let {
                        it(article)
                    }
                }
            }
        }
    }

}