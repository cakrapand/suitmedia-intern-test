package com.example.suitmedia.ui.third

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suitmedia.adapter.LoadingStateAdapter
import com.example.suitmedia.adapter.UserListAdapter
import com.example.suitmedia.databinding.ActivityThirdBinding
import com.example.suitmedia.ui.MainViewModel
import com.example.suitmedia.ui.ViewModelFactory
import com.example.suitmedia.ui.second.SecondActivity
import kotlin.math.log

class ThirdActivity : AppCompatActivity() {

    private var _activityThirdBinding: ActivityThirdBinding? = null
    private val binding get() = _activityThirdBinding!!

    private val mainViewModel: MainViewModel by viewModels {
        ViewModelFactory.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _activityThirdBinding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val layoutManager = LinearLayoutManager(this)
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        val adapter = UserListAdapter{
            val resultIntent = Intent()
            resultIntent.putExtra(SecondActivity.EXTRA_SELECTED_NAME, "${it.firstName} ${it.lastName}")
            setResult(SecondActivity.RESULT_OK, resultIntent)
            finish()
        }

        binding.swipeRefresh.apply {
            setOnRefreshListener {
                mainViewModel.getUser()
                isRefreshing = false
            }
        }

        binding.btnBack.setOnClickListener { finish() }

        binding.rvListUser.apply {
            this.layoutManager = layoutManager
            addItemDecoration(itemDecoration)
            this.adapter = adapter.withLoadStateFooter(footer = LoadingStateAdapter{adapter.retry()})
        }

        mainViewModel.listUser.observe(this){
            adapter.submitData(lifecycle, it)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _activityThirdBinding = null
    }
}