package com.example.geoquiz

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalStateException

private const val KEY_INDEX : String ="INDEX"

class MainActivity : AppCompatActivity() {

    private lateinit var falseButton: Button
    private lateinit var trueButton: Button
    private lateinit var questionTextView : TextView
    private lateinit var questionAuthor : TextView
    private lateinit var preArrow : ImageView
    private lateinit var nextArrow : ImageView


    private val QuizViewModel by lazy { ViewModelProvider(this).get(QuizViewModel::class.java) }
    private val TAG = "Main_Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //to save current question
        val currentIndex = savedInstanceState?.getInt(KEY_INDEX) ?:0
        QuizViewModel.currentIndex = currentIndex


        falseButton = findViewById(R.id.false_button)
        trueButton = findViewById(R.id.true_button)
        questionTextView = findViewById(R.id.question_Tv)
        questionAuthor = findViewById(R.id.question_author)
        preArrow = findViewById(R.id.pre_arrow)
        nextArrow = findViewById(R.id.next_arrow)


        var tBtn = false
        var fBtn = false


        falseButton.setOnClickListener {
            checkAnswer(false)
            tBtn = false
            fBtn = true

        }

        trueButton.setOnClickListener {
            checkAnswer(true)
            tBtn = true
            fBtn = false
        }


        questionTextView.setOnClickListener {
            QuizViewModel.nextQuestion()
            updateQuestion()
        }

        preArrow.setOnClickListener {

            try {
                if (tBtn || fBtn){
                    QuizViewModel.preQuestion()
                    updateQuestion()
                    tBtn = false
                    fBtn = false
                }
                else{
                    Toast.makeText(this, R.string.select_toast, Toast.LENGTH_SHORT).show()
                }
            }catch (e : ArrayIndexOutOfBoundsException){
                Toast.makeText(this, R.string.out_toast, Toast.LENGTH_SHORT).show()
            }

        }

        nextArrow.setOnClickListener {

            if (tBtn || fBtn){
                QuizViewModel.nextQuestion()
                updateQuestion()
                tBtn = false
                fBtn = false
            }
            else{
                Toast.makeText(this, R.string.select_toast, Toast.LENGTH_SHORT).show()
            }

        }

        updateQuestion()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX,QuizViewModel.currentIndex)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume")
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
        val authorTextResId = QuizViewModel.currentAuthor

        questionTextView.setText(questionTextResId)
        questionAuthor.setText(authorTextResId)
    }

    private fun checkAnswer(userAnswer : Boolean) {

        val correctAnswer =  QuizViewModel.currentQuestionAnswer

        if (correctAnswer == userAnswer){
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show()
        }
    }

}