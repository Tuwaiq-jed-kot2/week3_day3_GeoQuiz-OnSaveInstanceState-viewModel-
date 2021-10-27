package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider

private const val KEY_INDEX = "index"
private const val Tag = "Main_Activity"

class MainActivity : AppCompatActivity() {

    private lateinit var falseButton: Button
    private lateinit var trueButton: Button
    private lateinit var previousButton: ImageView
    private lateinit var nextButton: ImageView
    private lateinit var questionTextView: TextView
    private lateinit var instructorName: TextView
    private val QuizViewModel by lazy { ViewModelProvider(this).get(QuizViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(Tag, "onCreate()")
        Log.d(Tag, "hi i'm view Model from main Activity $QuizViewModel")

        falseButton = findViewById(R.id.false_button)
        trueButton = findViewById(R.id.true_button)
        nextButton = findViewById(R.id.nextButton)
        previousButton = findViewById(R.id.previousButton)
        questionTextView = findViewById(R.id.question_Tv)
        instructorName = findViewById(R.id.instructorName)

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
        questionTextView.setOnClickListener {
            QuizViewModel.nextQuestion()
            updateQuestion()
        }
        previousButton.setOnClickListener {
            QuizViewModel.previousQuestion()
            updateQuestion()
        }




        val actionBar: ActionBar? = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#B8CDCD"))
        actionBar!!.setBackgroundDrawable(colorDrawable)

        updateQuestion()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX, QuizViewModel.currentIndex)
        outState.putInt(KEY_INDEX, QuizViewModel.currentQuestionText)
    }

    override fun onStart() {
        super.onStart()
        Log.d(Tag, "onStart() ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(Tag, "onResume() ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(Tag, "onRestart() ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(Tag, "onPause() ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(Tag, "onStop() ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(Tag, "onDestroy() ")
    }

    private fun updateQuestion() {

        val questionTextResId = QuizViewModel.currentQuestionText

        questionTextView.setText(questionTextResId)

    }

    private fun checkAnswer(userAnswer: Boolean) {

        val correctAnswer = QuizViewModel.currentQuestionAnswer

        if (userAnswer == correctAnswer) {
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_LONG).show()
        }
    }
}
