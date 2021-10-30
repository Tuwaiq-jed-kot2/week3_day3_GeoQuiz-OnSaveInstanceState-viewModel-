package com.example.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel:ViewModel() {

    private val questionBank = listOf(
        Question(R.string.first_question,false,false,"by Anas"),
        Question(R.string.second_question,false,false,"by Eshraq"),
        Question(R.string.third_question,true,true,"by Eshraq"),
        Question(R.string.forth_question,true,true,"by Shuruq"),
        Question(R.string.fifth_question,true,true,"by Roya"),


        )

    var currentIndex = 0
    var isCheater = false


    val questions:String
        get()=questionBank[currentIndex].stractor




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