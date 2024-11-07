package com.example.newsjam_android.ui.view.news

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.data.extensions.TokenManager
import com.example.newsjam_android.R
import com.example.newsjam_android.databinding.FragmentNewsBinding
import com.example.newsjam_android.ui.base.BaseFragment
import com.example.newsjam_android.ui.view.adapter.HotTopicViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : BaseFragment<FragmentNewsBinding>(R.layout.fragment_news) {
    private lateinit var viewPagerAdapter: HotTopicViewPagerAdapter
    private lateinit var fragmentList: ArrayList<Fragment>
    private lateinit var tabList: List<String>

    @Inject
    lateinit var tokenManager: TokenManager
    override fun setLayout() {
        setList()
        initViewPager2()
        initTabBar()
    }

    private fun setList() {
        tabList = mutableListOf("PICK")
        runBlocking {
            (tabList as MutableList<String>).addAll(tokenManager.getList().first())
        }
        fragmentList = ArrayList()
        fragmentList.add(NewsPickFragment())
        for (i in 1..<tabList.size) {
            fragmentList.add(NewsCategoryFragment(tabList[i]))
        }
    }

    private fun initTabBar() {
        TabLayoutMediator(binding.fragmentNewsTb, binding.fragmentNewsVp) { tab, position ->
            tab.text = tabList[position]
        }.attach()
        setTabItemMargin(binding.fragmentNewsTb, 16)

        binding.fragmentNewsTb.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

        })
    }

    private fun initViewPager2() {
        viewPagerAdapter = HotTopicViewPagerAdapter(requireActivity(), fragmentList)
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