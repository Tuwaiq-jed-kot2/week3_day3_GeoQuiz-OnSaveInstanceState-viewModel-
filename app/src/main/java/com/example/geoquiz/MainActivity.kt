package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider


private const val KEY_INDEX="index"
private const val KEY_Question="question"

class MainActivity : AppCompatActivity() {
    private lateinit var falseButton: Button
    private lateinit var trueButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView:TextView
    private lateinit var backButton: Button
    private lateinit var questionInstructor:TextView



    val TAG ="Main_Activity"


    private val quizViewModel by lazy { ViewModelProvider(this)
        .get(QuizViewModel::class.java)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG,"onCreate")
        val currentIndex=savedInstanceState?.getInt(KEY_INDEX)  ?: 0
        quizViewModel.currentIndex=currentIndex
        Log.d(TAG,"hi im viewModel from MainActivity $quizViewModel")

        val currentQuestion=savedInstanceState?.getInt(KEY_Question)  ?: 0

        Log.d(TAG,"hi im viewModel from MainActivity $quizViewModel")

        falseButton = findViewById(R.id.false_button)
        trueButton = findViewById(R.id.true_button)
        nextButton = findViewById(R.id.nextButton)
        questionTextView = findViewById(R.id.question_Tv)
        backButton = findViewById(R.id.BACK)
        questionTextView = findViewById(R.id.guestion_wrote)


        falseButton.setOnClickListener {
            checkAnswer(false)

        }

        trueButton.setOnClickListener {
            checkAnswer(true)
        }
        questionTextView.setOnClickListener {
            quizViewModel.nextQuestion()
            updateQuestion()
        }
        nextButton.setOnClickListener {
            quizViewModel.nextQuestion()
            updateQuestion()
        }
        updateQuestion()

        backButton.setOnClickListener {
           quizViewModel.backQuestion()
            updateQuestion()
        }
        updateQuestion()


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
       outState.putInt(KEY_INDEX,quizViewModel.currentIndex)
        outState.putInt(KEY_INDEX,quizViewModel.currentQuestionText)
    }




    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume")
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart")
    }
    override fun onRestart(){

    super.onRestart()
    Log.d(TAG,"onRestart")
}

    override fun onPause(){

        super.onPause()
        Log.d(TAG,"onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy")
    }
    private fun updateQuestion(){
        val questionTextResId= quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
        val questionInstrucorView=quizViewModel.insructionQustionBan
        questionInstructor.setText(questionInstrucorView)
    }

    private fun checkAnswer(userAnswer:Boolean) {


        val correctAnswer = quizViewModel.currentQuestionAnswer

        if (userAnswer == correctAnswer) {
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_LONG).show()
        }
    }

}