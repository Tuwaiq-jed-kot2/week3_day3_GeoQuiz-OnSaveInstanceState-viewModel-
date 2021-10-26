package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalStateException
import kotlin.math.log

private const val KEY_INDEX = "index"

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var backButton: ImageButton
    private lateinit var questionTextView: TextView
    private lateinit var questionInstructorTextView: TextView
    private lateinit var appName: TextView

    private val quizViewModel by lazy {ViewModelProvider(this).get(QuizViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX) ?: 0
        quizViewModel.currentIndex = currentIndex

        val currentQuestion = savedInstanceState?.getInt(KEY_INDEX) ?: 0
        quizViewModel.currentQuestionText = currentQuestion

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        backButton = findViewById(R.id.back_button)
        questionTextView = findViewById(R.id.question_Tv)
        questionInstructorTextView = findViewById(R.id.instructor)
        appName = findViewById(R.id.appName)

        updateQuestion()

        questionTextView.setOnClickListener {
            backButton.isEnabled = true
            quizViewModel.nextQuestion()
            updateQuestion()
        }

        nextButton.setOnClickListener {
            backButton.isEnabled = true
            quizViewModel.nextQuestion()
            trueButton.isClickable = true
            falseButton.isClickable = true
            updateQuestion()
        }

        backButton.setOnClickListener {
            quizViewModel.prevQuestion()
            trueButton.isClickable = true
            falseButton.isClickable = true
            updateQuestion()
        }

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }
    }

    private fun updateQuestion() {
        isSolved()
        if (quizViewModel.currentIndex == 0) {
            backButton.isEnabled = false
        }
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
        val questionInstructor = quizViewModel.questionInstructor
        questionInstructorTextView.setText(questionInstructor)
    }

    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = quizViewModel.currentQuestionAnswer
        if (userAnswer == correctAnswer) {
            quizViewModel.questionBank[quizViewModel.currentIndex].solved = true
                   Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show()
                   trueButton.isClickable = false
                   falseButton.isClickable = false
            } else {
            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show()
            trueButton.isClickable = true
            falseButton.isClickable = true
        }
    }

    private fun isSolved() {
        if (quizViewModel.questionBank[quizViewModel.currentIndex].solved) {
            trueButton.isClickable = false
            falseButton.isClickable = false
        } else {
            trueButton.isClickable = true
            falseButton.isClickable = true
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX, quizViewModel.currentIndex)
        outState.putInt(KEY_INDEX, quizViewModel.currentQuestionText)
    }
}



