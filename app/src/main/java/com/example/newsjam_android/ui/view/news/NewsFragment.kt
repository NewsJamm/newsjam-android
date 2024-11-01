package com.example.newsjam_android.ui.view.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.newsjam_android.R
import com.example.newsjam_android.databinding.FragmentNewsBinding
import com.example.newsjam_android.ui.base.BaseFragment
import com.example.newsjam_android.ui.view.adapter.HotTopicViewPagerAdapter
import com.example.newsjam_android.ui.view.hottopic.HotTopicDetailFragment
import com.example.newsjam_android.ui.view.hottopic.HotTopicWordCloudFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class NewsFragment : BaseFragment<FragmentNewsBinding>(R.layout.fragment_news) {
    private lateinit var viewPagerAdapter: HotTopicViewPagerAdapter
    private lateinit var fragmentList: ArrayList<Fragment>
    private lateinit var tabList : List<String>
    override fun setLayout() {
        setList()
        initViewPager2()
        initTabBar()
    }
    private fun setList(){
        tabList = listOf("PICK", "카테고리 1", "카테고리 2", "카테고리 3",  "카테고리 4")
        fragmentList = ArrayList()
        fragmentList.add(NewsPickFragment())
        for (i in 1..<tabList.size){
            fragmentList.add(NewsCategoryFragment())
        }
    }
    private fun initTabBar() {
        TabLayoutMediator(binding.fragmentNewsTb, binding.fragmentNewsVp) { tab, position ->
            tab.text = tabList[position]
        }.attach()
        setTabItemMargin(binding.fragmentNewsTb, 16)

        binding.fragmentNewsTb.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

        })
    }
    private fun initViewPager2() {
        viewPagerAdapter = HotTopicViewPagerAdapter(requireActivity(),fragmentList)
        binding.fragmentNewsVp.adapter = viewPagerAdapter
    }
    private fun setTabItemMargin(tabLayout: TabLayout, marginEnd: Int = 20) {
        val tabs = tabLayout.getChildAt(0) as ViewGroup
        for (i in 0 until tabs.childCount) {
            val tab = tabs.getChildAt(i)
            val lp = tab.layoutParams as LinearLayout.LayoutParams
            lp.marginEnd = marginEnd
            tab.layoutParams = lp
            tabLayout.requestLayout()
        }
    }

}