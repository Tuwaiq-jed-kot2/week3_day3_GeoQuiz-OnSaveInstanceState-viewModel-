package com.example.geoquiz.com.example.geoquiz

import androidx.annotation.StringRes

data class  Question ( @StringRes val textResId :Int,val answer:Boolean){
    //تتاكد ان الريسورس موجود ويتاكد اليوزر يعطي فقط سترنق
    //حطينا انتقر لان الريسورس يستقبل رقم
}