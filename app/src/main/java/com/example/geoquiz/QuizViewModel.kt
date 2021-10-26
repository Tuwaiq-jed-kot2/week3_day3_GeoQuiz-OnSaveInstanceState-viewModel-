package com.example.geoquiz

import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.ViewModel


private const val Tag="QuizViewModel"
class QuizViewModel:ViewModel() {

    private val questionBank = listOf(

        Question(R.string.First_Question, false,"by BAYAN"),
        Question (R.string.second_question, false,"by BAYAN"),
        Question(R.string.third_question,true,"by BAYAN"),
        Question(R.string.forth_question,true,"by BAYAN")
    )
    private fun
     var currentIndex = 0
      var currentIndex = 0

    val insructionQustionBan:String
    get()=questionBank[currentIndex].instructin

    val currentQuestionText:Int
                 get()=questionBank[currentIndex].textResId
    val currentQuestionAnswer: Boolean
                 get()=questionBank[currentIndex].answer


    fun nextQuestion(){
        currentIndex=(currentIndex+1 )% questionBank.size
    }

    fun backQuestion(){

        currentIndex=(currentIndex-1 )% questionBank.size



    }

}



