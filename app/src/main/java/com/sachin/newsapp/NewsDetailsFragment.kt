package com.sachin.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.sachin.newsapp.databinding.FragmentNewsDetailsBinding

class NewsDetailsFragment : Fragment() {
   private lateinit var binding: FragmentNewsDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsDetailsBinding.bind(view)
        val args : NewsDetailsFragmentArgs by navArgs()
        val article = args.selectedItem

        binding.webDetails.apply {
            webViewClient = WebViewClient()
            if(article.url!==""){
                loadUrl(article.url)
            }
        }
    }

}