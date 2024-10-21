package com.example.newsjam_android.ui.view.adapter

import com.example.newsjam_android.R
import com.example.newsjam_android.data.model.NewsItem
import com.example.newsjam_android.databinding.ItemNews4Binding
import com.example.newsjam_android.databinding.ItemNewsBinding
import com.example.newsjam_android.domain.extention.GlobalApplication
import com.example.newsjam_android.ui.base.BaseAdapter
import com.example.newsjam_android.ui.base.BaseDiffCallback
import com.example.newsjam_android.ui.view.listener.AdapterItemClickedListener

class News4Adapter(private val adapterItemClickedListener: AdapterItemClickedListener) : BaseAdapter<NewsItem, ItemNews4Binding>(
    BaseDiffCallback(
        itemsTheSame = { oldItem, newItem -> oldItem == newItem },
        contentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {
    override val layoutId: Int
        get() = R.layout.item_news4

    override fun bind(binding: ItemNews4Binding, item: NewsItem) {
        with(binding) {
            newsItem = item
            GlobalApplication.loadCropImage(binding.itemNewsIv.context, binding.itemNewsIv, item.profile)

            binding.root.setOnClickListener{
                adapterItemClickedListener.onClick(item)
            }
        }
    }
}