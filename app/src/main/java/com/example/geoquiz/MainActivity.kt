package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.util.rangeTo
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalStateException

private const val KEY_INDEX = "index"
/*------------------------------------------------------------------------------*/
class MainActivity : AppCompatActivity() {

    private lateinit var falseButton:Button
    private lateinit var trueButton:Button
    private lateinit var imageButton: ImageButton
    private lateinit var imageButton1: ImageButton
    private lateinit var questionTextView:TextView
    private lateinit var instructorQ:TextView
/*------------------------------------------------------------------------------*/

    val TAG = "Main_Activity"

    private val quizViewModel by lazy {  ViewModelProvider(this).get(QuizViewModel::class.java) }
    /*------------------------------------------------------------------------------*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG , "onCreate()")
        /*------------------------------------------------------------------------------*/
        val currntIndex = savedInstanceState?.getInt(KEY_INDEX) ?: 0
        quizViewModel.currentIndex = currntIndex
        /*------------------------------------------------------------------------------*/
        setContentView(R.layout.activity_main)

        /*------------------------------------------------------------------------------*/
        falseButton = findViewById(R.id.false_Button)
        trueButton = findViewById(R.id.true_button)
        imageButton = findViewById(R.id.imageButton)
        imageButton1 = findViewById(R.id.imageButton1)
        questionTextView = findViewById(R.id.question_Tv)
        instructorQ = findViewById(R.id.instructorQ)
        /*------------------------------------------------------------------------------*/

        questionTextView.setOnClickListener {
            quizViewModel.nextQuestion()
            updateQuestion()
        }
        /*------------------------------------------------------------------------------*/
        falseButton.setOnClickListener {
            checkAnswer(false)
        }
        /*------------------------------------------------------------------------------*/
        trueButton.setOnClickListener {
            checkAnswer(true)
        }
        /*------------------------------------------------------------------------------*/
        imageButton.setOnClickListener {
            quizViewModel.nextQuestion()
            updateQuestion()
        }
        /*------------------------------------------------------------------------------*/
        imageButton1.setOnClickListener {
            quizViewModel.Back()
            updateQuestion()
        }
        updateQuestion()
    }
    /*------------------------------------------------------------------------------*/
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX , quizViewModel.currentIndex)
        outState.putInt(KEY_INDEX,quizViewModel.currentQuestionText)
    }
    /*------------------------------------------------------------------------------*/
    override fun onStart() {
        super.onStart()
        Log.d(TAG , "onStart()")
    }
    /*------------------------------------------------------------------------------*/
    override fun onResume() {
        super.onResume()
        Log.d(TAG , "onResume()")
    }
    /*------------------------------------------------------------------------------*/
    override fun onStop() {
        super.onStop()
        Log.d(TAG , "onStop()")
    }
    /*------------------------------------------------------------------------------*/
    override fun onRestart() {
        super.onRestart()
        Log.d(TAG , "onRestart()")
    }
    /*------------------------------------------------------------------------------*/
    override fun onPause() {
        super.onPause()
        Log.d(TAG , "onPause()")
    }
    /*------------------------------------------------------------------------------*/
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG , "onDestroy()")
    }
    /*------------------------------------------------------------------------------*/
    private fun updateQuestion(){
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
        val instructorN = quizViewModel.instructorQuestion
        instructorQ.setText("The instructor wrote the question is: $instructorN")
    }
    /*------------------------------------------------------------------------------*/
    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer = quizViewModel.currentQuestionAnswer
        if (correctAnswer == userAnswer){
            Toast.makeText(this,R.string.correct_toast,Toast.LENGTH_LONG).show()
        }else {
            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_LONG).show()

        }
    }
}