package com.anik.newsapp.ui.savednews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.anik.network.util.Resource
import com.anik.newsapp.R
import com.anik.newsapp.databinding.FragmentSavedNewsBinding
import com.anik.newsapp.ui.NewsAdapter
import com.anik.newsapp.ui.NewsViewModel
import com.app.core.base.fragment.AppFragment
import com.google.android.material.snackbar.Snackbar
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

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                newsAdapter?.differ?.currentList?.let {
                    val article = it[position]
                    viewModel.deleteArticle(article)
                    Snackbar.make(binding.root, "Successfully deleted article", Snackbar.LENGTH_LONG).apply {
                        setAction("Undo") {
                            viewModel.saveArticle(article)
                        }
                        show()
                    }
                }
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.recyclerView)
        }

        viewModel.getSavedNews().observe(viewLifecycleOwner, {
            newsAdapter?.differ?.submitList(it)
        })

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

    }

    companion object {

        @JvmStatic
        fun newInstance() = SavedNewsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}