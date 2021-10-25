package com.arsa.muse.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.arsa.muse.R
import com.arsa.muse.databinding.ActivityOverviewScreen3Binding
import com.arsa.muse.model.Data
import com.arsa.muse.viewmodel.ActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OverviewScreen : AppCompatActivity() {
    private lateinit var activityBinding: ActivityOverviewScreen3Binding
    lateinit var data: Data
    val viewModel:ActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityBinding =
            DataBindingUtil.setContentView<ActivityOverviewScreen3Binding>(this, R.layout.activity_overview_screen3)
        activityBinding.activity = this
        activityBinding.viewModel = viewModel
        activityBinding.lifecycleOwner = this
        val bundle: Bundle? = intent.extras
        bundle?.let {
            bundle.apply {
                data = get("data") as Data
            }
        }
        activityBinding.webView.loadDataWithBaseURL(null,data.content,"text/html","utf-8",null)

    }
}