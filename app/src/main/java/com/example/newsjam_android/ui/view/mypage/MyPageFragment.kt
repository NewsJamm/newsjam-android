package com.example.newsjam_android.ui.view.mypage

import android.content.Intent
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.CategoryValueDataEntry
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.listener.Event
import com.anychart.chart.common.listener.ListenersInterface
import com.anychart.charts.TagCloud
import com.anychart.scales.OrdinalColor
import com.example.newsjam_android.R
import com.example.newsjam_android.databinding.FragmentMyPageBinding
import com.example.newsjam_android.ui.base.BaseFragment
import com.example.newsjam_android.ui.view.account.LoginActivity
import com.example.newsjam_android.ui.view.dialog.WithDrawDialog
import com.example.newsjam_android.ui.view.listener.DialogConfirmListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page),
    DialogConfirmListener {
    lateinit var tagCloud: TagCloud

    override fun setLayout() {
        initCloud()
        bindingTextViewClickedListener()
    }

    private fun bindingTextViewClickedListener() {
        with(binding) {
            fragmentMyPageScrapNewsTv.setOnClickListener {
                findNavController().navigate(
                    R.id.action_myPageFragment_to_scrapFragment
                )
            }
            fragmentMyPageRecentNewsTv.setOnClickListener {
                findNavController().navigate(
                    R.id.action_myPageFragment_to_recentFragment
                )
            }
            fragmentMyPageSettingLikeTv.setOnClickListener {
                findNavController().navigateSafe(
                    R.id.action_myPageFragment_to_likeChoiceFragment2,
                    args = Bundle().apply { putString("page", "my_page") }
                )
            }
            fragmentMyPageSettingFunctionTv.setOnClickListener {
                findNavController().navigateSafe(
                    R.id.action_myPageFragment_to_hotNewsConfirmFragment2,
                    args = Bundle().apply { putString("page", "my_page") }
                )
            }
            fragmentMyPageLogoutTv.setOnClickListener {
                outFrame()
            }
            fragmentMyPageExitTv.setOnClickListener {
                val dialog = WithDrawDialog(this@MyPageFragment)
                dialog.isCancelable = false
                dialog.show(requireActivity().supportFragmentManager, "WithDrawDialog")
            }
        }
    }


    private fun navigationWithNextPage(action: Int) {
        findNavController().navigate(action)
    }


    private fun initCloud() {
        tagCloud = AnyChart.tagCloud()
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
            setOnClickListener(object :
                ListenersInterface.OnClickListener(arrayOf("x", "category")) {
                override fun onClick(event: Event) {
                    val clickedTag = event.data["x"]  // 클릭한 단어 (x)
                    val category = event.data["category"]  // 카테고리 (category)
                    android.widget.Toast.makeText(
                        requireContext(),
                        "$clickedTag",
                        android.widget.Toast.LENGTH_SHORT
                    ).show()
                    // 원하는 동작 수행
                    android.util.Log.d("로그", "$clickedTag, Category: $category")
                }
            })
        }
        binding.fragmentMyPageMyWordCloudAcv.setChart(tagCloud);
    }

    override fun onClick() {
        outFrame()
    }

    private fun outFrame() {
        startActivity(Intent(requireContext(), LoginActivity::class.java))
        requireActivity().finish()
    }


}