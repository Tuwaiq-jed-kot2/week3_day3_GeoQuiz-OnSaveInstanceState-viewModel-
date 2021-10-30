package com.example.firstapplicationreplaning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.geoquiz.R

private const val KEY_OF_INDIX="Index of question"
class MainActivity : AppCompatActivity() {
    private lateinit var trueBtn: Button
    private lateinit var falseBtn: Button
    private lateinit var nextBtn: Button
    private lateinit var preBtn: Button
    private lateinit var cheatBtn: Button
    private lateinit var questionTV: TextView
    private lateinit var instructorTV: TextView

    private val quizViewModel by lazy { ViewModelProvider(this).get(QuizViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init_view()
        listeners()
        updateQuestion()

    }


    ///////
    private fun listeners() {
        trueBtn.setOnClickListener {
            Toast.makeText(this,R.string.correct_toast,Toast.LENGTH_LONG).show()
        }
        falseBtn.setOnClickListener {
            Toast.makeText(this,R.string.incorrect_toast,Toast.LENGTH_LONG).show()
        }
        nextBtn.setOnClickListener {
            quizViewModel.nextQuestion()
            updateQuestion()
        }
        preBtn.setOnClickListener {
            quizViewModel.previuosQuestion()
            updateQuestion()
            ///////// week 3 day1 Q 2
            Toast.makeText(this, R.string.pre_btn,Toast.LENGTH_LONG).show()
        }


    }

    override fun onSaveInstanceState(outState: Bundle) {/// bundle
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_OF_INDIX,quizViewModel.currentIndex)
    }


    private fun updateQuestion(){
        val questionId=quizViewModel.currentQuestion
        questionTV.setText(questionId)

        val instructorQuestion=quizViewModel.instructor
        questionTV.setText(instructorQuestion)
    }



    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer=quizViewModel.currentQuestion


        val msg=when(correctAnswer){
            correctAnswer-> R.string.correct_toast
            else -> R.string.incorrect_toast
        }

        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()



    }

    private fun init_view() {
        trueBtn = findViewById(R.id.true_btn)//2
        falseBtn = findViewById(R.id.false_btn)
        nextBtn = findViewById(R.id.nixt_btn)
        preBtn = findViewById(R.id.pre_btn)
        cheatBtn = findViewById(R.id.Answer)
        cheatBtn = findViewById(R.id.first_question)
        instructorTV = findViewById(R.id.instructor_question)
        questionTV = findViewById(R.id.first_question)

    }


}