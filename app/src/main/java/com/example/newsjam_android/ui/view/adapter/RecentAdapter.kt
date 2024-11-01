package com.example.newsjam_android.ui.view.adapter

import com.example.newsjam_android.R
import com.example.newsjam_android.GlobalApplication
import com.example.data.model.RankType
import com.example.newsjam_android.databinding.ItemSearchRecentBinding
import com.example.newsjam_android.ui.base.BaseAdapter
import com.example.newsjam_android.ui.base.BaseDiffCallback
import com.example.newsjam_android.ui.view.listener.AdapterItemClickedListener

class RecentAdapter(private val adapterItemClickedListener: AdapterItemClickedListener) : BaseAdapter<RankType.SearchResultType, ItemSearchRecentBinding>(
    BaseDiffCallback(
        itemsTheSame = { oldItem, newItem -> oldItem == newItem },
        contentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {
    override val layoutId: Int
        get() = R.layout.item_search_recent

    override fun bind(binding: ItemSearchRecentBinding, item: RankType.SearchResultType) {
        with(binding) {
            recentType = item
            GlobalApplication.loadCropRoundedSquareImage(binding.itemRecentProfileIv.context, binding.itemRecentProfileIv, item.profile,8)

            binding.root.setOnClickListener{
                adapterItemClickedListener.onClick(item)
            }
        }
    }
}