package com.example.suitmedia.ui.second

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.suitmedia.databinding.ActivitySecondBinding
import com.example.suitmedia.ui.third.ThirdActivity

class SecondActivity : AppCompatActivity() {

    private var _activitySecondBinding: ActivitySecondBinding? = null
    private val binding get() = _activitySecondBinding!!

    val launcherThirdActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK && it.data != null) {
            val selectedUser = it.data?.getStringExtra(EXTRA_SELECTED_NAME) ?: ""
            binding.tvSelectedName.text = selectedUser
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _activitySecondBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firstName = intent.getStringExtra(EXTRA_FIRST_NAME) ?: ""
        binding.tvFirstName.text = firstName

        binding.btnBack.setOnClickListener { finish() }

        binding.btnChooseUser.setOnClickListener {
            val intent = Intent(this@SecondActivity, ThirdActivity::class.java)
            launcherThirdActivity.launch(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _activitySecondBinding = null
    }

    companion object{
        const val EXTRA_FIRST_NAME = "extra_first_name"
        const val EXTRA_SELECTED_NAME = "extra_selected_name"
        const val RESULT_OK = 200
    }
}