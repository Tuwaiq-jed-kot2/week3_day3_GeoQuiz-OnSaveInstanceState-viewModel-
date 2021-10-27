package com.example.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.geoquiz.R


private const val TAG = "MainQuizViewModel"

class Quiz_viewModel : ViewModel() {

    var currentIndex =0

    val currentQuestionText :Int
        get()=questionBank[currentIndex].textResId

    val currentQustionAnwser :Boolean
                            get() = questionBank[currentIndex].answer

    val currentQustionCheckAn :Boolean
        get() = questionBank[currentIndex].isSolved


    val currentWroteBy :Int
        get() = questionBank[currentIndex].wroteBy



    val qustionBankSize :Int
        get()=questionBank.size


//    var IsSolve :Int
//        set(isSolved) = questionBank[currentIndex].isSolved

//    init {
//        Log.d(TAG,"hi there i'm from the viewer model class")
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        Log.d(TAG,"\$_$ you cleared ")
//    }

    private val questionBank = listOf(
        Qusetion(R.string.first_question, true,false, R.string.first_wrote_By),
        Qusetion(R.string.second_question, false, false, R.string.second_wrote_By),
        Qusetion(R.string.third_question,false,false, R.string.third_wrote_By)
    )


 fun nextQustion(){
    currentIndex = (currentIndex + 1) % questionBank.size
}

    fun pervQustion(){
        currentIndex = (currentIndex - 1) % questionBank.size
    }

}