package com.example.geoquiz

import androidx.lifecycle.ViewModel



class QuizViewModel:ViewModel() {



    private val questionBank = listOf(
        Question(R.string.first_question, false),
        Question(R.string.second_question, false),
        Question(R.string.third_question, true),
        Question(R.string.fourth_question, true),
        Question(R.string.fifth_question, false),
        Question(R.string.sixth_question, true)
    )
    var currentIndex = 0


    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer


    fun nextQuestion() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun backQuestion() {
        currentIndex = if (currentIndex == 0) {
            questionBank.lastIndex
        } else
            (currentIndex - 1)
    }
}

