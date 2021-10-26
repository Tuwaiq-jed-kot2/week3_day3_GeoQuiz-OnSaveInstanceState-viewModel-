package com.example.geoquiz
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalStateException
import kotlin.math.log

//all capital
private const val KEY_INDEX="index"



class MainActivity : AppCompatActivity() {
    private lateinit var trueButton:Button
    private lateinit var falseButton:Button
    private lateinit var nextButton:Button
    private lateinit var backButton:Button
    private lateinit var questinTextView:TextView
    private lateinit var me:TextView

    private var currevtIndex = 0
    //tag
    val TAG = "main_Activty"
    private val quizViewModelv by lazy {ViewModelProvider(this).get(QuizViewModel::class.java)}

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate()")
        //try ?
        val currentIdex= savedInstanceState?.getInt(KEY_INDEX)?:0
        quizViewModelv.currevtIndex=currevtIndex

        Log.d(TAG,"hi im proider$quizViewModelv()")
        questinTextView= findViewById(R.id.next_question)
        questinTextView= findViewById(R.id.back_quetion)



        trueButton = findViewById(R.id.true_Botton)
        //trueButton.isClickable(true);
        falseButton = findViewById(R.id.false_Button)


        nextButton = findViewById(R.id.next_question)
        questinTextView = findViewById(R.id.question_textVeiw)
        backButton = findViewById(R.id.back_quetion)
        questinTextView = findViewById(R.id.question_textVeiw)
        me = findViewById(R.id.question_textVeiw)



        //logic
        trueButton.setOnClickListener {
            //tost
            checkAnsewr(true)
        }
        falseButton.setOnClickListener {
            checkAnsewr(false)
        }
        nextButton.setOnClickListener {
            quizViewModelv.nextQuestion()
            ubdateQuestion()
        }
        questinTextView.setOnClickListener {
            quizViewModelv.nextQuestion()
            ubdateQuestion()
        }
        backButton.setOnClickListener {
            quizViewModelv.backQuestoin()

            restoreQuestion()
        }

        ubdateQuestion()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX,quizViewModelv.currntQuestionText)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart()")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause()")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop()")
    }
    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")
    }



    private fun ubdateQuestion() {
        val questionTextResId = quizViewModelv.currntQuestionText
        questinTextView.setText(questionTextResId)
    }
    private fun restoreQuestion() {
        val questionTextResId = quizViewModelv.currntQuestionText
        questinTextView.setText(questionTextResId)
    }
    private fun checkAnsewr(userAnswer: Boolean) {
        //flag
        //Log.d(TAG,"im from check answer :",IllegalStateException())
        //break point

        val  correctAnswer = quizViewModelv.currentQuestionAnswer
        if (userAnswer == correctAnswer) {
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_LONG).show()

        } else {
            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_LONG)
                .show()
        }
    }
}
