package com.sachin.newsapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sachin.newsapp.data.model.Article
import com.sachin.newsapp.databinding.NewsListItemBinding
import java.util.*


class NewsAdapter: RecyclerView.Adapter<NewsAdapter.newsViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,callback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): newsViewHolder {
        val binding = NewsListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return newsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: newsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    inner class newsViewHolder(
        val binding:NewsListItemBinding
    ):RecyclerView.ViewHolder(binding.root){
        fun bind(article: Article){
            binding.textViewTitle.text = article.title
            binding.textViewDescription.text = article.description
            binding.textViewPublishedAt.text = "Date : "+article.publishedAt
            binding.textViewSource.text = "Source : "+article.source.name

            Glide.with(binding.imageArticle.context)
                .load(article.urlToImage)
                .into(binding.imageArticle)
        }
    }


}