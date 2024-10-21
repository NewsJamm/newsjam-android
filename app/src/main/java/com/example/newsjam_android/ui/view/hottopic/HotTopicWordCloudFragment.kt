package com.example.newsjam_android.ui.view.hottopic

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.CategoryValueDataEntry
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.listener.Event
import com.anychart.chart.common.listener.ListenersInterface
import com.anychart.charts.TagCloud
import com.anychart.scales.OrdinalColor
import com.example.newsjam_android.R
import com.example.newsjam_android.databinding.FragmentHotTopicWordCloudBinding
import com.example.newsjam_android.ui.base.BaseFragment
import kotlinx.coroutines.launch

class HotTopicWordCloudFragment(private val title : String) :
    BaseFragment<FragmentHotTopicWordCloudBinding>(R.layout.fragment_hot_topic_word_cloud) {
    lateinit var tagCloud: TagCloud
    override fun setLayout() {
        initCloud()
        setChartClickListener()
    }

    private fun initCloud() {
        tagCloud = AnyChart.tagCloud()
        tagCloud.title(title)
        //데이터 추가
        val data: MutableList<DataEntry> = ArrayList()
        data.add(CategoryValueDataEntry("김준하", "백엔드", 1383220000))
        data.add(CategoryValueDataEntry("김진하", "백엔드", 1316000000))
        data.add(CategoryValueDataEntry("박지원", "안드로이드", 324982000))
        data.add(CategoryValueDataEntry("이은규", "PM", 324982000))

        val ordinalColor = OrdinalColor.instantiate().apply {
            colors(arrayOf("#7F21F7", "#AD71FA", "#B47EFA", "#D2B0FF"))
        }
        with(tagCloud) {
            colorScale(ordinalColor)
            angles(arrayOf(-90.0, 0.0, 90.0))
            colorRange().enabled(true)
            colorRange().colorLineSize(15.0)
            data(data);
        }
        binding.fragmentHotTopicWordCloudWc.setChart(tagCloud);
    }
    private fun setChartClickListener() {
        tagCloud.setOnClickListener(object :
            ListenersInterface.OnClickListener(arrayOf("x", "category")) {
            override fun onClick(event: Event) {
                val clickedTag = event.data["x"]
                val category = event.data["category"]
                requireActivity().runOnUiThread {
                    Toast.makeText(requireContext(), "$clickedTag", Toast.LENGTH_SHORT).show()
                    Log.d("로그", "$clickedTag, Category: $category")
                    commitFragment()
                }
            }
        })
    }
    private fun commitFragment(){
           childFragmentManager.beginTransaction().apply {
                add(R.id.fragment_hot_topic_word_cloud_fl, HotTopicDetailFragment())
                commit()
        }
    }

    override fun onResume() {
        super.onResume()
        setChartClickListener()
    }

}