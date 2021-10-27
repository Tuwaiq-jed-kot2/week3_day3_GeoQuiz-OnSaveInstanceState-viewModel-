package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import android.view.Gravity
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalStateException


private const val KEY_INDEX = "index"

class MainActivity : AppCompatActivity() {
// هنا dclear
    private lateinit var falseButton:Button
    private lateinit var trueButton:Button

    private lateinit var nextButton:Button
    private lateinit var questionTextView:TextView
    private lateinit var previousButton: Button
    private lateinit var wroteTextView : TextView




   private val TAG = "Main_Activity"

   private val quizViewModel by lazy {ViewModelProvider(this).get(QuizViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG,"onCreate()")


         //هنا يعطيني الداتا اللي مخزنه في bundle
        val currentIndex =  savedInstanceState?.getInt(KEY_INDEX) ?: 0
        Log.d(TAG,"bundel : $currentIndex")
        quizViewModel.currentIndex = currentIndex


        // provider  هي الريجستر اللي مسؤوله عن كلاس الفيو مودل مثل  حذف بيانات من الميموري
       //owner اللي هو mainactivity

        Log.d(TAG,"hi i'm viewModel from mainActivity $quizViewModel")

        // هنا inshlized
        falseButton = findViewById(R.id.false_button)
        trueButton = findViewById(R.id.true_button)
        nextButton = findViewById(R.id.next_question)
        questionTextView = findViewById(R.id.question_Tv)
        previousButton = findViewById(R.id.previous_question)
        wroteTextView = findViewById(R.id.WroteTV)

        falseButton.setOnClickListener {

             checkAnswer(false)

        }

        trueButton.setOnClickListener {

             checkAnswer(true)


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
             quizViewModel.prviousQuestion()
            updateQuestion()
        }

        updateQuestion()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG,"a value has been saved")
        outState.putInt(KEY_INDEX,quizViewModel.currentIndex)
        //outState.putInt(KEY_INDEX,quizViewModel.currentQuestionText)

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

    private fun updateQuestion(){      //هذي الطريقة عشان اوصل لاول سؤال
        val questionTextResId =  quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
        val wroteTView = quizViewModel.currentQuestionWrote
        wroteTextView.setText(wroteTView)
    }

    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer =  quizViewModel.currentQuestionAnswer

        if (userAnswer == correctAnswer){

            val toast = Toast.makeText(this,R.string.correct_toast,Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP,0,100)
            toast.show()



            }else{
            val toast = Toast.makeText(this,R.string.incorrect_toast,Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP,0,100)
            toast.show()



        }
    }
}

