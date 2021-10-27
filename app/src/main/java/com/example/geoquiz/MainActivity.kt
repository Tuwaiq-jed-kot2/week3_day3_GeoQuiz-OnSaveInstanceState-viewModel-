package com.example.geoquiz
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalStateException


private const val KEY_INDEX = "index"

class MainActivity : AppCompatActivity() {
    private lateinit var falseButton: Button
    private lateinit var trueButton: Button
    private lateinit var nextButton: Button
    private lateinit var previousButton: Button
    private lateinit var questionTextView: TextView

    val TAG = "Main_Activity"


    private val quizViewModel by lazy { ViewModelProvider(this).get(QuizViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate")
        Log.d(TAG, "Hi Iam viewModel from MainActivity ()")

        val currentIndex =savedInstanceState?.getInt(KEY_INDEX,)?:0
        quizViewModel.currentIndex = currentIndex



        falseButton = findViewById(R.id.false_button)
        trueButton = findViewById(R.id.true_button)
        nextButton = findViewById(R.id.next_question)
        previousButton = findViewById(R.id.previous_question)
        questionTextView = findViewById(R.id.question_Tv)




        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        nextButton.setOnClickListener {
            quizViewModel.nextQuestion()
            updateQuestion()
        }

        previousButton.setOnClickListener {
            quizViewModel.previousQuestion()
            updateQuestion()
        }


        questionTextView.setOnClickListener {
            quizViewModel.currentIndex
            updateQuestion()

        }


        updateQuestion()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX,quizViewModel.currentIndex)

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart()")
    }


    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()")

    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause()")
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }


    private fun updateQuestion() {

        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)

    }

    private fun checkAnswer(userAnswer: Boolean) {
        Log.d(TAG,"Im from checkAnswer:", IllegalStateException())
        val correctAnswer = quizViewModel.currentQuestionAnswer


        if (userAnswer == correctAnswer) {
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT)
                .show()
            trueButton.isClickable = false
            falseButton.isClickable = true

        } else {
            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT)
                .show()
            trueButton.isClickable = true
            falseButton.isClickable = true
        }


    }

}






