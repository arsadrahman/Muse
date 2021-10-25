package com.arsa.muse.viewmodel

import android.provider.ContactsContract
import android.util.Log
import android.widget.ImageView
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.arsa.muse.model.Data
import com.arsa.muse.model.DataList
import com.arsa.muse.repository.Repository
import com.bumptech.glide.Glide
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    var cardOne: MutableLiveData<Data> = MutableLiveData()
    var cardTwo: MutableLiveData<Data> = MutableLiveData()
    var cardThree: MutableLiveData<Data> = MutableLiveData()
    var isLoading:MutableLiveData<Boolean> = MutableLiveData()


    fun getList() = liveData(Dispatchers.IO) {
        isLoading.postValue(true)
        try {
            val list: DataList = repository.getList();
            emit(list)
            cardOne.postValue(list.cardData[0])
            cardTwo.postValue(list.cardData[1])
            cardThree.postValue(list.cardData[2])
            isLoading.postValue(false)

        } catch (ex: Exception) {
            isLoading.postValue(false)
        }

    }

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, url: String?) {
          url?.let { url ->
              Glide.with(view.context).load(url).into(view)
          }
        }
    }
}