package com.example.newsjam_android.ui.view.adapter

import android.graphics.Color
import com.example.newsjam_android.R
import com.example.data.model.TagData
import com.example.newsjam_android.databinding.ItemLikeChoiceTagBinding
import com.example.newsjam_android.ui.base.BaseAdapter
import com.example.newsjam_android.ui.base.BaseDiffCallback
import com.example.newsjam_android.ui.view.listener.ItemAddListener

class LikeChoiceAdapter(
    private val itemAddListener: ItemAddListener
) : BaseAdapter<TagData, ItemLikeChoiceTagBinding>(
    BaseDiffCallback(
        itemsTheSame = { oldItem, newItem -> oldItem == newItem },
        contentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {

    override val layoutId: Int
        get() = R.layout.item_like_choice_tag

    override fun bind(binding: ItemLikeChoiceTagBinding, item: TagData) {
        if (item.isSelected) {
            binding.itemLikeChoiceTitleTv.setTextColor(Color.WHITE)
            binding.itemLikeChoiceTitleTv.setBackgroundResource(R.drawable.shape_fill_main_red_square_rounded_20dp)
        } else {
            binding.itemLikeChoiceTitleTv.setTextColor(Color.BLACK)
            binding.itemLikeChoiceTitleTv.setBackgroundResource(R.drawable.shape_fill_gray_square_rounded_20dp)
        }
        binding.tagData = item
        binding.root.setOnClickListener{
            if (!item.isSelected) {
                itemAddListener.add(item.title)
                binding.itemLikeChoiceTitleTv.setTextColor(Color.WHITE)
                binding.itemLikeChoiceTitleTv.setBackgroundResource(R.drawable.shape_fill_main_red_square_rounded_20dp)
                item.isSelected = true
            } else {
                itemAddListener.remove(item.title)
                binding.itemLikeChoiceTitleTv.setTextColor(Color.BLACK)
                binding.itemLikeChoiceTitleTv.setBackgroundResource(R.drawable.shape_fill_gray_square_rounded_20dp)
                item.isSelected = false
            }
        }
    }

}