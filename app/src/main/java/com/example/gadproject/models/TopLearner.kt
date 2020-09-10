package com.example.gadproject.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class TopLearner(
    @PrimaryKey
    @SerializedName("name") val name:String,
    @SerializedName("hours") val hours: String,
    @SerializedName("country") val country: String,
    @SerializedName("badgeUrl") val badgeUrl: String
)