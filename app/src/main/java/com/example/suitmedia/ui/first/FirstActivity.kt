package com.example.suitmedia.ui.first

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.suitmedia.databinding.ActivityFirstBinding
import com.example.suitmedia.ui.MainViewModel
import com.example.suitmedia.ui.ViewModelFactory
import com.example.suitmedia.ui.second.SecondActivity

class FirstActivity : AppCompatActivity() {

    private var _activityFirstBinding: ActivityFirstBinding? = null
    private val binding get() = _activityFirstBinding!!

    private val mainViewModel: MainViewModel by viewModels {
        ViewModelFactory.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _activityFirstBinding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCheck.setOnClickListener {
            val str = binding.edPalindrome.text.toString().trim()
            if(mainViewModel.isPalindrome(str)){
                Toast.makeText(this@FirstActivity, "isPalindrome", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this@FirstActivity, "not palindrome", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnNext.setOnClickListener {
            val name = binding.edName.text.toString().trim()
            val intent = Intent(this@FirstActivity, SecondActivity::class.java)
            intent.putExtra(SecondActivity.EXTRA_FIRST_NAME, name)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityFirstBinding = null
    }
}