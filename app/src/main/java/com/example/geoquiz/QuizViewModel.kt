package com.example.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel


private const val TAG = "Main_Activity"

class QuizViewModel: ViewModel() {

    private val questionBank = listOf(
        Question(R.string.first_question,true,R.string.wroteTV1),
        Question(R.string.second_question,false,R.string.wroteTV2),
        Question(R.string.third_question,false,R.string.wroteTV3) ,
        Question(R.string.fourth_question,true,R.string.wroteTV4),
        Question(R.string.fifth_question,true,R.string.wroteTV5)

    )

      var currentIndex = 0



    val currentQuestionText : Int
                    get() = questionBank[currentIndex].textResId

    val currentQuestionAnswer : Boolean
                    get() = questionBank[currentIndex].answer

    val currentQuestionWrote : Int
        get() = questionBank[currentIndex].wroteTheQuestion

//    fun questionView(){
//        currentIndex = (currentIndex + 1) % questionBank.size
//    }

    fun nextQuestion(){
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun prviousQuestion(){
        currentIndex = (currentIndex - 1) % questionBank.size
    }

}