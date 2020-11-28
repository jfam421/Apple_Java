package com.example.apple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvMsg;
    private TextView tvScore;
    private Button btnNewGame;
    private int[] gridCellId=new int[]{0,
            R.id.btn1, R.id.btn2, R.id.btn3,
            R.id.btn4, R.id.btn5, R.id.btn6,
            R.id.btn7, R.id.btn8, R.id.btn9};
    private int rand = (int)(Math.random() * 8);
    public int score = 0;
    private int mistakes = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvMsg = (TextView) findViewById(R.id.game);
        tvScore = (TextView) findViewById(R.id.score);
        btnNewGame = (Button) findViewById(R.id.startGame) ;
        btnNewGame.setOnClickListener(this);

        for (int i=1 ; i <= 9 ; i++) {
            Button btn = (Button)findViewById(gridCellId[i]);
            btn.setOnClickListener(this);
        }
    }
    public void onClick(View v) {
        if (v.getId() == R.id.startGame) {
            Intent game = new Intent(MainActivity.this, MainActivity.class);
            startActivity(game);
        }
        else if (v.getId() == gridCellId[rand]) {
            Toast toast1 = Toast.makeText(getApplicationContext(), "You are winner!", Toast.LENGTH_LONG);
            toast1.show();
            tvMsg.setText("You are winner!");
            score = (score + 10) - mistakes;
            tvScore.setText("Your score: " + score);
            btnNewGame.setText("Continue");
            Button WinButton = (Button)findViewById(gridCellId[rand]);
            WinButton.setText("The Apple!");
            WinButton.setBackgroundColor(Color.parseColor("#fc1303"));

        }
        else {
            Button btnMistake = (Button) findViewById(v.getId());
            btnMistake.setBackgroundColor(Color.parseColor("#ddff00"));
            mistakes +=1;
            if (mistakes == 5){
                tvMsg.setText("You have lost!");
                Toast toast = Toast.makeText(getApplicationContext(), "You have lost!", Toast.LENGTH_LONG);
                toast.show();
                btnNewGame.setText("Restart");Intent game = new Intent(MainActivity.this, MainActivity.class);
            }
        }
    }
}