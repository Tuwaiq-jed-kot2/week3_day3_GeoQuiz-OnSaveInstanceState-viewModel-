package com.example.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

//private const val TAG = "QuizViewModel"
class QuizViewModel: ViewModel() {

     val questionBank = listOf(
        Question(R.string.question_1, true, false, "Rajwa"),

        Question(R.string.question_2, false, false, "Sara"),

        Question(R.string.question_3, true, false, "Kendah")
    )

    var currentIndex = 0

    var currentQuestionText: Int = 0
        get() = questionBank[currentIndex].textResId

    val currentQuestionAnswer: Boolean
                get() = questionBank[currentIndex].answer

    val questionInstructor: String
                get() = "Instructor name: " + questionBank[currentIndex].instructor

    fun nextQuestion() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun prevQuestion() {
        currentIndex = (currentIndex - 1) % questionBank.size
    }
}