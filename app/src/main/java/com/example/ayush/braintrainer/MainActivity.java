package com.example.ayush.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    class Question
    {
        String ques,opt1,opt2,opt3,opt4;
        int ans,newAnswer;

        public Question(String q, String o1, String o2, String o3, String o4, int a)
        {
            
            ques=q;
            opt1=o1;
            opt2=o2;
            opt3=o3;
            opt4=o4;
            ans=a;

        }
        public int getAns()
        {
            return ans;
        }
    }
    Question question[]=new Question[5];

    int quesno;
    TextView questionTextView;
    TextView op1,op2,op3,op4;
    int totalques;
    int correctques;
    TextView scoreTextView;
    TextView resultTextView;
    TextView timerTextView;
    GridLayout gridLayout;
    Button button;
    public void updateQuestion(int i) {
        questionTextView.setText(question[i].ques);
        op1.setText(question[i].opt1);
        op2.setText(question[i].opt2);
        op3.setText(question[i].opt3);
        op4.setText(question[i].opt4);
    }

    public void codeStart()
    {
        //Hacktober Fest
        scoreTextView.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.INVISIBLE);
        timerTextView.setVisibility(View.INVISIBLE);
        scoreTextView.setText("0/0");
        totalques=0;
        correctques=0;
        quesno=0;
    }

    public void ansClick(View view)
    {
        resultTextView=findViewById(R.id.resultTextView);

        if (Integer.parseInt((String) view.getTag()) == question[quesno - 1].ans)
        {
            correctques=correctques+1;
            resultTextView.setText("Correct :)");
        }
        else
        {
            resultTextView.setText("Wrong :(");
        }

        resultTextView.setVisibility(View.VISIBLE);
        totalques++;
        updateQuestion(quesno);
        quesno++;
        scoreTextView.setText(Integer.toString(correctques) + "/" + Integer.toString(totalques));
        if(quesno==5)
            quesno=1;
    }


    public void onClick(View view)
    {

        button.setVisibility(View.INVISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        timerTextView.setVisibility(View.VISIBLE);

        new CountDownTimer(20100,1000) {
            @Override
            public void onTick(long l)
            {
                if(l>10000)
                    timerTextView.setText(Integer.toString((int)l/1000)+"s");
                else
                    timerTextView.setText("0"+Integer.toString((int)l/1000)+"s");
            }

            @Override
            public void onFinish()
            {

                Toast.makeText(MainActivity.this, "Congratulations on completing the test. You scored " + correctques + "/" + totalques , Toast.LENGTH_LONG).show();
                button.setVisibility(View.VISIBLE);
                questionTextView.setText("Click start to begin");
                codeStart();

            }
        }.start();
        updateQuestion(quesno);
        quesno++;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question[0]=new Question("17 + 33 =","30","80","50","60",3);
        question[1]=new Question("13 + 56 =","79","69","93","76",2);
        question[2]=new Question("18 x 6 =","108","111","128","98",1);
        question[3]=new Question("186 / 62 = ","1","7","4","3",4);
        question[4]=new Question("310 - 92 =","208","218","292","228",2);
        resultTextView=findViewById(R.id.resultTextView);
        questionTextView=findViewById(R.id.questionTextView);
        op1=findViewById(R.id.op1);
        op2=findViewById(R.id.op2);
        op3=findViewById(R.id.op3);
        op4=findViewById(R.id.op4);
        scoreTextView=findViewById(R.id.scoreTextView);
        gridLayout= findViewById(R.id.gridLayout);
        timerTextView=findViewById(R.id.timerTextView);
        button=findViewById(R.id.button);
        codeStart();

    }
}
