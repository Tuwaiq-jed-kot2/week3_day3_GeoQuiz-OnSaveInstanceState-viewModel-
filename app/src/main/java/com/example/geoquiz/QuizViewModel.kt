package com.example.geoquiz

import com.example.geoquiz.com.example.geoquiz.Question

class QuizViewModel {

    package com.example.geoquiz

    import android.util.Log
    import android.widget.Button
    import android.widget.TextView
    import androidx.lifecycle.ViewModel
    private  const val TAG ="com.example.geoquiz.QuizViewModel"
    class QuizViewModel:ViewModel() {
        private val questionBank = listOf(
            Question(R.string.first_question, true),
            Question(R.string.second_question, false),
            Question(R.string.third_question, false),
            Question(R.string.forth_question, true)
        )

        var currentIndex = 0
        val currentQuestioText: Int
            get() = questionBank[currentIndex].textResId

        val currentQuestionAnswer: Boolean
            get() =questionBank[currentIndex].answer

        fun nextQuestion() {
            currentIndex = (currentIndex + 1) % questionBank.size
        }
    }



}