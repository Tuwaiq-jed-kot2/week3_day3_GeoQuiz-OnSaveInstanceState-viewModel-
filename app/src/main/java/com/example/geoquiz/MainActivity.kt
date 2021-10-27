package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider


private const val KEY_INDEX = "index"

class MainActivity : AppCompatActivity() {

    private lateinit var falseButton: Button
    private lateinit var trueButton: Button
    private lateinit var nextButton: Button
    private lateinit var backButton: Button
    private lateinit var questionTextView: TextView
    private lateinit var instructorTextView: TextView


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
        backButton = findViewById(R.id.back_button)
        questionTextView = findViewById(R.id.question_Tv)
        instructorTextView =findViewById(R.id.instructor_Tv)





        falseButton.setOnClickListener {

            checkAnswer(false)

        }


        trueButton.setOnClickListener {

            checkAnswer(true)

        }

        nextButton.setOnClickListener {
            if(!quizViewModel.questionBankSizeMinus){
                quizViewModel.nextQuestion()

                updateQuestion()
                updateName()
            }
        }

        backButton.setOnClickListener {
            if(quizViewModel.currentIndex !== 0) {
                quizViewModel.backQuestion()
                updateQuestion()
                updateName()
            }
        }


        questionTextView.setOnClickListener {

            quizViewModel.nextQuestion()

            updateQuestion()
            updateName()
        }

        instructorTextView.setOnClickListener {
            updateName()
        }

        updateName()

        updateQuestion()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX,quizViewModel.currentIndex)

    }


    private fun updateQuestion(){
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }


    private fun updateName(){
        val nameTextRecId = quizViewModel.currentQuestionInstructor
        instructorTextView.setText(nameTextRecId)
    }



    private fun checkAnswer(userAnswer:Boolean){

        val correctAnswer = quizViewModel.currentQuestionAnswer

        if(userAnswer == correctAnswer){

            Toast.makeText(this,R.string.correct_toast,Toast.LENGTH_LONG).show()
        }else{

            Toast.makeText(this,R.string.incorrect_toast,Toast.LENGTH_LONG).show()

        }

    }


}