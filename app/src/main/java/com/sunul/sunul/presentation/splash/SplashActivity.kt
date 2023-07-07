package com.sunul.sunul.presentation.splash

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.sunul.sunul.R
import com.sunul.sunul.binding.BindingActivity
import com.sunul.sunul.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.*
import android.content.Intent
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import android.animation.PropertyValuesHolder

import com.sunul.sunul.presentation.MainActivity
import com.sunul.sunul.presentation.intro.IntroActivity

class SplashActivity : AppCompatActivity() {

    private val splashDelay: Long = 3 // 3초 동안 SplashActivity 표시
    private val splashCoroutineScope = CoroutineScope(Dispatchers.Main)
//    private lateinit var binding: ActivityMainBinding
    private val handler = Handler(Looper.getMainLooper())


    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        // 스플래시 화면 닫기 애니메이션
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            val propertyY = PropertyValuesHolder.ofFloat(
                View.TRANSLATION_Y,
                0f,
                -splashScreenView.view.height.toFloat()
            )
            val slideUp = ObjectAnimator.ofPropertyValuesHolder(splashScreenView.view, propertyY)
            slideUp.duration = 300L

            // 애니메이션 종료 시 splashScreenView 제거
            slideUp.doOnEnd { splashScreenView.remove() }

            // 애니메이션 실행
            slideUp.start()
        }

        moveMain(splashDelay)
    }


    override fun onDestroy() {
        splashCoroutineScope.cancel()
        super.onDestroy()
    }

    private fun moveMain(sec: Long) {
        handler.postDelayed({
            val intent = Intent(this, IntroActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }, (200 * sec))
    }
}