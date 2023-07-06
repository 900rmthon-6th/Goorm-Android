package com.sunul.sunul.presentation.prologue

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sunul.sunul.R
import com.sunul.sunul.binding.BindingActivity
import com.sunul.sunul.databinding.ActivityPrologueBinding
import com.sunul.sunul.presentation.MainActivity

class PrologueActivity : BindingActivity<ActivityPrologueBinding>(R.layout.activity_prologue) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.testBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }
    }
}