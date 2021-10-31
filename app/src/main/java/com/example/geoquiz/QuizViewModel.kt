package com.example.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

//Log tag
private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {

    //Attributes
    private val questionBank = listOf(
        //using the model class Question to create objects
        Question(R.string.firstQuestion, false, false),
        Question(R.string.secondQuestion, false, false),
        Question(R.string.thirdQuestion, true, false),
        Question(R.string.fourthQuestion, true, false),
        Question(R.string.fifthQuestion, true, false),
        Question(R.string.sixthQuestion, true, false),
        Question(R.string.seventhQuestion, false, false),
        Question(R.string.eighthQuestion, true, false),
        Question(R.string.ninthQuestion, false, false),
        Question(R.string.tenthQuestion, true, false)
    )

    //Attributes
    var currentIndex = 0
    val instructorName = R.string.instructorName
    val currentQuestionText: Int
            get() = questionBank[currentIndex].textResId
    val currentQuestionAnswer: Boolean
            get() = questionBank[currentIndex].answer

    var isCurrentQuestionAnswered: Boolean
            get() = questionBank[currentIndex].isAnswered
            set(value) {
                questionBank[currentIndex].isAnswered = value
            }

    //Functions
    fun nextQuestion(){
        currentIndex =
            (currentIndex + 1) % questionBank.size
    }

    fun previousQuestion(){
        if (currentIndex > 0){
            currentIndex = (currentIndex - 1)
        } else if (currentIndex == 0){
            currentIndex = 0
        }
    }

}