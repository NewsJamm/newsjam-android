package com.example.newsjam_android.ui.view.home

import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.newsjam_android.R
import com.example.newsjam_android.data.model.ChatMessage
import com.example.newsjam_android.databinding.ActivityMainBinding
import com.example.newsjam_android.ui.base.BaseActivity
import com.example.newsjam_android.ui.view.adapter.ChattingAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var navController: NavController
    private lateinit var chattingAdapter: ChattingAdapter
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    override fun setLayout() {
        initAdapter()
        initializePersistentBottomSheet()
        setBottomNavigation()
        setupBackPressedDispatcher()
    }

    private lateinit var chatList: MutableList<ChatMessage>
    private fun initAdapter() {
        chatList = mutableListOf(
            ChatMessage.AIMessage("해당 뉴스를 더 자세히 알고싶으신가요?\n" + "무엇이든 물어보세요!"),
            ChatMessage.AppMessage("기사에 대한 자동 생성 질문 1", true),
            ChatMessage.AppMessage("기사에 대한 자동 생성 질문 2", false),
            ChatMessage.AppMessage("기사를 요약해줘", false),
        )
        chattingAdapter = ChattingAdapter()
        // 초기 데이터 설정 후 submitList 호출
        chattingAdapter.submitList(chatList)
        binding.recyclerView.adapter = chattingAdapter
    }

    private fun setBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.activityMainFcv.id) as NavHostFragment
        navController = navHostFragment.navController
        binding.activityMainBnv.setupWithNavController(navController)
    }


    private fun initializePersistentBottomSheet() {
        binding.layoutChatBottomSheetSendBt.setOnClickListener {
            val message = binding.layoutChatBottomSheetSendEt.text.toString()
            if (message.isNotBlank()) {
                val newChatList = chatList.toMutableList()  // 새로운 리스트 생성
                newChatList.add(ChatMessage.HumanMessage(message))  // 새로운 메시지를 추가
                chattingAdapter.submitList(newChatList){
                    binding.recyclerView.scrollToPosition(-1)
                }  // 새로운 리스트를 전달
                chatList = newChatList  // 기존 리스트를 새로운 리스트로 교체
                binding.layoutChatBottomSheetSendEt.text.clear()  // 입력 필드를 초기화
            }
        }

        binding.fragmentNewsDescriptionFtb.setOnClickListener {
            // BottomSheet의 peek_height만큼 보여주기
            binding.mainEt.visibility = View.VISIBLE
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
        }

        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheetLayout)

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> binding.mainEt.visibility = View.GONE
                    BottomSheetBehavior.STATE_EXPANDED -> binding.mainEt.visibility = View.VISIBLE
                    BottomSheetBehavior.STATE_COLLAPSED -> binding.mainEt.visibility = View.GONE
                    BottomSheetBehavior.STATE_DRAGGING -> Log.d("MainActivity", "state: dragging")
                    BottomSheetBehavior.STATE_SETTLING -> Log.d("MainActivity", "state: settling")
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> binding.mainEt.visibility =
                        View.VISIBLE
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // 사용자가 드래그한 위치를 저장
                bottomSheetBehavior.saveFlags = BottomSheetBehavior.SAVE_ALL
            }
        })

        // 사용자가 드래그한 위치까지만 열리도록 설정
        bottomSheetBehavior.isFitToContents = false
        bottomSheetBehavior.halfExpandedRatio = 0.5f // 기본값, 필요에 따라 조정
    }

    private fun setupBackPressedDispatcher() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                when (bottomSheetBehavior.state) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
                    }

                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                        binding.mainEt.visibility = View.GONE
                    }

                    else -> {
                        isEnabled = false
                        onBackPressedDispatcher.onBackPressed()
                    }
                }
            }
        })
    }
}