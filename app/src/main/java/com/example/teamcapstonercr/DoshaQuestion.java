package com.example.teamcapstonercr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class DoshaQuestion extends AppCompatActivity {
    private String currentQuestionID;
    private int vata;
    private int pitta;
    private int kapha;

    ProgressBar progressbar;
    TextView progressText;
    TextView question;
    RadioGroup questionsRadioGroup;
    RadioButton radio1;
    RadioButton radio2;
    RadioButton radio3;
    Button submit_button;
    TextView error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosha_question);

        SharedPreferences prefs = getSharedPreferences("DoshaData", MODE_PRIVATE);
        currentQuestionID = prefs.getString("CurrentQuestionID", "1");
        vata = prefs.getInt("vata", 0);
        pitta = prefs.getInt("pitta", 0);
        kapha = prefs.getInt("kapha", 0);

        String id = "question" + currentQuestionID;
        int i = getResources().getIdentifier(id, "array", getPackageName());
        String[] currentQuestion = getResources().getStringArray(i);
        question = findViewById(R.id.question);
        question.setText(currentQuestion[1]);

        questionsRadioGroup = findViewById(R.id.QuestionsRadioGroup);
        questionsRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radio1) {
                    //Toast.makeText(getApplicationContext(), R.string.vata, Toast.LENGTH_SHORT).show();
                } else if(checkedId == R.id.radio2) {
                    //Toast.makeText(getApplicationContext(), R.string.pitta, Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(getApplicationContext(), R.string.kapha, Toast.LENGTH_SHORT).show();
                }
                error.setText("");
            }
        });

        radio1 = findViewById(R.id.radio1);
        radio1.setText(currentQuestion[2]);

        radio2 =  findViewById(R.id.radio2);
        radio2.setText(currentQuestion[3]);

        radio3 = findViewById(R.id.radio3);
        radio3.setText(currentQuestion[4]);

        error =  findViewById(R.id.error);

        submit_button = findViewById(R.id.submit_button);
        submit_button.setOnClickListener(submitButtonClick);
        updateProgress();
    }


    protected void updateProgress(){
        progressbar = findViewById(R.id.progressbar);
        int numberOfQuestions = Integer.valueOf(getResources().getString(R.string.number_of_questions));
        progressbar.setMax(100);
        int progressPercentage = Integer.valueOf((Integer.valueOf(currentQuestionID) * 100) / numberOfQuestions);
        progressbar.setProgress(progressPercentage);
        progressText = findViewById(R.id.progresstext);
        String finalText = getResources().getString(R.string.question_label_progress) + " " + currentQuestionID + "/" + String.valueOf(numberOfQuestions);
        progressText.setText(finalText);
    }


    protected void nextQuestion(){
        error =  findViewById(R.id.error);
        error.setText("");

        int numberOfQuestions = Integer.valueOf(getResources().getString(R.string.number_of_questions));
        int Qid = Integer.valueOf(currentQuestionID);
        //Log.e("numberOfQuestions", String.valueOf(numberOfQuestions));
        //Log.e("currentQuestionID", String.valueOf(Qid));

        if(Qid < numberOfQuestions) {
            currentQuestionID = String.valueOf(Qid + 1);
            String id = "question" + currentQuestionID;
            int i = getResources().getIdentifier(id, "array", getPackageName());
            String[] currentQuestion = getResources().getStringArray(i);

            question = findViewById(R.id.question);
            question.setText(currentQuestion[1]);

            questionsRadioGroup = findViewById(R.id.QuestionsRadioGroup);
            questionsRadioGroup.clearCheck();

            radio1 =  findViewById(R.id.radio1);
            radio1.setChecked(false);
            radio1.setText(currentQuestion[2]);

            radio2 =  findViewById(R.id.radio2);
            radio2.setChecked(false);
            radio2.setText(currentQuestion[3]);

            radio3 =  findViewById(R.id.radio3);
            radio3.setChecked(false);
            radio3.setText(currentQuestion[4]);

            // submit_button = (Button) findViewById(R.id.submit_button);
            // submit_button.setOnClickListener(submitButtonClick);
            updateProgress();
            //error.setText("Question" + Qid +"/" + numberOfQuestions  +" Vata: " + vata + " - Pitta: "+ pitta + " - Kapha: " + kapha);

        }else{
            // save results in preferences
            SharedPreferences prefs = getSharedPreferences("DoshaData", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("vata", vata);
            editor.putInt("pitta", pitta);
            editor.putInt("kapha", kapha);
            editor.commit();
            // show results
            showResults();
        }
    }

    private void showResults(){
        Intent intent=new Intent(getApplicationContext(),DoshaResult.class);
        startActivity(intent);
        // finish and destroy current activity
        finish();
    }

    private View.OnClickListener submitButtonClick = new View.OnClickListener() {
        public void onClick(View v) {
            int selectedId = questionsRadioGroup.getCheckedRadioButtonId();
            if (selectedId == radio1.getId()) {
                vata++;
                //output.setText("You chose first option");
                // Toast.makeText(getApplicationContext(), R.string.vata, Toast.LENGTH_SHORT).show();
                nextQuestion();
            } else if (selectedId == radio2.getId()) {
                pitta++;
                //output.setText("You chose second option");
                //Toast.makeText(getApplicationContext(), R.string.pitta, Toast.LENGTH_SHORT).show();
                nextQuestion();
            } else if (selectedId == radio3.getId()){
                kapha++;
                //output.setText("You chose third option");
                //Toast.makeText(getApplicationContext(), R.string.kapha, Toast.LENGTH_SHORT).show();
                nextQuestion();
            }else{
                error.setText(R.string.question_mandatory_error);
            }

        }
    };

    @Override
    protected void onPause()
    {
        SharedPreferences prefs = getSharedPreferences("DoshaData", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("vata", vata);
        editor.putInt("pitta", pitta);
        editor.putInt("kapha", kapha);
        editor.commit();

        super.onPause();
    }
    @Override
    protected void onResume()
    {
        SharedPreferences prefs = getSharedPreferences("DoshaData", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("vata", vata);
        editor.putInt("pitta", pitta);
        editor.putInt("kapha", kapha);
        editor.commit();
        super.onResume();
    }


}
