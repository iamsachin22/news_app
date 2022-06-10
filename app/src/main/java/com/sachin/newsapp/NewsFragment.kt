package com.sachin.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sachin.newsapp.databinding.FragmentNewsBinding
import com.sachin.newsapp.presentation.adapter.NewsAdapter
import com.sachin.newsapp.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NewsFragment : Fragment() {
    private lateinit var viewModel: NewsViewModel
    private lateinit var fragmentNewsBinding: FragmentNewsBinding
    private lateinit var newsAdapter: NewsAdapter
    private var country = "us"
    private var page = 1
    private var isScrolling = false
    private var isLoading =false
    private var isLastPage = false
    private var  pages=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentNewsBinding = FragmentNewsBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        newsAdapter = (activity as MainActivity).newsAdapter
        newsAdapter.setOnItemClickListener {
            val bundle= Bundle().apply {
                putSerializable("selected_item",it)
            }
            findNavController().navigate(
                R.id.action_newsFragment_to_newsDetailsFragment
                ,bundle

            )
        }
        initRecyclerView()
        viewNewsList()
        setSearchedNews()
    }

    private fun viewNewsList() {
        viewModel.getNewsHeadLines(country,page)
        viewModel.newsHeadLines.observe(viewLifecycleOwner) { response ->
            when (response) {
                is com.sachin.newsapp.data.util.Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles.toList())
                        if(it.totalResults%20 == 0){
                            pages = it.totalResults / 20
                        }
                        else{
                            pages = it.totalResults /20+1
                        }
                        isLastPage = page == pages
                    }
                }
                is com.sachin.newsapp.data.util.Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "An Error Occured : $it", Toast.LENGTH_SHORT)
                            .show()
                    }

                }
                is com.sachin.newsapp.data.util.Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun initRecyclerView() {
        fragmentNewsBinding.recyclerNews.apply {
            adapter= newsAdapter
            layoutManager= LinearLayoutManager(activity)
            addOnScrollListener(this@NewsFragment.onScrollListener)
        }
    }

    private fun showProgressBar(){
        isLoading = true
        fragmentNewsBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        isLoading = false
        fragmentNewsBinding.progressBar.visibility = View.INVISIBLE
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = fragmentNewsBinding.recyclerNews.layoutManager as LinearLayoutManager
            val sizeOfCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            val hasEndItem = topPosition+visibleItems >= sizeOfCurrentList
            val shouldPaginate = !isLoading && !isLastPage && hasEndItem && isScrolling
            if(shouldPaginate){
                page++
                viewModel.getNewsHeadLines(country, page)
                isScrolling=false
            }
        }
    }

    // search news

    private fun setSearchedNews(){
        fragmentNewsBinding.searchedView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchNews("us",query.toString(),page)
                viewSearchedNews()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                MainScope().launch {
                    delay(2000)
                    viewModel.searchNews("us",newText.toString(),page)
                    viewSearchedNews()
                }
                return false
            }

        })

        fragmentNewsBinding.searchedView.setOnCloseListener(object : SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                initRecyclerView()
                viewNewsList()
                return false
            }

        })
    }

    fun viewSearchedNews(){
        viewModel.searchedNews.observe(viewLifecycleOwner) { response ->
            when (response) {
                is com.sachin.newsapp.data.util.Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles.toList())
                        if(it.totalResults%20 == 0){
                            pages = it.totalResults / 20
                        }
                        else{
                            pages = it.totalResults /20+1
                        }
                        isLastPage = page == pages
                    }
                }
                is com.sachin.newsapp.data.util.Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "An Error Occured : $it", Toast.LENGTH_SHORT)
                            .show()
                    }

                }
                is com.sachin.newsapp.data.util.Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

}