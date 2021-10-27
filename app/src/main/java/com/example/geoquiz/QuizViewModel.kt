package com.example.geoquiz


import android.widget.TextView
import androidx.lifecycle.ViewModel


private const val TAg ="quizViewModel"
class QuizViewModel: ViewModel() {

    private val questionBank = listOf(
        Question(R.string.first_question,false,R.string.first_m),
        Question(R.string.second_question,false,R.string.second_m),
        Question(R.string.third_question,true,R.string.third_m),
        Question(R.string.forth_question,true,R.string.forth_m),
        Question(R.string.fifth_question,false,R.string.fifth_m)


        )

    val  currentQuestionAnswer :Boolean
    get()=questionBank[currevtIndex].answer
    var currevtIndex =0
    val currntQuestionText:Int
    get()=questionBank[currevtIndex].textResId
    val  currentQuestionM
    get() =questionBank[currevtIndex].myName

    fun nextQuestion(){
        currevtIndex = (currevtIndex + 1) % questionBank.size
        currevtIndex = (currevtIndex +1) % questionBank.size
    }
    fun backQuestoin(){
        currevtIndex = (currevtIndex -1) % questionBank.size
    }
}