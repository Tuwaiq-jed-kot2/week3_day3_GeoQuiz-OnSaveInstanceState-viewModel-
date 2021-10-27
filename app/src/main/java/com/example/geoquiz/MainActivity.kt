import com.example.geoquiz.QuizViewModel
import com.example.geoquiz.R



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

private const val key_index = "index"




class MainActivity : AppCompatActivity() {

    private lateinit var falseButton:Button
    private lateinit var trueButton:Button
    private lateinit var nextButton: Button
    private lateinit var preButton: Button
    private lateinit var questionTextView:TextView
    private lateinit var TTTextView:TextView

    private val quizViewModel by lazy { ViewModelProvider(this).get(QuizViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        falseButton = findViewById(R.id.false_button)
        trueButton = findViewById(R.id.true_button)
        nextButton=findViewById(R.id.next_question)
        preButton=findViewById(R.id.back_question)
        questionTextView = findViewById(R.id.question_Tv)
        TTTextView=findViewById(R.id.TTextView)



        falseButton.setOnClickListener {
            checkAnswer(true)
            Toast.makeText(this, R.string.incorrect_toast,Toast.LENGTH_SHORT).show()
        }

        trueButton.setOnClickListener {
            checkAnswer(false)
            checkAnswer(userAnswer = quizViewModel.currentQuestionAnswer)

            Toast.makeText(this, R.string.correct_toast,Toast.LENGTH_SHORT).show()
        }
        nextButton.setOnClickListener {
            try{ quizViewModel.nextQuestion()
                updateQuestion()}catch (e:ArrayIndexOutOfBoundsException){
                Toast.makeText(this, R.string.r_toast,Toast.LENGTH_SHORT)
            }
        }

        preButton.setOnClickListener {
            try {
                quizViewModel.preQuestion()
                updateQuestion()
            }catch (e:ArrayIndexOutOfBoundsException){
                Toast.makeText(this, R.string.rr_toast,Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun updateQuestion(){
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)

    } private fun checkAnswer(userAnswer:Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer



        if (userAnswer == correctAnswer){
            val toast = Toast.makeText(this, R.string.correct_toast,Toast.LENGTH_LONG).show()
        }else {
            val toast = Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_LONG).show()
        }


    }}