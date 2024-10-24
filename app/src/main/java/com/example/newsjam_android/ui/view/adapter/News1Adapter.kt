package com.example.newsjam_android.ui.view.adapter

import com.example.newsjam_android.R
import com.example.newsjam_android.data.model.NewsItem
import com.example.newsjam_android.databinding.ItemNewsBinding
import com.example.newsjam_android.GlobalApplication
import com.example.newsjam_android.ui.base.BaseAdapter
import com.example.newsjam_android.ui.base.BaseDiffCallback
import com.example.newsjam_android.ui.view.listener.AdapterItemClickedListener

class News1Adapter(
    private val adapterItemClickedListener: AdapterItemClickedListener
) : BaseAdapter<NewsItem, ItemNewsBinding>(
    BaseDiffCallback(
        itemsTheSame = { oldItem, newItem -> oldItem == newItem },
        contentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {
    override val layoutId: Int
        get() = R.layout.item_news

    override fun bind(binding: ItemNewsBinding, item: NewsItem) {
        with(binding) {
            newsItem = item
            GlobalApplication.loadCropRoundedSquareImage(
                itemNewsIv.context,
                itemNewsIv,
                item.profile,
                8
            )
        }
        binding.root.setOnClickListener{
            adapterItemClickedListener.onClick(item)
        }
    }
}