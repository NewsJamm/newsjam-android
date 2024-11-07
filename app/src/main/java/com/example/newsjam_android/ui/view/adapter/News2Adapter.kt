package com.example.newsjam_android.ui.view.adapter

import com.example.newsjam_android.R
import com.example.data.model.NewsItem
import com.example.data.model.NewsItem2
import com.example.data.model.NewsItem3
import com.example.newsjam_android.databinding.ItemNews2Binding
import com.example.newsjam_android.GlobalApplication
import com.example.newsjam_android.ui.base.BaseAdapter
import com.example.newsjam_android.ui.base.BaseDiffCallback

class News2Adapter(
) : BaseAdapter<NewsItem2, ItemNews2Binding>(
    BaseDiffCallback(
        itemsTheSame = { oldItem, newItem -> oldItem == newItem },
        contentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {
    private var news3Adapter = News3Adapter()
    override val layoutId: Int
        get() = R.layout.item_news_2

    fun setList(newsItem3List : List<List<NewsItem3>>?){
        newsItem3List?.map{news3Adapter.submitList(it)}
    }

    override fun bind(binding: ItemNews2Binding, item: NewsItem2) {
        with(binding){
            newsItem2 = item
            item.newsItem.profile?.let {
                GlobalApplication.loadCropRoundedSquareImage(binding.itemNews2Iv.context ,itemNews2Iv,
                    it,8)
            }
            itemNews2Rv.adapter = news3Adapter
        }
    }


}