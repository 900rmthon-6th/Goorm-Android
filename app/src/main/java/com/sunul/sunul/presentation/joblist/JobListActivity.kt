package com.sunul.sunul.presentation.joblist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sunul.sunul.R
import com.sunul.sunul.binding.BindingActivity
import com.sunul.sunul.databinding.ActivityJobListBinding
import com.sunul.sunul.presentation.intro.IntroActivity
import com.sunul.sunul.presentation.jobdetail.JobDetailActivity
import com.sunul.sunul.presentation.prologue.PrologueActivity

class JobListActivity : BindingActivity<ActivityJobListBinding>(R.layout.activity_job_list) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 검사 다시하기: IntroActivity로 이동, User 정보 null 필요
        binding.testBtn.setOnClickListener {
            val intent = Intent(this, IntroActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }

        binding.cardView.setOnClickListener {
            val intent = Intent(this, JobDetailActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }
    }
}