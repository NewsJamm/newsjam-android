package com.example.newsjam_android.ui.view.adapter

import com.example.newsjam_android.R
import com.example.data.model.NewsItem3
import com.example.newsjam_android.databinding.ItemNews3Binding
import com.example.newsjam_android.GlobalApplication
import com.example.newsjam_android.ui.base.BaseAdapter
import com.example.newsjam_android.ui.base.BaseDiffCallback

class News3Adapter : BaseAdapter<NewsItem3, ItemNews3Binding>(
    BaseDiffCallback(
        itemsTheSame = { oldItem, newItem -> oldItem == newItem },
        contentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {

    override val layoutId: Int
        get() = R.layout.item_news_3

    override fun bind(binding: ItemNews3Binding, item: NewsItem3) {
        with(binding) {
            newsItem3 = item
            item.newsItem.profile?.let {
                GlobalApplication.loadCropRoundedSquareImage(
                    item3NewsIv.context,
                    item3NewsIv,
                    it,
                    8
                )
            }
        }
    }
}