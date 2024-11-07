package com.example.newsjam_android.ui.view.home

import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.newsjam_android.R
import com.example.data.model.ChatMessage
import com.example.data.request.ChatBotDTO
import com.example.domain.mapper.toAI
import com.example.domain.mapper.toApp
import com.example.domain.mapper.toUser
import com.example.newsjam_android.data.enums.ChatOwner
import com.example.newsjam_android.databinding.ActivityMainBinding
import com.example.newsjam_android.ui.base.BaseActivity
import com.example.newsjam_android.ui.view.adapter.ChattingAdapter
import com.example.newsjam_android.ui.view.hottopic.HotTopicWordCloudFragment.Companion.HOTTOPICWORDSCOUNT
import com.example.newsjam_android.ui.view.listener.AdapterItemClickedListener
import com.example.newsjam_android.ui.view.viewmodel.CategoryViewModel
import com.example.newsjam_android.ui.view.viewmodel.ChatViewModel
import com.example.newsjam_android.ui.view.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main), AdapterItemClickedListener {
    private lateinit var navController: NavController
    private lateinit var chattingAdapter: ChattingAdapter
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var mainViewModel : MainViewModel
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var chatViewModel : ChatViewModel
    var CHATTYPE = ChatOwner.AI
    private var chatList: MutableList<ChatMessage> = mutableListOf()


    override fun setLayout() {
        initViewModel()
        initAdapter()
        initializePersistentBottomSheet()
        setBottomNavigation()
        setupBackPressedDispatcher()
        hideFloating()
        observeLifeCycle()
        initChat()
    }

    private fun initChat() {
        if (chatList.size == 3) {
            chatList.add(ChatMessage.AppMessage("기사를 요약해줘", false))
            val initList = chatList
            chattingAdapter.submitList(initList)
            stateChangeChatType(ChatOwner.HUMAN)
        }
    }

    private fun initViewModel(){
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        categoryViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        chatViewModel = ViewModelProvider(this)[ChatViewModel::class.java]
    }

    fun initAdapter() {
        chattingAdapter = ChattingAdapter(this)
        chatList.clear()
        chatList.add(ChatMessage.AIMessage("해당 뉴스를 더 자세히 알고싶으신가요?\n" + "무엇이든 물어보세요!"))
        chattingAdapter.submitList(chatList)
        binding.recyclerView.adapter = chattingAdapter
    }



    private fun setBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.activityMainFcv.id) as NavHostFragment
        navController = navHostFragment.navController
        binding.activityMainBnv.setupWithNavController(navController)
    }

    fun hideFloating(){
        binding.fragmentNewsDescriptionFtb.visibility = View.GONE
    }

    fun showFloating(){
        binding.fragmentNewsDescriptionFtb.visibility = View.VISIBLE
    }

    fun updateChat(message: ChatMessage){
        chatList.add(message)
        val list = chatList
        chattingAdapter.submitList(list)
    }

    private fun observeLifeCycle(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                chatViewModel.chat.collectLatest {
                    if (it.isSuccess) {
                        Log.d("메인","${chatList.size}  $it $CHATTYPE")
                        when (CHATTYPE) {
                            ChatOwner.HUMAN -> {
                                chatList.add(it.result!!.toUser())
                            }
                            ChatOwner.APP -> {
                                chatList.add(it.result!!.toApp())
                            }
                            ChatOwner.AI -> {
                                chatList.add(it.result!!.toAI())
                                stateChangeChatType(ChatOwner.HUMAN)
                            }
                        }
                        initChat()
                        binding.recyclerView.adapter = chattingAdapter
                    }
                }
            }
        }
    }

    fun stateChangeChatType(chatOwner: ChatOwner) {CHATTYPE = chatOwner}

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
                chatViewModel.postChatBot(ChatBotDTO(message,"9"))
                stateChangeChatType(ChatOwner.AI)
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

    override fun onClick(item: Any) {
        item as ChatMessage.AppMessage
        stateChangeChatType(ChatOwner.AI)
        chatViewModel.postChatBot(ChatBotDTO(
            chat_message = item.message,
            news_id = "1"
        ))
    }
}