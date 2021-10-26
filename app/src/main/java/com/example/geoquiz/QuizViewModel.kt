package com.example.geoquiz
import androidx.lifecycle.ViewModel



class QuizViewModel:ViewModel() {

      val questionBank = listOf(
        Question(R.string.first_question, answer = true, isSolved = false, wroteBy = "Anas"),
        Question(R.string.second_question, answer = false, isSolved = false, wroteBy = "Anas"),
        Question(R.string.third_question, answer = false, isSolved = false,wroteBy = "Rana"),
        Question(R.string.fourth_question, answer = true, isSolved = false, wroteBy = "Rana"),
        Question(R.string.fifth_question, answer = false, isSolved = false, wroteBy = "Rana"),
        Question(R.string.sixth_question, answer = true, isSolved = false, wroteBy = "Rana"),
        Question(R.string.seventh_question, answer = true, isSolved = false,wroteBy = "Rana"),
        Question(R.string.eighth_question, answer = false, isSolved = false,wroteBy = "Rana"),
        Question(R.string.nine_question, answer = true, isSolved = false,wroteBy = "Rana"),
        Question(R.string.tenth_question, answer = false, isSolved = false,wroteBy = "Rana"),
    )

    var currentIndex = 0
    var currentScore = 0


    val currentQuestionText get() = questionBank[currentIndex].textResId

    val currentQuestionAnswer get() = questionBank[currentIndex].answer

    val currentQuestionWriter get() = questionBank[currentIndex].wroteBy

    fun nextQuestion(){
            currentIndex = (currentIndex+1)%questionBank.size
    }

    fun backQuestion(){
        currentIndex = if (currentIndex == 0) {
            questionBank.lastIndex
        } else
            (currentIndex - 1)
    }

    fun tryAgain(){
        var i = 0
        while (i < questionBank.size){
            questionBank[i].isSolved = false
            i++
        }

        currentIndex = 0
        currentScore = 0
    }

    fun correctScore(){
        questionBank[currentIndex].isSolved = true
        currentScore += 1
    }

    fun wrongScore(){
        questionBank[currentIndex].isSolved = true
        currentScore += -1
    }

}