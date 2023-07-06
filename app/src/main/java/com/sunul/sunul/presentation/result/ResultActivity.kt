package com.sunul.sunul.presentation.result

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sunul.sunul.R
import com.sunul.sunul.binding.BindingActivity
import com.sunul.sunul.databinding.ActivityResultBinding
import com.sunul.sunul.presentation.joblist.JobListActivity
import com.sunul.sunul.presentation.prologue.PrologueActivity

class ResultActivity : BindingActivity<ActivityResultBinding>(R.layout.activity_result) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.testBtn.setOnClickListener {
            val intent = Intent(this, JobListActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }
    }
}