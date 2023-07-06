package com.sunul.sunul.presentation.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.sunul.sunul.R
import com.sunul.sunul.binding.BindingActivity
import com.sunul.sunul.databinding.ActivityIntroBinding
import com.sunul.sunul.databinding.ActivityMainBinding
import com.sunul.sunul.presentation.MainActivity
import com.sunul.sunul.presentation.prologue.PrologueActivity

class IntroActivity : BindingActivity<ActivityIntroBinding>(R.layout.activity_intro) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.testBtn.setOnClickListener {
            val intent = Intent(this, PrologueActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }
    }
}