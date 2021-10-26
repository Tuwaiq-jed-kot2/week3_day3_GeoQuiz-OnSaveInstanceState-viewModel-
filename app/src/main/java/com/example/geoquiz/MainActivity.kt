package com.example.geoquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlin.math.log

private const val KEY_INDEX="index"

const val ExtraAnswerisTrueOrFulse="MainActivityExtraAnswer"
//اقدر اسميه اي شي
class MainActivity : AppCompatActivity() {

    private lateinit var falseButton: Button
    private lateinit var trueButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView : TextView
    private lateinit var instructorName : TextView
    private lateinit var cheatButton: Button



    private val TAG ="Main_Activity"

    val quizOfViewModel by lazy { ViewModelProvider(this) .get(QuizViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG,"onCreate()")
        val currentIndex= savedInstanceState?.getInt(KEY_INDEX) ?: 0
        Log.d(TAG, "budle val ")
        quizOfViewModel.currentIndex=currentIndex

        Log.d(TAG,"hi im view model from mainActivity")


        falseButton = findViewById(R.id.false_button)
        trueButton = findViewById(R.id.true_button)
        nextButton=findViewById(R.id.next_question)
        questionTextView=findViewById(R.id.textViewQ)
        instructorName =findViewById(R.id.instructorName)
        cheatButton=findViewById(R.id.showAnswerButton)


        cheatButton.setOnClickListener {

            val intent= Intent(this,CheatActivity::class.java)

            intent.putExtra(ExtraAnswerisTrueOrFulse,quizOfViewModel.currentQuestionAnswer )
            startActivity(intent)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)

        }

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        nextButton.setOnClickListener {
            quizOfViewModel.nextQuestion()
            updateQuestion()
        }

        //Private
        fun updateQuestion(){
            val questionTextResId = quizOfViewModel.currentQuestioText
            questionTextView.setText(questionTextResId)
            Toast.makeText(this,R.string.instructorName,Toast.LENGTH_SHORT).show()

        }


    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX,quizOfViewModel.currentIndex)
        outState.putInt(KEY_INDEX,quizOfViewModel.currentQuestioText)
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

    private fun updateQuestion (){
        val questionTextResId =quizOfViewModel.currentQuestioText
        Log.d(TAG,"test $questionTextResId")
        questionTextView.setText(questionTextResId)

    }



    private fun checkAnswer(userAnswer:Boolean){
        Log.d(TAG,"ia from checkAnswer:",IllegalStateException())
        val correctAnswer = quizOfViewModel.currentQuestionAnswer
//طريقه اكتشاف الايرور
        if (userAnswer==correctAnswer){
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show()


        }else{
            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show()
        }

    }
}