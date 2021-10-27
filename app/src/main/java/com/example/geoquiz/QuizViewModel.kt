package com.example.geoquiz

import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
class QuizViewModel: ViewModel() {

    private val questionBank = listOf(

        Question(R.string.first_question,true,R.string.first_instructor),
        Question(R.string.second_question,false,R.string.second_instructor),
        Question(R.string.third_question,false,R.string.third_instructor),
        Question(R.string.fourth_question,true,R.string.fourth_instructor),
        Question(R.string.fifth_question,false,R.string.fifth_instructor),
        Question(R.string.sixth_question,true,R.string.sixth_instructor)

    )

    var currentIndex = 0

    val currentQuestionText : Int
        get() = questionBank[currentIndex].textResId

    val currentQuestionAnswer : Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionInstructor
        get() = questionBank[currentIndex].instResId



    val questionBankSizeMinus

        get() = currentIndex==questionBank.size-1

    fun nextQuestion(){
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun backQuestion(){
        currentIndex = (currentIndex - 1) % questionBank.size
    }


}