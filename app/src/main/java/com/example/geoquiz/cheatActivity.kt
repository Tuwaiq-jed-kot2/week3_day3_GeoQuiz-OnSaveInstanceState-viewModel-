package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

const val EXTRA_ANSWER_SHOWN = "cheater!!"
const val EXTRA_QUESTION_SHOWN = "the question"

class cheatActivity : AppCompatActivity() {

    private lateinit var answer_tv:TextView
    private lateinit var show_answer_button:Button
    private lateinit var show_question:TextView


    var answerIsTrueOrNot = false
    var question=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        answer_tv=findViewById(R.id.answer_tv)
        show_answer_button=findViewById(R.id.show_answer_button)
        show_question=findViewById(R.id.question1_Tv)

        val cheater = intent.getIntExtra(KYE_QUESTION,0)
        answer_tv.setText(cheater)

        answerIsTrueOrNot=intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE_OR_NOT,false)
        question=intent.getIntExtra(KYE_QUESTION,0)

        show_answer_button.setOnClickListener{


            answer_tv.setText(answerIsTrueOrNot.toString())
            setAnswerShownResult()


        }










    }

    fun setAnswerShownResult(){
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN,true)
        }
        setResult(Activity.RESULT_OK,data)
    }

//    fun setQuestionShown(){
//        val data = Intent().apply {
//            getIntExtra(EXTRA_QUESTION_SHOWN,0)
//        }
//        setResult(Activity.RESULT_OK,data)
//    }
}