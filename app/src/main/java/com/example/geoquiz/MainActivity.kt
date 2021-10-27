package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider



    private const val TAG = "MainActivity"
    private const val KAY_INDEX = "index"

private const val Key_INDEX = "index"

class MainActivity : AppCompatActivity() {

    private lateinit var falseButton: Button
    private lateinit var trueButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView : TextView
    private lateinit var instructorName :TextView


     val TAG ="Main_Activity"

    val quizOfViewModel by lazy {ViewModelProvider(this) .get(QuizViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG,"onCreate()")
        val currntIndex =  savedInstanceState?.getInt(Key_INDEX) ?:0

        quizOfViewModel.currentIndex=currntIndex



        Log.d(TAG,"hi im view model from mainActivity")


        falseButton = findViewById(R.id.false_button)
        trueButton = findViewById(R.id.true_button)
        nextButton=findViewById(R.id.button_next)
        questionTextView=findViewById(R.id.first_question)
        instructorName=findViewById(R.id.instructorName)

        falseButton.setOnClickListener {
            checkAnswer(false)

        }

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        nextButton.setOnClickListener {

            updateQuestion()
        }
    }

    private fun updateQuestion (){
        val questionTextResId =quizOfViewModel.currentIndex
        questionTextView.setText(questionTextResId)
        updateQuestion()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(Key_INDEX,quizOfViewModel.currentIndex)
        outState.putInt(Key_INDEX,quizOfViewModel.currentQuestionText)
    }


    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"onRestart()")
    }
    override fun onPostResume() {
        super.onPostResume()
        Log.d(TAG,"onPostResume()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy()")
    }





    private fun checkAnswer(userAnswer:Boolean){
        Log.d(TAG,"im from check answer:",IllegalStateException( ))

        val correctAnswer = quizOfViewModel.currentQuestionAnswer

        if (userAnswer==correctAnswer){
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show()


        }else{
            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show()
        }

    }




}



