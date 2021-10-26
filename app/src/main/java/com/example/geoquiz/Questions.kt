package com.example.geoquiz

import androidx.annotation.StringRes

data class Questions(@StringRes val textResId :Int, val answer:Boolean, var isAnswered:Boolean,val writtenBy:String) {

}