package com.example.geoquiz


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"

class MainActivity : AppCompatActivity() {
    //Widgets "Views"
    private lateinit var questionTextView: TextView
    private lateinit var scoreTextView: TextView
    private lateinit var falseButton: Button
    private lateinit var trueButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var previousButton: ImageButton
    private lateinit var cheatButton: Button

    //Attributes
    private val quizViewModel by lazy {
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //getting the value in the bundle
        val currentIndex = savedInstanceState?.getInt(KEY_INDEX) ?: 0
        quizViewModel.currentIndex = currentIndex

        //linking the widget "activity_main.xml" with this class "MainActivity.kt" using their 'id'
        questionTextView = findViewById(R.id.questionTextView)
        scoreTextView = findViewById(R.id.scoreTextView)
        trueButton = findViewById(R.id.trueButton)
        falseButton = findViewById(R.id.falseButton)
        nextButton = findViewById(R.id.nextQuestion)
        previousButton = findViewById(R.id.previousQuestion)


        trueButton.setOnClickListener {
            checkAnswer(true)

        }
        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        questionTextView.setOnClickListener {
            quizViewModel.nextQuestion()
            updateQuestion()
        }

        nextButton.setOnClickListener {
            quizViewModel.nextQuestion()
            updateQuestion()
        }

        previousButton.setOnClickListener {
            quizViewModel.previousQuestion()
            updateQuestion()
        }

        updateQuestion()
    }

    //Save bundle
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX, quizViewModel.currentIndex)
    }

    private fun updateQuestion() {
        val questionTextRedId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextRedId)
        isQuestionAnswered()
    }

    private fun isQuestionAnswered() {
        trueButton.isEnabled = !quizViewModel.isCurrentQuestionAnswered
        falseButton.isEnabled = !quizViewModel.isCurrentQuestionAnswered
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val toastMessage: Int
        if (userAnswer == correctAnswer){
            toastMessage = R.string.correct_toast
            questionBeenAnswered()
        }else{
            toastMessage = R.string.Incorrect_toast
            questionBeenAnswered()
        }
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
    }

    private fun questionBeenAnswered(){
        quizViewModel.isCurrentQuestionAnswered = true
        isQuestionAnswered()
    }
}