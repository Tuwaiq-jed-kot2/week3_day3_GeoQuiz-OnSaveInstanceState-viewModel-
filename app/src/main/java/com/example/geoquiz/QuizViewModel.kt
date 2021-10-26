package com.example.geoquiz


import androidx.lifecycle.ViewModel


private const val TAg ="quizViewModel"
class QuizViewModel: ViewModel() {

    private val questionBank = listOf(
        Question(R.string.first_question,false),
        Question(R.string.second_question,false),
        Question(R.string.third_question,true),
        Question(R.string.forth_question,true),
        Question(R.string.fifth_question,false),
        Question(R.string.my_name, true)


        )
    val  currentQuestionAnswer :Boolean
        get()=questionBank[currevtIndex].answer
    var currevtIndex =0
    val currntQuestionText:Int
        get()=questionBank[currevtIndex].textResId


    fun nextQuestion(){
        currevtIndex = (currevtIndex + 1) % questionBank.size
        currevtIndex = (currevtIndex +1) % questionBank.size
    }
    fun backQuestoin(){
        currevtIndex = (currevtIndex -1) % questionBank.size
    }
}