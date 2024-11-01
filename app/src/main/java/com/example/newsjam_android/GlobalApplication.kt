package com.example.newsjam_android


import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.data.di.AppCoroutineScope
import com.example.data.extensions.TokenManager
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class GlobalApplication : Application() {

    @Inject
    lateinit var tokenManager: TokenManager

    @Inject
    @AppCoroutineScope
    lateinit var scope: CoroutineScope

    override fun onCreate() {
        super.onCreate()
        instance = this
        takeFirebaseToken()
    }

    private fun takeFirebaseToken() {
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val token = task.result
                    scope.launch {
                        tokenManager.saveFirebaseToken(token) // Firebase 토큰 저장
                    }
                    Log.d("토큰", "FCM 토큰: $token")
                } else {
                    Log.e("토큰", "FCM 토큰 가져오기 실패", task.exception)
                }
            }
    }


    companion object {
        lateinit var instance: GlobalApplication
            private set

        // 이미지를 맞추어 로드
        fun loadImage(context: Context, imageView: ImageView, source: Any) {
            Glide.with(context)
                .load(source)
                .into(imageView)
        }


        // 프로필 이미지 (동그란 이미지) 지정
        fun loadProfileImage(imageView: ImageView, source: Any) {
            Glide.with(instance)
                .load(source)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(imageView)
        }


        // 이미지를 맞게 조정하여 로드
        fun loadCropImage(context: Context, imageView: ImageView, source: Any) {
            Glide.with(context)
                .load(source)
                .centerCrop()
                .into(imageView)
        }


        // 네 개의 변이 Rounded로 처리되어있는 사각형
        fun loadCropRoundedSquareImage(
            context: Context,
            imageView: ImageView,
            source: Any,
            rounded: Int
        ) {
            val density = context.resources.displayMetrics.density
            val roundedCorners = RoundedCorners((rounded * density).toInt())

            Glide.with(context)
                .load(source)
                .apply(RequestOptions().transform(CenterCrop(), roundedCorners))
                .into(imageView)
        }
    }
}