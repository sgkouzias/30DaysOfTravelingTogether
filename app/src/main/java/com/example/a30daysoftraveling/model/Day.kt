package com.example.a30daysoftraveling.model

import androidx.annotation.DrawableRes

data class Day(
    val indicator: String,
    val title: String,
    val idea: String,
    @DrawableRes val image: Int
)