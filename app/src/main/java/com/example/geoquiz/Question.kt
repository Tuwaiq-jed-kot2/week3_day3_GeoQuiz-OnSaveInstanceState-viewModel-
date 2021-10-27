package com.example.geoquiz

import androidx.annotation.IdRes
import androidx.annotation.StringRes

data class Question(@StringRes val textResId:Int, val answer:Boolean,val name:String)
