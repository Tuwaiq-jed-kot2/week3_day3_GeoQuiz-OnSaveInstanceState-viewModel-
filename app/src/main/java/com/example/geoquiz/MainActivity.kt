package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlin.math.log
private const val KEY_INDEX = "index"
private const val  KEY_QU = "question"

class MainActivity : AppCompatActivity() {

   private lateinit var falseButton:Button
   private lateinit var trueButton: Button
   private lateinit var nextButton: Button
   private lateinit var questionTextView:TextView
   private lateinit var backButton:TextView
    private lateinit var QustionInstructerTextView :TextView


    val TAG = "Main_Activity"
  private  val QuizViewModel by lazy {ViewModelProvider(this).get(QuizViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG,"onCreate()")
        val currentIndex = savedInstanceState?.getInt(KEY_INDEX)?:0
            val currentQuestion = savedInstanceState?.getInt(KEY_QU)?:0

                Log.d(TAG,"that is the bundle val:$currentQuestion")

           Log.d(TAG,"hi a am viewModel from()")





        falseButton = findViewById(R.id.false_button)
        trueButton = findViewById(R.id.true_button)
        nextButton = findViewById(R.id.next_question)
          questionTextView = findViewById(R.id.question_Tv)

        backButton = findViewById(R.id.back_question)
        QustionInstructerTextView = findViewById(R.id.instructorName)


        falseButton.setOnClickListener {
          checkAnswer(false)
        }
        trueButton.setOnClickListener {
         checkAnswer(true)
        }
        nextButton.setOnClickListener {
            QuizViewModel.nextQuestion()
            updateQuestion()
        }

        backButton.setOnClickListener {
            QuizViewModel.nextQuestion()
            updateQuestion()
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX, QuizViewModel.currentIndex)
        outState.putInt(KEY_INDEX,QuizViewModel.currentQuestionText)

    }


    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"oneStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy")
    }

    private fun updateQuestion(){

        val questionTextResId = QuizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
        val questionInstructer = QuizViewModel.instructorName
        QustionInstructerTextView.setText(questionInstructer)
    }
    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer = QuizViewModel.currentuestionAnswer

        if (userAnswer == correctAnswer) {
            Toast.makeText(this,R.string.correct_toast,Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this,R.string.incorrect_toast,Toast.LENGTH_LONG).show()
        }
    }
}