package com.example.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel:ViewModel() {

    private val questionBank = listOf(
        Question(R.string.first_question,false,false,"Eshraq"),
        Question(R.string.second_question,false,false,"Eshraq"),
        Question(R.string.third_question,true,true,"Anas"),
        Question(R.string.forth_question,true,true,"Shuruq"),
        Question(R.string.fifth_question,true,true,"Eshraq"),


    )

     var currentIndex = 0

    val questions:String
    get()=questionBank[currentIndex].stractor

    val textView="by:Eshraq"


    val currentQuestionText : Int
                    get() =questionBank[currentIndex].textResId

    val currentQuestionAnswer:Boolean
                            get()=questionBank[currentIndex].answer
    var currentQuestionCheckAnswer:Boolean = questionBank[currentIndex].isSolved


    fun nextQuestion(){
        currentIndex= (currentIndex+1) % questionBank.size
    }

    fun backQuestion(){
        currentIndex= (currentIndex-1 ) % questionBank.size
    }






}