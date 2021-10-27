package com.example.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

private const val TAG = "QuizViewModel"
class QuizViewModel :ViewModel () {

    private val questionBank = listOf(
        Question(R.string.first_question, true),
        Question(R.string.socond_question, false)
    )
    var currentIndex = 0

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResIdRes

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    fun nextQuestion() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }
}