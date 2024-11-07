package com.example.newsjam_android.ui.view.adapter

import android.view.View
import com.example.newsjam_android.R
import com.example.newsjam_android.GlobalApplication
import com.example.data.model.ScrapType
import com.example.newsjam_android.databinding.ItemSearchScrapBinding
import com.example.newsjam_android.ui.base.BaseAdapter
import com.example.newsjam_android.ui.base.BaseDiffCallback
import com.example.newsjam_android.ui.view.listener.AdapterItemClickedListener

class ScrapAdapter(private val adapterItemClickedListener: AdapterItemClickedListener) : BaseAdapter<ScrapType, ItemSearchScrapBinding>(
    BaseDiffCallback(
        itemsTheSame = { oldItem, newItem -> oldItem == newItem },
        contentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {
    override val layoutId: Int
        get() = R.layout.item_search_scrap

    override fun bind(binding: ItemSearchScrapBinding, item: ScrapType) {
        with(binding) {
            scrapType = item
            GlobalApplication.loadCropRoundedSquareImage(binding.itemSearchProfileIv.context, binding.itemSearchProfileIv, item.searchResultType.profile,8)
            if(item.isScrap){
                binding.itemSearchScrapIv.visibility = View.VISIBLE
            }
            else{
                binding.itemSearchScrapIv.visibility = View.GONE
            }

            binding.itemSearchScrapIv.setOnClickListener {
                val mutableList = currentList.toMutableList()
                mutableList.remove(item)
                submitList(mutableList)
            }

            binding.root.setOnClickListener{
                adapterItemClickedListener.onClick(item)
            }
        }
    }
}