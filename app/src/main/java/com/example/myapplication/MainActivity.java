package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    Button continueButton;
    Button option1;
    Button option2;
    Button option3;
    Button option4;
    EditText userNumber;                              // use, number = Integer.parseInt("string")
    TextView statusText;
    GridLayout optionArea;
    ArrayList<Integer> options= new ArrayList<Integer>();
    ArrayList<Integer> factors=new ArrayList<Integer>();
    int correctAnswer;
    int factorPostion;
    int wrongAnswer;
    int correctAnswerPosition;
    // handle exeecptions like 1, 0, 2 and negetive numbers
    /*
        Log.i("output","");
        Toast.makeText(MainActivity.this,"good job dude "+ myNumber.getText().toString(), Toast.LENGTH_SHORT).show();
        {textview}.setText(Integer.toString({integer number}) )
       */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton= findViewById(R.id.startButton);
        continueButton =  findViewById(R.id.continueButton);
        statusText =  findViewById(R.id.statusText);
        option1=  findViewById(R.id.option1);
        option2=findViewById(R.id.option2);
        option3=  findViewById(R.id.option3);
        option4= findViewById(R.id.option4);
        optionArea=  findViewById(R.id.optionArea);

    }
    public void gameStart( View view) {
        Random rand = new Random();
        userNumber = findViewById(R.id.userNumber);
        // make sure execptions like one appearing as an option is not a thing
        if(userNumber.getText().toString().equals("")){
            Toast.makeText(MainActivity.this, "Please enter a number.", Toast.LENGTH_SHORT).show();
        }else if(Integer.parseInt(userNumber.getText().toString())<2) {
            Toast.makeText(MainActivity.this, "Number must be greater than 1", Toast.LENGTH_SHORT).show();
        }else{
            startButton.setVisibility(View.INVISIBLE);
            correctAnswerPosition= rand.nextInt(4);
            statusText.setVisibility(View.VISIBLE);
            optionArea.setVisibility(View.VISIBLE);

             int rootNumber= (int) Math.sqrt(Double.parseDouble(userNumber.getText().toString()));
        for (int i =1; i<=rootNumber; i++){
            if ((Integer.parseInt(userNumber.getText().toString())/i)*i == Integer.parseInt(userNumber.getText().toString())){
                factors.add(i);
                if (i!= Integer.parseInt(userNumber.getText().toString())/rootNumber)
                    factors.add(Integer.parseInt(userNumber.getText().toString())/i);
            }
        }

        factorPostion= rand.nextInt(factors.size());
        correctAnswer=factors.get(factorPostion);

            for( int i=0; i<4; i++){
                boolean flag=true;
                if (i==correctAnswerPosition){
                    options.add(correctAnswer);
                }else{
                    while (flag) {
                        if (Integer.parseInt(userNumber.getText().toString())>8 ) {
                            wrongAnswer = rand.nextInt(Integer.parseInt(userNumber.getText().toString()) / 2) + 2;
                        }else {
                            wrongAnswer = rand.nextInt(8) + 2;
                        }
                      flag=false;
                      for(int j=0; j<options.size();j++){
                          if(wrongAnswer==options.get(j))
                              flag= true;
                      }
                      for (int j = 0; j < factors.size(); j++) {
                          if (wrongAnswer == factors.get(j))
                                  flag=true;
                      }
                    }

                    options.add(wrongAnswer);
                }
            }

            option1.setText(Integer.toString(options.get(0)));
            option2.setText(Integer.toString(options.get(1)));
            option3.setText(Integer.toString(options.get(2)));
            option4.setText(Integer.toString(options.get(3)));

        }
    }

    public void chooseAnswer(View view){
        continueButton.setVisibility(View.VISIBLE);
        if(view.getTag().toString().equals(Integer.toString(correctAnswerPosition))){
            statusText.setText("YAY, Correct answer!");
        }else{
            statusText.setText("Oh no, The answer was "+ correctAnswer);
        }

    }

    public void resetGame(View view) {
        factors.clear();
        options.clear();
        continueButton.setVisibility(View.INVISIBLE);
        statusText.setVisibility(View.INVISIBLE);
        userNumber.setText("");
        optionArea.setVisibility(View.INVISIBLE);
        statusText.setText("Choose your Option");
        startButton.setVisibility(View.VISIBLE);
        // reset conditions:

    }
}
