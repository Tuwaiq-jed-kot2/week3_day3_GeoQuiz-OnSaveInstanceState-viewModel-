package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalStateException

private const val KEY_INDEX="index" //the index refer to the question so this code save the current question also.
class MainActivity : AppCompatActivity() {

    private lateinit var falseButton:Button
    private lateinit var trueButton:Button
    private lateinit var nextButton: Button
    private lateinit var prevButton: Button
    private lateinit var questionTextView: TextView




    val TAG ="Main_Activity"


    private val QuizViewModel by lazy {ViewModelProvider(this).get(QuizViewModel::class.java)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG,"onCreate()")

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX) ?:0
        QuizViewModel.currentIndex=currentIndex
        Log.d(TAG,"bundle:$currentIndex")


        Log.d(TAG,"hi iam viewModel from main Activity $QuizViewModel")

        falseButton = findViewById(R.id.falseButton)
        trueButton = findViewById(R.id.trueButton)
        nextButton= findViewById(R.id.nextButton)
        prevButton=findViewById(R.id.preButton)
        questionTextView = findViewById(R.id.qusetion_textv)



        falseButton.setOnClickListener {
            checkAnswae(false)
        }


        trueButton.setOnClickListener {
            checkAnswae(true)
        }

        nextButton.setOnClickListener {
            QuizViewModel.nextQuestion()
            updateQuestion()
        }
        prevButton.setOnClickListener {
            QuizViewModel.preQuestion()
            updateQuestion()
        }

        questionTextView.setOnClickListener {
            QuizViewModel.nextQuestion()
            updateQuestion()
        }

        updateQuestion()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG,"the last question been saved")
        outState.putInt(KEY_INDEX,QuizViewModel.currentIndex)
    }

    private fun updateQuestion(){
        val questionTextResId =QuizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswae(userAnswer:Boolean){

        val correctAnswer = QuizViewModel.currentQuestionAnswer


        if (userAnswer ==correctAnswer){
            val toast: Toast =Toast.makeText(this,R.string.correct_toast,Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP, 0, 250)
            toast.show()
        }else{
            val toast: Toast = Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP, 0, 250)
            toast.show()
        }
    }
}