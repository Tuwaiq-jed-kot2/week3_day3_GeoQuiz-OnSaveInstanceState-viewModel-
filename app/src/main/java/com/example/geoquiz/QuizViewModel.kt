package com.example.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel
private const val TAG="QuizViewModel"
class QuizViewModel:ViewModel() {






    val questionBank = listOf(
        Question(R.string.first_question,false,"This question was written by FAISAL"),
        Question(R.string.second_question,false,"This question was written by Ahmed"),
        Question(R.string.third_question,true,"This question was written by Eman"),
        Question(R.string.forth_question,false,"This question was written by anas"),
        Question(R.string.forth_question2,true,"This question was written by some guy"),
        Question(R.string.forth_question3,false,"This question was written by wise man"),
        Question(R.string.forth_question4,true,"This question was written by me"),
        Question(R.string.faith_question,true,"This question was written by me too")



        )
     var currentIndex=0

    val currentQuestionText : Int
        get () = questionBank[currentIndex].textResId



    val currentQuestionAnswer:Boolean
                     get() = questionBank[currentIndex].answer
     fun nextQuestion(){
         currentIndex = (++currentIndex)

     }
    fun preQuestion(){
        currentIndex = (--currentIndex)

    }





}



