package com.example.newsjam_android.ui.view.adapter

import com.example.newsjam_android.R
import com.example.newsjam_android.data.model.NewsItem
import com.example.newsjam_android.databinding.ItemNewsBinding
import com.example.newsjam_android.domain.extention.GlobalApplication
import com.example.newsjam_android.ui.base.BaseAdapter
import com.example.newsjam_android.ui.base.BaseDiffCallback

class News1Adapter : BaseAdapter<NewsItem, ItemNewsBinding>(
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
    }
}