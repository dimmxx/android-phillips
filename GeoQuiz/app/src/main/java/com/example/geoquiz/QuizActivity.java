package com.example.geoquiz;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    //app/build/intermediates/runtime_symbol_list/<insert build type here>/R.txt

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreviousButton;
    private ImageButton mPreviousImageButton;

    private TextView mQuestionTextView;
    private TextView mProgressTextView;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0;
    private int mCorrectAnswer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        View trueButtonView = findViewById(R.id.true_button);
        View falseButtonView = findViewById(R.id.false_button);
        View nextButtonView = findViewById(R.id.next_button);
        View previousButton = findViewById(R.id.previous_button);

        View previousImageButton = findViewById(R.id.previous_image_button);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mProgressTextView = (TextView) findViewById(R.id.progress_text_view);

        mTrueButton = (Button) trueButtonView;
        mFalseButton = (Button) falseButtonView;
        mNextButton = (Button) nextButtonView;
        mPreviousButton = (Button) previousButton;

        mPreviousImageButton = (ImageButton) previousImageButton;

        updateQuestion();

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                nextQuestion();
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               checkAnswer(false);
               nextQuestion();
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentIndex == 0){
                    mCurrentIndex = mQuestionBank.length - 1;
                }else mCurrentIndex--;
                System.out.println();
                updateQuestion();
            }
        });

        mPreviousImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentIndex == 0){
                    mCurrentIndex = mQuestionBank.length - 1;
                }else mCurrentIndex--;
                System.out.println();
                updateQuestion();
            }
        });
    }

    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getmTextResId();
        mQuestionTextView.setText(question);
        String status = String.valueOf(mCurrentIndex + 1) + "/" + mQuestionBank.length + "/" + mCorrectAnswer;
        mProgressTextView.setText(status);
    }

    private void nextQuestion(){
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
        updateQuestion();
    }

    private void checkAnswer(boolean isButton){

        if(isButton == mQuestionBank[mCurrentIndex].ismAnswerTrue()){
            Toast toast = Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
            mCorrectAnswer++;
        }else {
            Toast toast = Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
        }
    }

}