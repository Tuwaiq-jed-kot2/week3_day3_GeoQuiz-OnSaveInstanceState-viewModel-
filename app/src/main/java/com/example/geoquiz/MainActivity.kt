package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.room.Index


class MainActivity : AppCompatActivity() {

   private lateinit var falseButton:Button
   private lateinit var trueButton:Button
    private var questIon:String? = ""
    private lateinit var instructorTextView:TextView
    private lateinit var mainViewModel:MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState !=null){
            questIon = savedInstanceState.getString("Question")
            //savedInstanceState.getString("Question")
        }


        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        instructorTextView = findViewById(R.id.textView3)
        falseButton = findViewById(R.id.false_button)
        trueButton = findViewById(R.id.true_button)
        instructorTextView.text = mainViewModel.getInstructorOfQuestion(questIon?: " ")

        falseButton.setOnClickListener {
            Toast.makeText(this,R.string.incorrect_toast,Toast.LENGTH_LONG).show()
        }

        trueButton.setOnClickListener {
            Toast.makeText(this,R.string.correct_toast,Toast.LENGTH_LONG).show()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("Question", getString(R.string.question_text))
        super.onSaveInstanceState(outState)
    }
}