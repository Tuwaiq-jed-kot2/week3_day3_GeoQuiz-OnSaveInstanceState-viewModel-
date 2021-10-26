package com.example.geoquiz

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

private const val KEY_INDEX = "index"


class MainActivity : AppCompatActivity() {

    private lateinit var falseButton:Button
    private lateinit var trueButton:Button
    private lateinit var nextButton:Button
    private lateinit var backButton:Button
    private lateinit var questionTv:TextView
    private lateinit var scoreTv:TextView
    private lateinit var answerIc:ImageView
    private lateinit var tryAgainTv:TextView
    private lateinit var tryAgainBtn:Button
    private lateinit var writerTv:TextView

    private val quizViewModel by lazy{ViewModelProvider(this).get(QuizViewModel::class.java)}

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        falseButton = findViewById(R.id.false_button)
        trueButton = findViewById(R.id.true_button)
        nextButton = findViewById(R.id.next_question)
        questionTv = findViewById(R.id.question_tV)
        backButton = findViewById(R.id.back_question)
        scoreTv = findViewById(R.id.score)
        answerIc = findViewById(R.id.answer_ic)
        tryAgainTv = findViewById(R.id.try_again_tv)
        tryAgainBtn = findViewById(R.id.try_again_btn)
        writerTv = findViewById(R.id.writerTv)

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX) ?: 0

        quizViewModel.currentIndex = currentIndex

        scoreTv.text = quizViewModel.currentScore.toString()

        isSolved()

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        nextButton.setOnClickListener {
            if (quizViewModel.questionBank[9].isSolved){
                questionTv.visibility = View.INVISIBLE
                trueButton.visibility = View.INVISIBLE
                falseButton.visibility = View.INVISIBLE
                nextButton.visibility = View.INVISIBLE
                backButton.visibility = View.INVISIBLE
                writerTv.visibility = View.INVISIBLE
                tryAgainTv.visibility = View.VISIBLE
                tryAgainBtn.visibility = View.VISIBLE
            }else
                quizViewModel.nextQuestion()
            updateQuestion()
            answerIc.visibility = View.INVISIBLE
            falseButton.isEnabled = true
            trueButton.isEnabled = true
            val color = Color.WHITE
            falseButton.backgroundTintList = ColorStateList.valueOf(color)
            trueButton.backgroundTintList = ColorStateList.valueOf(color)
            isSolved()
        }

        backButton.setOnClickListener {
            quizViewModel.backQuestion()
            updateQuestion()
            answerIc.visibility = View.INVISIBLE
            isSolved()
        }

        tryAgainBtn.setOnClickListener {
            updateQuestion()
            quizViewModel.tryAgain()

            questionTv.visibility = View.VISIBLE
            trueButton.visibility = View.VISIBLE
            falseButton.visibility = View.VISIBLE
            nextButton.visibility = View.VISIBLE
            backButton.visibility = View.VISIBLE
            writerTv.visibility = View.VISIBLE
            tryAgainTv.visibility = View.INVISIBLE
            tryAgainBtn.visibility = View.INVISIBLE

            falseButton.isEnabled = true
            trueButton.isEnabled = true
            val color = Color.WHITE
            falseButton.backgroundTintList = ColorStateList.valueOf(color)
            trueButton.backgroundTintList = ColorStateList.valueOf(color)

            scoreTv.text = quizViewModel.currentScore.toString()
        }

        updateQuestion()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(KEY_INDEX , quizViewModel.currentIndex)
        outState.putInt(KEY_INDEX,quizViewModel.currentQuestionText)

    }


    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        val writer = quizViewModel.currentQuestionWriter
        questionTv.setText(questionTextResId)
        writerTv.setText("Wrote By: $writer")
    }

    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer = quizViewModel.currentQuestionAnswer
        if (correctAnswer == userAnswer){
            quizViewModel.correctScore()
            scoreTv.text = quizViewModel.currentScore.toString()
            answerIc.setImageResource(R.drawable.ic_right)
            answerIc.visibility = View.VISIBLE
            isSolved()
        }else {
            quizViewModel.questionBank[quizViewModel.currentIndex].isSolved = true
            quizViewModel.wrongScore()
            scoreTv.text = quizViewModel.currentScore.toString()
            answerIc.setImageResource(R.drawable.ic_wrong)
            answerIc.visibility = View.VISIBLE
            isSolved()
        }
    }

    private fun isSolved(){
        if (quizViewModel.questionBank[quizViewModel.currentIndex].isSolved){
            falseButton.isEnabled = false
            trueButton.isEnabled = false
            val color = Color.GRAY
            falseButton.backgroundTintList = ColorStateList.valueOf(color)
            trueButton.backgroundTintList = ColorStateList.valueOf(color)
        }
    }
}