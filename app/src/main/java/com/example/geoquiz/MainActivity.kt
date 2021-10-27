package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider


private const val KEY_INDEX ="index"

class MainActivity : AppCompatActivity() {

    private lateinit var falseButton:Button
    private lateinit var trueButton:Button
    private lateinit var nextBtn:Button
    private lateinit var pervBtn:Button
    private lateinit var questionTextView:TextView
    private lateinit var wroteByTextView:TextView

    private val TAG = "Main Activity"

    private val quizViewModel by lazy { ViewModelProvider(this).get(Quiz_viewModel::class.java) }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG,"onCreate function")




        Log.d(TAG,"hi i'm view model from Main activity $quizViewModel")

        falseButton = findViewById(R.id.false_button)
        trueButton = findViewById(R.id.true_button)
        nextBtn = findViewById(R.id.next_qution)
        pervBtn = findViewById(R.id.previous_qution)


        questionTextView = findViewById(R.id.question_Tv)
        wroteByTextView = findViewById(R.id.wroteBy)


        falseButton.setOnClickListener {
            checkAnswer(false)

        }

        trueButton.setOnClickListener {
            checkAnswer(true)

        }

        questionTextView.setOnClickListener{
            quizViewModel.nextQustion()
            updatedQuestion()
        }

        nextBtn.setOnClickListener {
            quizViewModel.nextQustion()
            updatedQuestion()
        }

        pervBtn.setOnClickListener {
         quizViewModel.pervQustion()
         updatedQuestion()
        }

        updatedQuestion()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX,quizViewModel.currentIndex)
        outState.putInt(KEY_INDEX,quizViewModel.currentQuestionText)
        outState.putInt(KEY_INDEX,quizViewModel.currentWroteBy)

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"on start function")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"on resume function")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"stop function")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"on restart function")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"on pause function")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"on destroy function")
    }

    private fun  updatedQuestion(){

        when (quizViewModel.currentIndex) {
            0 -> pervBtn.isEnabled = false
            quizViewModel.qustionBankSize - 1 -> nextBtn.isEnabled = false
            in 1..quizViewModel.qustionBankSize - 2 -> {
                                            pervBtn.isEnabled = true
                                            nextBtn.isEnabled = true
                                            }
        }
        val qustionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(qustionTextResId)

        val wroteByTextResId = quizViewModel.currentWroteBy
        wroteByTextView.setText(wroteByTextResId)
    }

    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer = quizViewModel.currentQustionAnwser

        val mesageResId = if (userAnswer == correctAnswer){
            R.string.correct_toast
        } else{
            R.string.incorrect_toast
        }
        Toast.makeText(this,mesageResId,Toast.LENGTH_LONG).show()
        if(userAnswer == correctAnswer){
           // quizViewModel.currentQustionCheckAn = true
            if(quizViewModel.currentQustionCheckAn){
                trueButton.isEnabled = false
                falseButton.isEnabled = false
            }

        }



    }
}