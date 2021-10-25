package com.arsa.muse.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.arsa.muse.R
import com.arsa.muse.databinding.ActivityMainBinding
import com.arsa.muse.model.Data


import com.arsa.muse.viewmodel.ActivityViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var activityBinding: ActivityMainBinding
    val viewModel:ActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityBinding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        activityBinding.activity = this
        activityBinding.viewModel = viewModel
        activityBinding.lifecycleOwner = this
        viewModel.getList().observe(this , Observer { list -> Log.e("Result",list.toString()) })
    }

    fun animateIntent(view: View?,data:Data,num:Int) {
        val intent = Intent(this, OverviewScreen::class.java)
        intent.putExtra("data",data)
        val transitionName = getString(com.arsa.muse.R.string.card)

       lateinit var imageView:ImageView
        when(num){
            1 -> imageView = activityBinding.cardOneIv
            2 -> imageView = activityBinding.cardTwoIv
            3 -> imageView = activityBinding.cardThreeIv
        }
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this,
                    imageView,
                     transitionName
                )

        if (options != null) {
            ActivityCompat.startActivity(this, intent, options.toBundle())
        }
    }


}