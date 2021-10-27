package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalStateException

private const val KEY_INDEX = "index"


class MainActivity : AppCompatActivity() {
    private lateinit var falseButton:Button
    private lateinit var trueButton:Button
    private lateinit var nextButton:Button
    private lateinit var backButton: Button
    private lateinit var questionTextView:TextView

    val TAG = "Main_Activity"
    private val quizViewModel by lazy { ViewModelProvider(this).get(QuizViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val currentIndex = savedInstanceState?.getInt(KEY_INDEX) ?: 0
        quizViewModel.currentIndex = currentIndex



        falseButton = findViewById(R.id.false_button)
        trueButton = findViewById(R.id.true_button)
        nextButton = findViewById(R.id.next_question)
        backButton = findViewById(R.id.previous)
        questionTextView = findViewById(R.id.question_Tv)

        falseButton.setOnClickListener {
            checkAnswer(userAnswer = false)
        }

        trueButton.setOnClickListener {
            checkAnswer(userAnswer = true)
        }

        nextButton.setOnClickListener {
            quizViewModel.nextQuestion()
            updateQuestion()
        }

        backButton.setOnClickListener {
            quizViewModel.backQuestion()
            updateQuestion()
        }

        questionTextView.setOnClickListener{
            quizViewModel.nextQuestion()
            updateQuestion()
        }
        updateQuestion()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX,quizViewModel.currentIndex)
        outState.putInt(KEY_INDEX,quizViewModel.currentQuestionText)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart()")

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"onRestart())")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy()")
    }

    private fun updateQuestion(){
        val questionTextResId =  quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
        Toast.makeText(this,R.string.show_name_tost,Toast.LENGTH_SHORT).show()
    }

    private fun checkAnswer(userAnswer:Boolean){
        Log.d(TAG, "im from check answer :",IllegalStateException())
        val correctAnswer=quizViewModel.currentQuestionAnswer
        if (userAnswer==correctAnswer){
            Toast.makeText(this,R.string.correct_toast,Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this,R.string.incorrect_tost,Toast.LENGTH_SHORT).show()
        }
    }
}