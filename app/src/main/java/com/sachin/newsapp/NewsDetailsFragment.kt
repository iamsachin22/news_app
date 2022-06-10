package com.sachin.newsapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.sachin.newsapp.databinding.FragmentNewsDetailsBinding
import com.sachin.newsapp.presentation.viewmodel.NewsViewModel

class NewsDetailsFragment : Fragment() {
   private lateinit var binding: FragmentNewsDetailsBinding
   private lateinit var viewModel: NewsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {

        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsDetailsBinding.bind(view)
        val args : NewsDetailsFragmentArgs by navArgs()
        val article = args.selectedItem

        viewModel = (activity as MainActivity).viewModel

        binding.webDetails.apply {
            webViewClient = WebViewClient()
            if(article.url!==""){
                article.url?.let { loadUrl(it) }
            }
        }

        binding.fabsave.setOnClickListener {
            viewModel.saveArticles(article)
            Snackbar.make(view,"Saved Successfully!!",Snackbar.LENGTH_LONG).show()
        }
    }catch (e:Exception){
        Log.i("DATA",e.toString())
    }}

}