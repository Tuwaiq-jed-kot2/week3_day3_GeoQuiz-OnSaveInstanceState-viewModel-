package com.example.geoquiz

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
    /*------------------------------------------------------------------------------*/
class QuizViewModel: ViewModel() {
    /*------------------------------------------------------------------------------*/
    private val questionBank = listOf(
        Question(R.string.first_question, true , "Azzam"),
        Question(R.string.second_question, false , "Azzam"),
        Question(R.string.third_question, false , "Azzam"),
        Question(R.string.forth_question, true , "Azzam"),
        Question(R.string.fifth_question, false , "Azzam")
    )
    var currentIndex = 0
    /*------------------------------------------------------------------------------*/
    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId
    /*------------------------------------------------------------------------------*/
    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer
    /*------------------------------------------------------------------------------*/
    val instructorQuestion get() = questionBank[currentIndex].instructorName
    /*------------------------------------------------------------------------------*/
    fun nextQuestion() {
        currentIndex = (++currentIndex) % questionBank.size

    }
    /*------------------------------------------------------------------------------*/
    fun Back() {
        if (currentIndex > 0) {
            currentIndex = (currentIndex - 1) % questionBank.size
        } else if(currentIndex == 0){
            currentIndex = 0
        }
    }
}
