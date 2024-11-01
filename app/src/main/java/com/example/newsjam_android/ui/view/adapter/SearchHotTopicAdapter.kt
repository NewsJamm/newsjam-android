package com.example.newsjam_android.ui.view.adapter

import androidx.databinding.ViewDataBinding
import com.example.newsjam_android.R
import com.example.data.model.RankType
import com.example.newsjam_android.databinding.ItemSearchBinding
import com.example.newsjam_android.databinding.ItemSearchHotTopicBinding
import com.example.newsjam_android.GlobalApplication
import com.example.newsjam_android.ui.base.BaseDiffCallback
import com.example.newsjam_android.ui.base.BaseMultiAdapter

class SearchHotTopicAdapter : BaseMultiAdapter<RankType, ViewDataBinding>(
    BaseDiffCallback(
        itemsTheSame = { oldItem, newItem -> oldItem == newItem },
        contentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {
    companion object {
        const val VIEW_TYPE_HOT_TOPIC = 1
        const val VIEW_TYPE_SEARCH_RESULT = 2
    }

    override fun getLayoutId(viewType: Int) = when (viewType) {
        VIEW_TYPE_SEARCH_RESULT -> R.layout.item_search
        VIEW_TYPE_HOT_TOPIC -> R.layout.item_search_hot_topic
        else -> throw IllegalAccessError("Invalid View Types")
    }

    override fun getViewTypeForItem(item: RankType) = when (item) {
        is RankType.HopTopicType -> VIEW_TYPE_HOT_TOPIC
        is RankType.SearchResultType-> VIEW_TYPE_SEARCH_RESULT
    }

    override fun bind(binding: ViewDataBinding, item: RankType) {
        when(item){
            is RankType.HopTopicType -> (binding as ItemSearchHotTopicBinding).resultType = item
            is RankType.SearchResultType -> {
                (binding as ItemSearchBinding).searchType = item
                GlobalApplication.loadCropRoundedSquareImage(binding.root.context,binding.itemSearchProfileIv,item.profile,8)
            }
        }
    }

}