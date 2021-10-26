package com.example.geoquiz

import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {

    var currentIndex = 0

    private val questionBank = listOf(
        Question(R.string.first_question,true,"Instructor: Abdulghani"),
        Question(R.string.second_question,false,"Instructor: Mohammed"),
        Question(R.string.third_question,false,"Instructor: Ahmed"),
        Question(R.string.fourth_question,true,"Instructor: Hassan"),
        Question(R.string.fifth_question,true,"Instructor: Abdulghani"),
        Question(R.string.six_question,false,"Instructor: Abdulghani"),
        Question(R.string.seven_question,true,"Instructor: Abdulghani")
    )

    val currentQuestionText : Int
        get() = questionBank[currentIndex].textResId

    val currentQuestionAnswer : Boolean
        get() =  questionBank[currentIndex].answer

    val currentAuthor : String
            get() = questionBank[currentIndex].instructor

    fun nextQuestion(){
        currentIndex = (++currentIndex) % questionBank.size
    }

    fun preQuestion(){
        currentIndex = (--currentIndex) % questionBank.size
    }




}