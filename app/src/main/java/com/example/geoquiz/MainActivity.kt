package com.example.geoquiz

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

private const val key_index = "index"

class MainActivity : AppCompatActivity() {

    // buttons
   private lateinit var falseButton:Button
   private lateinit var trueButton:Button
   private lateinit var nextButton: ImageButton
   private lateinit var previousButton: ImageButton
   private lateinit var questionTextView:TextView
   private lateinit var score_Tv:TextView
   private lateinit var questionWriter:TextView

   private var score = 0

    val TAG = "MainActivity"

    private val quizViewModel by lazy {ViewModelProvider(this).get(QuizViewModel::class.java)}

    // function
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentIndex = savedInstanceState?.getInt(key_index) ?: 0
        Log.d(TAG,"bundle val : $currentIndex")
        quizViewModel.currentIndex = currentIndex
        Log.d(TAG,"onCreate()")

        Log.d(TAG,"Hi I'm ViewModel from main activity $quizViewModel")

        falseButton = findViewById(R.id.false_button)
        trueButton = findViewById(R.id.true_button)
        nextButton = findViewById(R.id.next_question_button)
        previousButton = findViewById(R.id.previous_question_button)
        questionTextView = findViewById(R.id.question_Tv)
        score_Tv = findViewById(R.id.score_TV)
        questionWriter = findViewById(R.id.question_writer)



        falseButton.setOnClickListener {
            checkAnswer(false)
            updateQuestion()
        }

        trueButton.setOnClickListener {
            checkAnswer(true)
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG," a value been saved")
        outState.putInt(key_index,quizViewModel.currentIndex)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"onRestart()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy()")
    }

    private fun updateQuestion() {
        val questionTextResid = quizViewModel.currentQuestionText
        val currentQuestionWriter = quizViewModel.writtenBy
        questionTextView.setText(questionTextResid)
        questionWriter.setText(currentQuestionWriter)
        falseButton.isEnabled = !quizViewModel.currentQuestionIsAnswered
        trueButton.isEnabled = !quizViewModel.currentQuestionIsAnswered

    }

    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer = quizViewModel.currentQuestionAnswer


        if (userAnswer == correctAnswer){
            val toast = Toast.makeText(this,R.string.correct_toast,Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP,0,0)
            toast.show()
            quizViewModel.currentQuestionIsAnswered = true
            updateScore()
        }else {
            val toast = Toast.makeText(this,R.string.incorrect_toast,Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP,0,0)
            toast.show()

        }

    }

    @SuppressLint("SetTextI18n")
    private fun updateScore () {
        score++

        score_Tv.text = "score: $score/6"
    }
}

