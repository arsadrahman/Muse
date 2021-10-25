package com.arsa.muse.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Data(@SerializedName("content") val content : String,
                @SerializedName("subtitle") val subtitle : String,
                @SerializedName("title") val title : String,
                @SerializedName("url") val url : String) :Serializable
data class DataList(@SerializedName("card_data") val cardData: List<Data>)

