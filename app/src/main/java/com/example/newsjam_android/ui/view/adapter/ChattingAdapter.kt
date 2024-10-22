package com.example.newsjam_android.ui.view.adapter

import android.view.View
import androidx.databinding.ViewDataBinding
import com.example.newsjam_android.R
import com.example.newsjam_android.data.model.ChatMessage
import com.example.newsjam_android.data.model.NewsItem3
import com.example.newsjam_android.databinding.ItemAiChatBinding
import com.example.newsjam_android.databinding.ItemAppChatBinding
import com.example.newsjam_android.databinding.ItemHumanChatBinding
import com.example.newsjam_android.databinding.ItemNews3Binding
import com.example.newsjam_android.databinding.LayoutChatBottomSheetBinding
import com.example.newsjam_android.ui.base.BaseAdapter
import com.example.newsjam_android.ui.base.BaseDiffCallback
import com.example.newsjam_android.ui.base.BaseMultiAdapter

class ChattingAdapter : BaseMultiAdapter<ChatMessage, ViewDataBinding>(
    BaseDiffCallback(
        itemsTheSame = { oldItem, newItem -> oldItem == newItem },
        contentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {
    companion object {
        const val VIEW_TYPE_AI = 1
        const val VIEW_TYPE_APP = 2
        const val VIEW_TYPE_HUMAN = 3
    }

    override fun getLayoutId(viewType: Int) = when (viewType) {
        VIEW_TYPE_AI -> R.layout.item_ai_chat
        VIEW_TYPE_APP -> R.layout.item_app_chat
        VIEW_TYPE_HUMAN -> R.layout.item_human_chat
        else -> throw IllegalAccessError("Invalid View Types")
    }

    override fun getViewTypeForItem(item: ChatMessage) = when (item) {
        is ChatMessage.AIMessage -> VIEW_TYPE_AI
        is ChatMessage.AppMessage -> VIEW_TYPE_APP
        is ChatMessage.HumanMessage -> VIEW_TYPE_HUMAN
    }

    override fun bind(binding: ViewDataBinding, item: ChatMessage) {
        when(item){
            is ChatMessage.AIMessage -> (binding as ItemAiChatBinding).aiItem = item
            is ChatMessage.HumanMessage -> (binding as ItemHumanChatBinding).humanItem = item
            is ChatMessage.AppMessage -> {
                (binding as ItemAppChatBinding).appItem  = item
                with(binding) {
                    if (item.topMessage) {
                        itemAppIv.visibility = View.VISIBLE
                        itemAppTopMessageTv.visibility = View.VISIBLE
                    } else {
                        itemAppIv.visibility = View.GONE
                        itemAppTopMessageTv.visibility = View.GONE
                    }
                }
            }
        }
    }

}