package com.example.geoquiz

import android.widget.Toast
import androidx.lifecycle.ViewModel

// MOdel view 1

const val TAG ="QuizViewModel"


// 2 Creating quiz view model class

class QuizViewModel:ViewModel (){



    private val questionBank = listOf(
        Question(R.string.first_question, true),
        Question(R.string.second_question,false),
        Question(R.string.third_question, true),
        Question(R.string.forth_question,false),
        Question(R.string.fifth_question, true),

        )
     var currentIndex = 0

    val currentQuestionText:Int
        get() = questionBank [currentIndex ].texResId

    val currentQuestionAnswer:Boolean
        get() = questionBank [currentIndex ].answer


    fun nextQuestion(){

        currentIndex = (++currentIndex) % questionBank.size
    }


    fun previousQuestion(){

        currentIndex = (--currentIndex) % questionBank.size
    }



}