package com.example.aplikacja_screen;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.lang.*;
import java.util.concurrent.TimeUnit;

import android.widget.Toast;

import com.example.m.aplikacja_screen.R;

public class PlanszaKwadratowa extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
    //w tablicy beda zapisane slowa polskie i ich angielskie odpowiedniki
    String[] pl = {"Dom", "Pies", "Kot", "Biurko"};
    String[] en = {"Home", "Dog", "Cat", "Desk"};

    boolean click1 = false;
    boolean click2 = false;
    boolean click3 = false;
    boolean click4 = false;
    boolean click5 = false;
    boolean click6 = false;
    boolean click7 = false;
    boolean click8 = false;

    //od wątków
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plansza_kwadratowa);

        btn1 = (Button) findViewById(R.id.button10);
        btn2 = (Button) findViewById(R.id.button12);
        btn3 = (Button) findViewById(R.id.button13);
        btn4 = (Button) findViewById(R.id.button31);
        btn5 = (Button) findViewById(R.id.button34);
        btn6 = (Button) findViewById(R.id.button35);
        btn7 = (Button) findViewById(R.id.button36);
        btn8 = (Button) findViewById(R.id.button37);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click1 = true;
                btn1.setText(pl[0]);


                if (click7 == true) {
                    if ((btn1.getText() == pl[0] && btn7.getText() == en[0]) || (btn1.getText() == en[0] && btn7.getText() == pl[0])) {
                        btn1.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setBackgroundResource(R.drawable.button_border_blue);
                        btn1.setClickable(false);
                        btn7.setClickable(false);
                        click1 = false;
                        click7 = false;
                    } else if ((btn1.getText() == pl[1] && btn7.getText() == en[1]) || (btn1.getText() == en[1] && btn7.getText() == pl[1])) {
                        btn1.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setBackgroundResource(R.drawable.button_border_pink);
                        btn1.setClickable(false);
                        btn7.setClickable(false);
                        click1 = false;
                        click7 = false;
                    } else if ((btn1.getText() == pl[2] && btn7.getText() == en[2]) || (btn1.getText() == en[2] && btn7.getText() == pl[2])) {
                        btn1.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setBackgroundResource(R.drawable.button_border_yellow);
                        btn1.setClickable(false);
                        btn7.setClickable(false);
                        click1 = false;
                        click7 = false;
                    } else if ((btn1.getText() == pl[3] && btn7.getText() == en[3]) || (btn1.getText() == en[3] && btn7.getText() == pl[3])) {
                        btn1.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setBackgroundResource(R.drawable.button_border_purple);
                        btn1.setClickable(false);
                        btn7.setClickable(false);
                        click1 = false;
                        click7 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn1.setText(" ");
                                btn7.setText(" ");
                            }
                        }, 2000);
                        click1 = false;
                        click7 = false;
//                        btn7.setBackgroundColor(Color.parseColor("#d8d0d0"));
                    }
                } else if (click2 == true) {
                    if ((btn2.getText() == pl[0] && btn1.getText() == en[0]) || (btn2.getText() == en[0] && btn1.getText() == pl[0])) {
                        btn2.setBackgroundResource(R.drawable.button_border_blue);
                        btn1.setBackgroundResource(R.drawable.button_border_blue);
                        btn1.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click1 = false;
                    } else if ((btn2.getText() == pl[1] && btn1.getText() == en[1]) || (btn2.getText() == en[1] && btn1.getText() == pl[1])) {
                        btn2.setBackgroundResource(R.drawable.button_border_pink);
                        btn1.setBackgroundResource(R.drawable.button_border_pink);
                        btn1.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click1 = false;
                    } else if ((btn2.getText() == pl[2] && btn1.getText() == en[2]) || (btn2.getText() == en[2] && btn1.getText() == pl[2])) {
                        btn2.setBackgroundResource(R.drawable.button_border_yellow);
                        btn1.setBackgroundResource(R.drawable.button_border_yellow);
                        btn1.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click1 = false;
                    } else if ((btn2.getText() == pl[3] && btn1.getText() == en[3]) || (btn2.getText() == en[3] && btn1.getText() == pl[3])) {
                        btn2.setBackgroundResource(R.drawable.button_border_purple);
                        btn1.setBackgroundResource(R.drawable.button_border_purple);
                        btn1.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click1 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn1.setText(" ");
                                btn2.setText(" ");
                            }
                        }, 2000);
                        click2 = false;
                        click1 = false;
                    }
                } else if (click3 == true) {
                    if ((btn3.getText() == pl[0] && btn1.getText() == en[0]) || (btn3.getText() == en[0] && btn1.getText() == pl[0])) {
                        btn3.setBackgroundResource(R.drawable.button_border_blue);
                        btn1.setBackgroundResource(R.drawable.button_border_blue);
                        btn1.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click1 = false;
                    } else if ((btn3.getText() == pl[1] && btn1.getText() == en[1]) || (btn3.getText() == en[1] && btn1.getText() == pl[1])) {
                        btn3.setBackgroundResource(R.drawable.button_border_pink);
                        btn1.setBackgroundResource(R.drawable.button_border_pink);
                        btn1.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click1 = false;
                    } else if ((btn3.getText() == pl[2] && btn1.getText() == en[2]) || (btn3.getText() == en[2] && btn1.getText() == pl[2])) {
                        btn3.setBackgroundResource(R.drawable.button_border_yellow);
                        btn1.setBackgroundResource(R.drawable.button_border_yellow);
                        btn1.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click1 = false;
                    } else if ((btn3.getText() == pl[3] && btn1.getText() == en[3]) || (btn3.getText() == en[3] && btn1.getText() == pl[3])) {
                        btn3.setBackgroundResource(R.drawable.button_border_purple);
                        btn1.setBackgroundResource(R.drawable.button_border_purple);
                        btn1.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click1 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn1.setText(" ");
                                btn3.setText(" ");
                            }
                        }, 2000);
                        click3 = false;
                        click1 = false;
                    }
                } else if (click4 == true) {
                    if ((btn4.getText() == pl[0] && btn1.getText() == en[0]) || (btn4.getText() == en[0] && btn1.getText() == pl[0])) {
                        btn4.setBackgroundResource(R.drawable.button_border_blue);
                        btn1.setBackgroundResource(R.drawable.button_border_blue);
                        btn1.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click1 = false;
                    } else if ((btn4.getText() == pl[1] && btn1.getText() == en[1]) || (btn4.getText() == en[1] && btn1.getText() == pl[1])) {
                        btn4.setBackgroundResource(R.drawable.button_border_pink);
                        btn1.setBackgroundResource(R.drawable.button_border_pink);
                        btn1.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click1 = false;
                    } else if ((btn4.getText() == pl[2] && btn1.getText() == en[2]) || (btn4.getText() == en[2] && btn1.getText() == pl[2])) {
                        btn4.setBackgroundResource(R.drawable.button_border_yellow);
                        btn1.setBackgroundResource(R.drawable.button_border_yellow);
                        btn1.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click1 = false;
                    } else if ((btn4.getText() == pl[3] && btn1.getText() == en[3]) || (btn4.getText() == en[3] && btn1.getText() == pl[3])) {
                        btn4.setBackgroundResource(R.drawable.button_border_purple);
                        btn1.setBackgroundResource(R.drawable.button_border_purple);
                        btn1.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click1 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn1.setText(" ");
                                btn4.setText(" ");
                            }
                        }, 2000);
                        click4 = false;
                        click1 = false;
                    }
                } else if (click5 == true) {
                    if ((btn5.getText() == pl[0] && btn1.getText() == en[0]) || (btn5.getText() == en[0] && btn1.getText() == pl[0])) {
                        btn5.setBackgroundResource(R.drawable.button_border_blue);
                        btn1.setBackgroundResource(R.drawable.button_border_blue);
                        btn1.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click1 = false;
                    } else if ((btn5.getText() == pl[1] && btn1.getText() == en[1]) || (btn5.getText() == en[1] && btn1.getText() == pl[1])) {
                        btn5.setBackgroundResource(R.drawable.button_border_pink);
                        btn1.setBackgroundResource(R.drawable.button_border_pink);
                        btn1.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click1 = false;
                    } else if ((btn5.getText() == pl[2] && btn1.getText() == en[2]) || (btn5.getText() == en[2] && btn1.getText() == pl[2])) {
                        btn5.setBackgroundResource(R.drawable.button_border_yellow);
                        btn1.setBackgroundResource(R.drawable.button_border_yellow);
                        btn1.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click1 = false;
                    } else if ((btn5.getText() == pl[3] && btn1.getText() == en[3]) || (btn5.getText() == en[3] && btn1.getText() == pl[3])) {
                        btn5.setBackgroundResource(R.drawable.button_border_purple);
                        btn1.setBackgroundResource(R.drawable.button_border_purple);
                        btn1.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click1 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn1.setText(" ");
                                btn5.setText(" ");
                            }
                        }, 2000);
                        click5 = false;
                        click1 = false;
                    }
                } else if (click6 == true) {
                    if ((btn6.getText() == pl[0] && btn1.getText() == en[0]) || (btn6.getText() == en[0] && btn1.getText() == pl[0])) {
                        btn6.setBackgroundResource(R.drawable.button_border_blue);
                        btn1.setBackgroundResource(R.drawable.button_border_blue);
                        btn1.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click1 = false;
                    } else if ((btn6.getText() == pl[1] && btn1.getText() == en[1]) || (btn6.getText() == en[1] && btn1.getText() == pl[1])) {
                        btn6.setBackgroundResource(R.drawable.button_border_pink);
                        btn1.setBackgroundResource(R.drawable.button_border_pink);
                        btn1.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click1 = false;
                    } else if ((btn6.getText() == pl[2] && btn1.getText() == en[2]) || (btn6.getText() == en[2] && btn1.getText() == pl[2])) {
                        btn6.setBackgroundResource(R.drawable.button_border_yellow);
                        btn1.setBackgroundResource(R.drawable.button_border_yellow);
                        btn1.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click1 = false;
                    } else if ((btn6.getText() == pl[3] && btn1.getText() == en[3]) || (btn6.getText() == en[3] && btn1.getText() == pl[3])) {
                        btn6.setBackgroundResource(R.drawable.button_border_purple);
                        btn1.setBackgroundResource(R.drawable.button_border_purple);
                        btn1.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click1 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn1.setText(" ");
                                btn6.setText(" ");
                            }
                        }, 2000);
                        click6 = false;
                        click1 = false;
                    }
                } else if (click8 == true) {
                    if ((btn8.getText() == pl[0] && btn1.getText() == en[0]) || (btn8.getText() == en[0] && btn1.getText() == pl[0])) {
                        btn1.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setBackgroundResource(R.drawable.button_border_blue);
                        btn1.setClickable(false);
                        btn8.setClickable(false);
                        click1 = false;
                        click8 = false;
                    } else if ((btn8.getText() == pl[1] && btn1.getText() == en[1]) || (btn8.getText() == en[1] && btn1.getText() == pl[1])) {
                        btn1.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setBackgroundResource(R.drawable.button_border_pink);
                        btn1.setClickable(false);
                        btn8.setClickable(false);
                        click1 = false;
                        click8 = false;
                    } else if ((btn8.getText() == pl[2] && btn1.getText() == en[2]) || (btn8.getText() == en[2] && btn1.getText() == pl[2])) {
                        btn1.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setBackgroundResource(R.drawable.button_border_yellow);
                        btn1.setClickable(false);
                        btn8.setClickable(false);
                        click1 = false;
                        click8 = false;
                    } else if ((btn8.getText() == pl[3] && btn1.getText() == en[3]) || (btn8.getText() == en[3] && btn1.getText() == pl[3])) {
                        btn1.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setBackgroundResource(R.drawable.button_border_purple);
                        btn1.setClickable(false);
                        btn8.setClickable(false);
                        click1 = false;
                        click8 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn1.setText(" ");
                                btn8.setText(" ");
                            }
                        }, 2000);
                        click1 = false;
                        click8 = false;
                    }
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click2 = true;
                btn2.setText(en[2]);

                if (click1 == true) {
                    if ((btn1.getText() == pl[0] && btn2.getText() == en[0]) || (btn1.getText() == en[0] && btn2.getText() == pl[0])) {
                        btn1.setBackgroundResource(R.drawable.button_border_blue);
                        btn2.setBackgroundResource(R.drawable.button_border_blue);
                        btn2.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click2 = false;
                    } else if ((btn1.getText() == pl[1] && btn2.getText() == en[1]) || (btn1.getText() == en[1] && btn2.getText() == pl[1])) {
                        btn1.setBackgroundResource(R.drawable.button_border_pink);
                        btn2.setBackgroundResource(R.drawable.button_border_pink);
                        btn2.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click2 = false;
                    } else if ((btn1.getText() == pl[2] && btn2.getText() == en[2]) || (btn1.getText() == en[2] && btn2.getText() == pl[2])) {
                        btn1.setBackgroundResource(R.drawable.button_border_yellow);
                        btn2.setBackgroundResource(R.drawable.button_border_yellow);
                        btn2.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click2 = false;
                    } else if ((btn1.getText() == pl[3] && btn2.getText() == en[3]) || (btn1.getText() == en[3] && btn2.getText() == pl[3])) {
                        btn1.setBackgroundResource(R.drawable.button_border_purple);
                        btn2.setBackgroundResource(R.drawable.button_border_purple);
                        btn2.setClickable(false);
                        btn1.setClickable(false);
                        click2 = false;
                        click2 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn1.setText(" ");
                                btn2.setText(" ");
                            }
                        }, 2000);

                        click2 = false;
                        click2 = false;
                    }
                } else if (click7 == true) {
                    if ((btn2.getText() == pl[0] && btn7.getText() == en[0]) || (btn2.getText() == en[0] && btn7.getText() == pl[0])) {
                        btn2.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setBackgroundResource(R.drawable.button_border_blue);
                        btn2.setClickable(false);
                        btn7.setClickable(false);
                        click2 = false;
                        click7 = false;
                    } else if ((btn2.getText() == pl[1] && btn7.getText() == en[1]) || (btn2.getText() == en[1] && btn7.getText() == pl[1])) {
                        btn2.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setBackgroundResource(R.drawable.button_border_pink);
                        btn2.setClickable(false);
                        btn7.setClickable(false);
                        click2 = false;
                        click7 = false;
                    } else if ((btn2.getText() == pl[2] && btn7.getText() == en[2]) || (btn2.getText() == en[2] && btn7.getText() == pl[2])) {
                        btn2.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setBackgroundResource(R.drawable.button_border_yellow);
                        btn2.setClickable(false);
                        btn7.setClickable(false);
                        click2 = false;
                        click7 = false;
                    } else if ((btn2.getText() == pl[3] && btn7.getText() == en[3]) || (btn2.getText() == en[3] && btn7.getText() == pl[3])) {
                        btn2.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setBackgroundResource(R.drawable.button_border_purple);
                        btn2.setClickable(false);
                        btn7.setClickable(false);
                        click2 = false;
                        click7 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn2.setText(" ");
                                btn7.setText(" ");
                            }
                        }, 2000);
                        click2 = false;
                        click7 = false;
                    }
                } else if (click3 == true) {
                    if ((btn3.getText() == pl[0] && btn2.getText() == en[0]) || (btn3.getText() == en[0] && btn2.getText() == pl[0])) {
                        btn3.setBackgroundResource(R.drawable.button_border_blue);
                        btn2.setBackgroundResource(R.drawable.button_border_blue);
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click2 = false;
                    } else if ((btn3.getText() == pl[1] && btn2.getText() == en[1]) || (btn3.getText() == en[1] && btn2.getText() == pl[1])) {
                        btn3.setBackgroundResource(R.drawable.button_border_pink);
                        btn2.setBackgroundResource(R.drawable.button_border_pink);
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click2 = false;
                    } else if ((btn3.getText() == pl[2] && btn2.getText() == en[2]) || (btn3.getText() == en[2] && btn2.getText() == pl[2])) {
                        btn3.setBackgroundResource(R.drawable.button_border_yellow);
                        btn2.setBackgroundResource(R.drawable.button_border_yellow);
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click2 = false;
                    } else if ((btn3.getText() == pl[3] && btn2.getText() == en[3]) || (btn3.getText() == en[3] && btn2.getText() == pl[3])) {
                        btn3.setBackgroundResource(R.drawable.button_border_purple);
                        btn2.setBackgroundResource(R.drawable.button_border_purple);
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click2 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn3.setText(" ");
                                btn2.setText(" ");
                            }
                        }, 2000);
                        click3 = false;
                        click2 = false;
                    }
                } else if (click4 == true) {
                    if ((btn4.getText() == pl[0] && btn2.getText() == en[0]) || (btn4.getText() == en[0] && btn2.getText() == pl[0])) {
                        btn4.setBackgroundResource(R.drawable.button_border_blue);
                        btn2.setBackgroundResource(R.drawable.button_border_blue);
                        btn2.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click2 = false;
                    } else if ((btn4.getText() == pl[1] && btn2.getText() == en[1]) || (btn4.getText() == en[1] && btn2.getText() == pl[1])) {
                        btn4.setBackgroundResource(R.drawable.button_border_pink);
                        btn2.setBackgroundResource(R.drawable.button_border_pink);
                        btn2.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click2 = false;
                    } else if ((btn4.getText() == pl[2] && btn2.getText() == en[2]) || (btn4.getText() == en[2] && btn2.getText() == pl[2])) {
                        btn4.setBackgroundResource(R.drawable.button_border_yellow);
                        btn2.setBackgroundResource(R.drawable.button_border_yellow);
                        btn2.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click2 = false;
                    } else if ((btn4.getText() == pl[3] && btn2.getText() == en[3]) || (btn4.getText() == en[3] && btn2.getText() == pl[3])) {
                        btn4.setBackgroundResource(R.drawable.button_border_purple);
                        btn2.setBackgroundResource(R.drawable.button_border_purple);
                        btn2.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click2 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn4.setText(" ");
                                btn2.setText(" ");
                            }
                        }, 2000);
                        click4 = false;
                        click2 = false;
                    }
                } else if (click5 == true) {
                    if ((btn5.getText() == pl[0] && btn2.getText() == en[0]) || (btn5.getText() == en[0] && btn2.getText() == pl[0])) {
                        btn5.setBackgroundResource(R.drawable.button_border_blue);
                        btn2.setBackgroundResource(R.drawable.button_border_blue);
                        btn2.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click2 = false;
                    } else if ((btn5.getText() == pl[1] && btn2.getText() == en[1]) || (btn5.getText() == en[1] && btn2.getText() == pl[1])) {
                        btn5.setBackgroundResource(R.drawable.button_border_pink);
                        btn2.setBackgroundResource(R.drawable.button_border_pink);
                        btn2.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click2 = false;
                    } else if ((btn5.getText() == pl[2] && btn2.getText() == en[2]) || (btn5.getText() == en[2] && btn2.getText() == pl[2])) {
                        btn5.setBackgroundResource(R.drawable.button_border_yellow);
                        btn2.setBackgroundResource(R.drawable.button_border_yellow);
                        btn2.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click2 = false;
                    } else if ((btn5.getText() == pl[3] && btn2.getText() == en[3]) || (btn5.getText() == en[3] && btn2.getText() == pl[3])) {
                        btn5.setBackgroundResource(R.drawable.button_border_purple);
                        btn2.setBackgroundResource(R.drawable.button_border_purple);
                        btn2.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click2 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn5.setText(" ");
                                btn2.setText(" ");
                            }
                        }, 2000);

                        click5 = false;
                        click2 = false;
                    }
                } else if (click6 == true) {
                    if ((btn6.getText() == pl[0] && btn2.getText() == en[0]) || (btn6.getText() == en[0] && btn2.getText() == pl[0])) {
                        btn6.setBackgroundResource(R.drawable.button_border_blue);
                        btn2.setBackgroundResource(R.drawable.button_border_blue);
                        btn2.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click2 = false;
                    } else if ((btn6.getText() == pl[1] && btn2.getText() == en[1]) || (btn6.getText() == en[1] && btn2.getText() == pl[1])) {
                        btn6.setBackgroundResource(R.drawable.button_border_pink);
                        btn2.setBackgroundResource(R.drawable.button_border_pink);
                        btn2.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click2 = false;
                    } else if ((btn6.getText() == pl[2] && btn2.getText() == en[2]) || (btn6.getText() == en[2] && btn2.getText() == pl[2])) {
                        btn6.setBackgroundResource(R.drawable.button_border_yellow);
                        btn2.setBackgroundResource(R.drawable.button_border_yellow);
                        btn2.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click2 = false;
                    } else if ((btn6.getText() == pl[3] && btn2.getText() == en[3]) || (btn6.getText() == en[3] && btn2.getText() == pl[3])) {
                        btn6.setBackgroundResource(R.drawable.button_border_purple);
                        btn2.setBackgroundResource(R.drawable.button_border_purple);
                        btn2.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click2 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn6.setText(" ");
                                btn2.setText(" ");
                            }
                        }, 2000);
                        click6 = false;
                        click2 = false;
                    }
                } else if (click8 == true) {
                    if ((btn8.getText() == pl[0] && btn2.getText() == en[0]) || (btn8.getText() == en[0] && btn2.getText() == pl[0])) {
                        btn7.setBackgroundResource(R.drawable.button_border_blue);
                        btn2.setBackgroundResource(R.drawable.button_border_blue);
                        btn2.setClickable(false);
                        btn8.setClickable(false);
                        click7 = false;
                        click2 = false;
                    } else if ((btn8.getText() == pl[1] && btn2.getText() == en[1]) || (btn8.getText() == en[1] && btn2.getText() == pl[1])) {
                        btn7.setBackgroundResource(R.drawable.button_border_pink);
                        btn2.setBackgroundResource(R.drawable.button_border_pink);
                        btn2.setClickable(false);
                        btn8.setClickable(false);
                        click7 = false;
                        click2 = false;
                    } else if ((btn8.getText() == pl[2] && btn2.getText() == en[2]) || (btn8.getText() == en[2] && btn2.getText() == pl[2])) {
                        btn7.setBackgroundResource(R.drawable.button_border_yellow);
                        btn2.setBackgroundResource(R.drawable.button_border_yellow);
                        btn2.setClickable(false);
                        btn8.setClickable(false);
                        click7 = false;
                        click2 = false;
                    } else if ((btn8.getText() == pl[3] && btn2.getText() == en[3]) || (btn8.getText() == en[3] && btn2.getText() == pl[3])) {
                        btn7.setBackgroundResource(R.drawable.button_border_purple);
                        btn2.setBackgroundResource(R.drawable.button_border_purple);
                        btn2.setClickable(false);
                        btn8.setClickable(false);
                        click7 = false;
                        click2 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn8.setText(" ");
                                btn2.setText(" ");
                            }
                        }, 2000);
                        click7 = false;
                        click2 = false;
                    }
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click3 = true;
                btn3.setText(pl[3]);

                if (click1 == true) {
                    if ((btn1.getText() == pl[0] && btn3.getText() == en[0]) || (btn1.getText() == en[0] && btn3.getText() == pl[0])) {
                        btn1.setBackgroundResource(R.drawable.button_border_blue);
                        btn3.setBackgroundResource(R.drawable.button_border_blue);
                        btn3.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click3 = false;
                    } else if ((btn1.getText() == pl[1] && btn3.getText() == en[1]) || (btn1.getText() == en[1] && btn3.getText() == pl[1])) {
                        btn1.setBackgroundResource(R.drawable.button_border_pink);
                        btn3.setBackgroundResource(R.drawable.button_border_pink);
                        btn3.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click3 = false;
                    } else if ((btn1.getText() == pl[2] && btn3.getText() == en[2]) || (btn1.getText() == en[2] && btn3.getText() == pl[2])) {
                        btn1.setBackgroundResource(R.drawable.button_border_yellow);
                        btn3.setBackgroundResource(R.drawable.button_border_yellow);
                        btn3.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click3 = false;
                    } else if ((btn1.getText() == pl[3] && btn3.getText() == en[3]) || (btn1.getText() == en[3] && btn3.getText() == pl[3])) {
                        btn1.setBackgroundResource(R.drawable.button_border_purple);
                        btn3.setBackgroundResource(R.drawable.button_border_purple);
                        btn3.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click3 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn1.setText(" ");
                                btn3.setText(" ");
                            }
                        }, 2000);
                        click1 = false;
                        click3 = false;
                    }
                } else if (click2 == true) {
                    if ((btn2.getText() == pl[0] && btn3.getText() == en[0]) || (btn2.getText() == en[0] && btn3.getText() == pl[0])) {
                        btn2.setBackgroundResource(R.drawable.button_border_blue);
                        btn3.setBackgroundResource(R.drawable.button_border_blue);
                        btn3.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click3 = false;
                    } else if ((btn2.getText() == pl[1] && btn3.getText() == en[1]) || (btn2.getText() == en[1] && btn3.getText() == pl[1])) {
                        btn2.setBackgroundResource(R.drawable.button_border_pink);
                        btn3.setBackgroundResource(R.drawable.button_border_pink);
                        btn3.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click3 = false;
                    } else if ((btn2.getText() == pl[2] && btn3.getText() == en[2]) || (btn2.getText() == en[2] && btn3.getText() == pl[2])) {
                        btn2.setBackgroundResource(R.drawable.button_border_yellow);
                        btn3.setBackgroundResource(R.drawable.button_border_yellow);
                        btn3.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click3 = false;
                    } else if ((btn2.getText() == pl[3] && btn3.getText() == en[3]) || (btn2.getText() == en[3] && btn3.getText() == pl[3])) {
                        btn2.setBackgroundResource(R.drawable.button_border_purple);
                        btn3.setBackgroundResource(R.drawable.button_border_purple);
                        btn3.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click3 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn2.setText(" ");
                                btn3.setText(" ");
                            }
                        }, 2000);
                        click2 = false;
                        click3 = false;
                    }
                } else if (click7 == true) {
                    if ((btn3.getText() == pl[0] && btn7.getText() == en[0]) || (btn3.getText() == en[0] && btn7.getText() == pl[0])) {
                        btn3.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setBackgroundResource(R.drawable.button_border_blue);
                        btn3.setClickable(false);
                        btn7.setClickable(false);
                        click3 = false;
                        click7 = false;
                    } else if ((btn3.getText() == pl[1] && btn7.getText() == en[1]) || (btn3.getText() == en[1] && btn7.getText() == pl[1])) {
                        btn3.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setBackgroundResource(R.drawable.button_border_pink);
                        btn3.setClickable(false);
                        btn7.setClickable(false);
                        click3 = false;
                        click7 = false;
                    } else if ((btn3.getText() == pl[2] && btn7.getText() == en[2]) || (btn3.getText() == en[2] && btn7.getText() == pl[2])) {
                        btn3.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setBackgroundResource(R.drawable.button_border_yellow);
                        btn3.setClickable(false);
                        btn7.setClickable(false);
                        click3 = false;
                        click7 = false;
                    } else if ((btn3.getText() == pl[3] && btn7.getText() == en[3]) || (btn3.getText() == en[3] && btn7.getText() == pl[3])) {
                        btn3.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setBackgroundResource(R.drawable.button_border_purple);
                        btn3.setClickable(false);
                        btn7.setClickable(false);
                        click3 = false;
                        click7 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn7.setText(" ");
                                btn3.setText(" ");
                            }
                        }, 2000);
                        click3 = false;
                        click7 = false;
                    }
                } else if (click4 == true) {
                    if ((btn4.getText() == pl[0] && btn3.getText() == en[0]) || (btn4.getText() == en[0] && btn3.getText() == pl[0])) {
                        btn4.setBackgroundResource(R.drawable.button_border_blue);
                        btn3.setBackgroundResource(R.drawable.button_border_blue);
                        btn3.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click3 = false;
                    } else if ((btn4.getText() == pl[1] && btn3.getText() == en[1]) || (btn4.getText() == en[1] && btn3.getText() == pl[1])) {
                        btn4.setBackgroundResource(R.drawable.button_border_pink);
                        btn3.setBackgroundResource(R.drawable.button_border_pink);
                        btn3.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click3 = false;
                    } else if ((btn4.getText() == pl[2] && btn3.getText() == en[2]) || (btn4.getText() == en[2] && btn3.getText() == pl[2])) {
                        btn4.setBackgroundResource(R.drawable.button_border_yellow);
                        btn3.setBackgroundResource(R.drawable.button_border_yellow);
                        btn3.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click3 = false;
                    } else if ((btn4.getText() == pl[3] && btn3.getText() == en[3]) || (btn4.getText() == en[3] && btn3.getText() == pl[3])) {
                        btn4.setBackgroundResource(R.drawable.button_border_purple);
                        btn3.setBackgroundResource(R.drawable.button_border_purple);
                        btn3.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click3 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn4.setText(" ");
                                btn3.setText(" ");
                            }
                        }, 2000);
                        click4 = false;
                        click3 = false;
                    }
                } else if (click5 == true) {
                    if ((btn5.getText() == pl[0] && btn3.getText() == en[0]) || (btn5.getText() == en[0] && btn3.getText() == pl[0])) {
                        btn5.setBackgroundResource(R.drawable.button_border_blue);
                        btn3.setBackgroundResource(R.drawable.button_border_blue);
                        btn3.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click3 = false;
                    } else if ((btn5.getText() == pl[1] && btn3.getText() == en[1]) || (btn5.getText() == en[1] && btn3.getText() == pl[1])) {
                        btn5.setBackgroundResource(R.drawable.button_border_pink);
                        btn3.setBackgroundResource(R.drawable.button_border_pink);
                        btn3.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click3 = false;
                    } else if ((btn5.getText() == pl[2] && btn3.getText() == en[2]) || (btn5.getText() == en[2] && btn3.getText() == pl[2])) {
                        btn5.setBackgroundResource(R.drawable.button_border_yellow);
                        btn3.setBackgroundResource(R.drawable.button_border_yellow);
                        btn3.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click3 = false;
                    } else if ((btn5.getText() == pl[3] && btn3.getText() == en[3]) || (btn5.getText() == en[3] && btn3.getText() == pl[3])) {
                        btn5.setBackgroundResource(R.drawable.button_border_purple);
                        btn3.setBackgroundResource(R.drawable.button_border_purple);
                        btn3.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click3 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn5.setText(" ");
                                btn3.setText(" ");
                            }
                        }, 2000);
                        click5 = false;
                        click3 = false;
                    }
                } else if (click6 == true) {
                    if ((btn6.getText() == pl[0] && btn3.getText() == en[0]) || (btn6.getText() == en[0] && btn3.getText() == pl[0])) {
                        btn6.setBackgroundResource(R.drawable.button_border_blue);
                        btn3.setBackgroundResource(R.drawable.button_border_blue);
                        btn3.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click3 = false;
                    } else if ((btn6.getText() == pl[1] && btn3.getText() == en[1]) || (btn6.getText() == en[1] && btn3.getText() == pl[1])) {
                        btn6.setBackgroundResource(R.drawable.button_border_pink);
                        btn3.setBackgroundResource(R.drawable.button_border_pink);
                        btn3.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click3 = false;
                    } else if ((btn6.getText() == pl[2] && btn3.getText() == en[2]) || (btn6.getText() == en[2] && btn3.getText() == pl[2])) {
                        btn6.setBackgroundResource(R.drawable.button_border_yellow);
                        btn3.setBackgroundResource(R.drawable.button_border_yellow);
                        btn3.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click3 = false;
                    } else if ((btn6.getText() == pl[3] && btn3.getText() == en[3]) || (btn6.getText() == en[3] && btn3.getText() == pl[3])) {
                        btn6.setBackgroundResource(R.drawable.button_border_purple);
                        btn3.setBackgroundResource(R.drawable.button_border_purple);
                        btn3.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click3 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn6.setText(" ");
                                btn3.setText(" ");
                            }
                        }, 2000);
                        click6 = false;
                        click3 = false;
                    }
                } else if (click8 == true) {
                    if ((btn8.getText() == pl[0] && btn3.getText() == en[0]) || (btn8.getText() == en[0] && btn3.getText() == pl[0])) {
                        btn3.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setBackgroundResource(R.drawable.button_border_blue);
                        btn3.setClickable(false);
                        btn8.setClickable(false);
                        click3 = false;
                        click8 = false;
                    } else if ((btn8.getText() == pl[1] && btn3.getText() == en[1]) || (btn8.getText() == en[1] && btn3.getText() == pl[1])) {
                        btn3.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setBackgroundResource(R.drawable.button_border_pink);
                        btn3.setClickable(false);
                        btn8.setClickable(false);
                        click3 = false;
                        click8 = false;
                    } else if ((btn8.getText() == pl[2] && btn3.getText() == en[2]) || (btn8.getText() == en[2] && btn3.getText() == pl[2])) {
                        btn3.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setBackgroundResource(R.drawable.button_border_yellow);
                        btn3.setClickable(false);
                        btn8.setClickable(false);
                        click3 = false;
                        click8 = false;
                    } else if ((btn8.getText() == pl[3] && btn3.getText() == en[3]) || (btn8.getText() == en[3] && btn3.getText() == pl[3])) {
                        btn3.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setBackgroundResource(R.drawable.button_border_purple);
                        btn3.setClickable(false);
                        btn8.setClickable(false);
                        click3 = false;
                        click8 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn8.setText(" ");
                                btn3.setText(" ");
                            }
                        }, 2000);
                        click3 = false;
                        click8 = false;
                    }
                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click4 = true;
                btn4.setText(en[3]);

                if (click1 == true) {
                    if ((btn1.getText() == pl[0] && btn4.getText() == en[0]) || (btn1.getText() == en[0] && btn4.getText() == pl[0])) {
                        btn1.setBackgroundResource(R.drawable.button_border_blue);
                        btn4.setBackgroundResource(R.drawable.button_border_blue);
                        btn4.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click4 = false;
                    } else if ((btn1.getText() == pl[1] && btn4.getText() == en[1]) || (btn1.getText() == en[1] && btn4.getText() == pl[1])) {
                        btn1.setBackgroundResource(R.drawable.button_border_pink);
                        btn4.setBackgroundResource(R.drawable.button_border_pink);
                        btn4.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click4 = false;
                    } else if ((btn1.getText() == pl[2] && btn4.getText() == en[2]) || (btn1.getText() == en[2] && btn4.getText() == pl[2])) {
                        btn1.setBackgroundResource(R.drawable.button_border_yellow);
                        btn4.setBackgroundResource(R.drawable.button_border_yellow);
                        btn4.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click4 = false;
                    } else if ((btn1.getText() == pl[3] && btn4.getText() == en[3]) || (btn1.getText() == en[3] && btn4.getText() == pl[3])) {
                        btn1.setBackgroundResource(R.drawable.button_border_purple);
                        btn4.setBackgroundResource(R.drawable.button_border_purple);
                        btn4.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click4 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn4.setText(" ");
                                btn1.setText(" ");
                            }
                        }, 2000);
                        click1 = false;
                        click4 = false;
                    }
                } else if (click2 == true) {
                    if ((btn2.getText() == pl[0] && btn4.getText() == en[0]) || (btn2.getText() == en[0] && btn4.getText() == pl[0])) {
                        btn2.setBackgroundResource(R.drawable.button_border_blue);
                        btn4.setBackgroundResource(R.drawable.button_border_blue);
                        btn4.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click4 = false;
                    } else if ((btn2.getText() == pl[1] && btn4.getText() == en[1]) || (btn2.getText() == en[1] && btn4.getText() == pl[1])) {
                        btn2.setBackgroundResource(R.drawable.button_border_pink);
                        btn4.setBackgroundResource(R.drawable.button_border_pink);
                        btn4.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click4 = false;
                    } else if ((btn2.getText() == pl[2] && btn4.getText() == en[2]) || (btn2.getText() == en[2] && btn4.getText() == pl[2])) {
                        btn2.setBackgroundResource(R.drawable.button_border_yellow);
                        btn4.setBackgroundResource(R.drawable.button_border_yellow);
                        btn4.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click4 = false;
                    } else if ((btn2.getText() == pl[3] && btn4.getText() == en[3]) || (btn2.getText() == en[3] && btn4.getText() == pl[3])) {
                        btn2.setBackgroundResource(R.drawable.button_border_purple);
                        btn4.setBackgroundResource(R.drawable.button_border_purple);
                        btn4.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click4 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn4.setText(" ");
                                btn2.setText(" ");
                            }
                        }, 2000);
                        click2 = false;
                        click4 = false;
                    }
                } else if (click3 == true) {
                    if ((btn3.getText() == pl[0] && btn4.getText() == en[0]) || (btn3.getText() == en[0] && btn4.getText() == pl[0])) {
                        btn3.setBackgroundResource(R.drawable.button_border_blue);
                        btn4.setBackgroundResource(R.drawable.button_border_blue);
                        btn4.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click4 = false;
                    } else if ((btn3.getText() == pl[1] && btn4.getText() == en[1]) || (btn3.getText() == en[1] && btn4.getText() == pl[1])) {
                        btn3.setBackgroundResource(R.drawable.button_border_pink);
                        btn4.setBackgroundResource(R.drawable.button_border_pink);
                        btn4.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click4 = false;
                    } else if ((btn3.getText() == pl[2] && btn4.getText() == en[2]) || (btn3.getText() == en[2] && btn4.getText() == pl[2])) {
                        btn3.setBackgroundResource(R.drawable.button_border_yellow);
                        btn4.setBackgroundResource(R.drawable.button_border_yellow);
                        btn4.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click4 = false;
                    } else if ((btn3.getText() == pl[3] && btn4.getText() == en[3]) || (btn3.getText() == en[3] && btn4.getText() == pl[3])) {
                        btn3.setBackgroundResource(R.drawable.button_border_purple);
                        btn4.setBackgroundResource(R.drawable.button_border_purple);
                        btn4.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click4 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn4.setText(" ");
                                btn3.setText(" ");
                            }
                        }, 2000);
                        click3 = false;
                        click4 = false;
                    }
                } else if (click7 == true) {
                    if ((btn4.getText() == pl[0] && btn7.getText() == en[0]) || (btn4.getText() == en[0] && btn7.getText() == pl[0])) {
                        btn4.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setBackgroundResource(R.drawable.button_border_blue);
                        btn4.setClickable(false);
                        btn7.setClickable(false);
                        click4 = false;
                        click7 = false;
                    } else if ((btn4.getText() == pl[1] && btn7.getText() == en[1]) || (btn4.getText() == en[1] && btn7.getText() == pl[1])) {
                        btn4.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setBackgroundResource(R.drawable.button_border_pink);
                        btn4.setClickable(false);
                        btn7.setClickable(false);
                        click4 = false;
                        click7 = false;
                    } else if ((btn4.getText() == pl[2] && btn7.getText() == en[2]) || (btn4.getText() == en[2] && btn7.getText() == pl[2])) {
                        btn4.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setBackgroundResource(R.drawable.button_border_yellow);
                        btn4.setClickable(false);
                        btn7.setClickable(false);
                        click4 = false;
                        click7 = false;
                    } else if ((btn4.getText() == pl[3] && btn7.getText() == en[3]) || (btn4.getText() == en[3] && btn7.getText() == pl[3])) {
                        btn4.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setBackgroundResource(R.drawable.button_border_purple);
                        btn4.setClickable(false);
                        btn7.setClickable(false);
                        click4 = false;
                        click7 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn4.setText(" ");
                                btn7.setText(" ");
                            }
                        }, 2000);
                        click4 = false;
                        click7 = false;
                    }
                } else if (click5 == true) {
                    if ((btn5.getText() == pl[0] && btn4.getText() == en[0]) || (btn5.getText() == en[0] && btn4.getText() == pl[0])) {
                        btn5.setBackgroundResource(R.drawable.button_border_blue);
                        btn4.setBackgroundResource(R.drawable.button_border_blue);
                        btn4.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click4 = false;
                    } else if ((btn5.getText() == pl[1] && btn4.getText() == en[1]) || (btn5.getText() == en[1] && btn4.getText() == pl[1])) {
                        btn5.setBackgroundResource(R.drawable.button_border_pink);
                        btn4.setBackgroundResource(R.drawable.button_border_pink);
                        btn4.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click4 = false;
                    } else if ((btn5.getText() == pl[2] && btn4.getText() == en[2]) || (btn5.getText() == en[2] && btn4.getText() == pl[2])) {
                        btn5.setBackgroundResource(R.drawable.button_border_yellow);
                        btn4.setBackgroundResource(R.drawable.button_border_yellow);
                        btn4.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click4 = false;
                    } else if ((btn5.getText() == pl[3] && btn4.getText() == en[3]) || (btn5.getText() == en[3] && btn4.getText() == pl[3])) {
                        btn5.setBackgroundResource(R.drawable.button_border_purple);
                        btn4.setBackgroundResource(R.drawable.button_border_purple);
                        btn4.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click4 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn4.setText(" ");
                                btn5.setText(" ");
                            }
                        }, 2000);
                        click5 = false;
                        click4 = false;
                    }
                } else if (click6 == true) {
                    if ((btn6.getText() == pl[0] && btn4.getText() == en[0]) || (btn6.getText() == en[0] && btn4.getText() == pl[0])) {
                        btn6.setBackgroundResource(R.drawable.button_border_blue);
                        btn4.setBackgroundResource(R.drawable.button_border_blue);
                        btn4.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click4 = false;
                    } else if ((btn6.getText() == pl[1] && btn4.getText() == en[1]) || (btn6.getText() == en[1] && btn4.getText() == pl[1])) {
                        btn6.setBackgroundResource(R.drawable.button_border_pink);
                        btn4.setBackgroundResource(R.drawable.button_border_pink);
                        btn4.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click4 = false;
                    } else if ((btn6.getText() == pl[2] && btn4.getText() == en[2]) || (btn6.getText() == en[2] && btn4.getText() == pl[2])) {
                        btn6.setBackgroundResource(R.drawable.button_border_yellow);
                        btn4.setBackgroundResource(R.drawable.button_border_yellow);
                        btn4.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click4 = false;
                    } else if ((btn6.getText() == pl[3] && btn4.getText() == en[3]) || (btn6.getText() == en[3] && btn4.getText() == pl[3])) {
                        btn6.setBackgroundResource(R.drawable.button_border_purple);
                        btn4.setBackgroundResource(R.drawable.button_border_purple);
                        btn4.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click4 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn4.setText(" ");
                                btn6.setText(" ");
                            }
                        }, 2000);
                        click6 = false;
                        click4 = false;
                    }
                } else if (click8 == true) {
                    if ((btn8.getText() == pl[0] && btn4.getText() == en[0]) || (btn8.getText() == en[0] && btn4.getText() == pl[0])) {
                        btn8.setBackgroundResource(R.drawable.button_border_blue);
                        btn4.setBackgroundResource(R.drawable.button_border_blue);
                        btn4.setClickable(false);
                        btn8.setClickable(false);
                        click8 = false;
                        click4 = false;
                    } else if ((btn8.getText() == pl[1] && btn4.getText() == en[1]) || (btn8.getText() == en[1] && btn4.getText() == pl[1])) {
                        btn8.setBackgroundResource(R.drawable.button_border_pink);
                        btn4.setBackgroundResource(R.drawable.button_border_pink);
                        btn4.setClickable(false);
                        btn8.setClickable(false);
                        click8 = false;
                        click4 = false;
                    } else if ((btn8.getText() == pl[2] && btn4.getText() == en[2]) || (btn8.getText() == en[2] && btn4.getText() == pl[2])) {
                        btn8.setBackgroundResource(R.drawable.button_border_yellow);
                        btn4.setBackgroundResource(R.drawable.button_border_yellow);
                        btn4.setClickable(false);
                        btn8.setClickable(false);
                        click8 = false;
                        click4 = false;
                    } else if ((btn8.getText() == pl[3] && btn4.getText() == en[3]) || (btn8.getText() == en[3] && btn4.getText() == pl[3])) {
                        btn8.setBackgroundResource(R.drawable.button_border_purple);
                        btn4.setBackgroundResource(R.drawable.button_border_purple);
                        btn4.setClickable(false);
                        btn8.setClickable(false);
                        click8 = false;
                        click4 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn4.setText(" ");
                                btn8.setText(" ");
                            }
                        }, 2000);
                        click8 = false;
                        click4 = false;
                    }
                }
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click5 = true;
                btn5.setText(pl[1]);

                if (click1 == true) {
                    if ((btn1.getText() == pl[0] && btn5.getText() == en[0]) || (btn1.getText() == en[0] && btn5.getText() == pl[0])) {
                        btn1.setBackgroundResource(R.drawable.button_border_blue);
                        btn5.setBackgroundResource(R.drawable.button_border_blue);
                        btn5.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click5 = false;
                    } else if ((btn1.getText() == pl[1] && btn5.getText() == en[1]) || (btn1.getText() == en[1] && btn5.getText() == pl[1])) {
                        btn1.setBackgroundResource(R.drawable.button_border_pink);
                        btn5.setBackgroundResource(R.drawable.button_border_pink);
                        btn5.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click5 = false;
                    } else if ((btn1.getText() == pl[2] && btn5.getText() == en[2]) || (btn1.getText() == en[2] && btn5.getText() == pl[2])) {
                        btn1.setBackgroundResource(R.drawable.button_border_yellow);
                        btn5.setBackgroundResource(R.drawable.button_border_yellow);
                        btn5.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click5 = false;
                    } else if ((btn1.getText() == pl[3] && btn5.getText() == en[3]) || (btn1.getText() == en[3] && btn5.getText() == pl[3])) {
                        btn1.setBackgroundResource(R.drawable.button_border_purple);
                        btn5.setBackgroundResource(R.drawable.button_border_purple);
                        btn5.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click5 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn5.setText(" ");
                                btn1.setText(" ");
                            }
                        }, 2000);
                        click1 = false;
                        click5 = false;
                    }
                } else if (click2 == true) {
                    if ((btn2.getText() == pl[0] && btn5.getText() == en[0]) || (btn2.getText() == en[0] && btn5.getText() == pl[0])) {
                        btn2.setBackgroundResource(R.drawable.button_border_blue);
                        btn5.setBackgroundResource(R.drawable.button_border_blue);
                        btn5.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click5 = false;
                    } else if ((btn2.getText() == pl[1] && btn5.getText() == en[1]) || (btn2.getText() == en[1] && btn5.getText() == pl[1])) {
                        btn2.setBackgroundResource(R.drawable.button_border_pink);
                        btn5.setBackgroundResource(R.drawable.button_border_pink);
                        btn5.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click5 = false;
                    } else if ((btn2.getText() == pl[2] && btn5.getText() == en[2]) || (btn2.getText() == en[2] && btn5.getText() == pl[2])) {
                        btn2.setBackgroundResource(R.drawable.button_border_yellow);
                        btn5.setBackgroundResource(R.drawable.button_border_yellow);
                        btn5.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click5 = false;
                    } else if ((btn2.getText() == pl[3] && btn5.getText() == en[3]) || (btn2.getText() == en[3] && btn5.getText() == pl[3])) {
                        btn2.setBackgroundResource(R.drawable.button_border_purple);
                        btn5.setBackgroundResource(R.drawable.button_border_purple);
                        btn5.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click5 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn5.setText(" ");
                                btn2.setText(" ");
                            }
                        }, 2000);
                        click2 = false;
                        click5 = false;
                    }
                } else if (click3 == true) {
                    if ((btn3.getText() == pl[0] && btn5.getText() == en[0]) || (btn3.getText() == en[0] && btn5.getText() == pl[0])) {
                        btn3.setBackgroundResource(R.drawable.button_border_blue);
                        btn5.setBackgroundResource(R.drawable.button_border_blue);
                        btn5.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click5 = false;
                    } else if ((btn3.getText() == pl[1] && btn5.getText() == en[1]) || (btn3.getText() == en[1] && btn5.getText() == pl[1])) {
                        btn3.setBackgroundResource(R.drawable.button_border_pink);
                        btn5.setBackgroundResource(R.drawable.button_border_pink);
                        btn5.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click5 = false;
                    } else if ((btn3.getText() == pl[2] && btn5.getText() == en[2]) || (btn3.getText() == en[2] && btn5.getText() == pl[2])) {
                        btn3.setBackgroundResource(R.drawable.button_border_yellow);
                        btn5.setBackgroundResource(R.drawable.button_border_yellow);
                        btn5.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click5 = false;
                    } else if ((btn3.getText() == pl[3] && btn5.getText() == en[3]) || (btn3.getText() == en[3] && btn5.getText() == pl[3])) {
                        btn3.setBackgroundResource(R.drawable.button_border_purple);
                        btn5.setBackgroundResource(R.drawable.button_border_purple);
                        btn5.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click5 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn5.setText(" ");
                                btn3.setText(" ");
                            }
                        }, 2000);
                        click3 = false;
                        click5 = false;
                    }
                } else if (click4 == true) {
                    if ((btn4.getText() == pl[0] && btn5.getText() == en[0]) || (btn4.getText() == en[0] && btn5.getText() == pl[0])) {
                        btn4.setBackgroundResource(R.drawable.button_border_blue);
                        btn5.setBackgroundResource(R.drawable.button_border_blue);
                        btn5.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click5 = false;
                    } else if ((btn4.getText() == pl[1] && btn5.getText() == en[1]) || (btn4.getText() == en[1] && btn5.getText() == pl[1])) {
                        btn4.setBackgroundResource(R.drawable.button_border_pink);
                        btn5.setBackgroundResource(R.drawable.button_border_pink);
                        btn5.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click5 = false;
                    } else if ((btn4.getText() == pl[2] && btn5.getText() == en[2]) || (btn4.getText() == en[2] && btn5.getText() == pl[2])) {
                        btn4.setBackgroundResource(R.drawable.button_border_yellow);
                        btn5.setBackgroundResource(R.drawable.button_border_yellow);
                        btn5.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click5 = false;
                    } else if ((btn4.getText() == pl[3] && btn5.getText() == en[3]) || (btn4.getText() == en[3] && btn5.getText() == pl[3])) {
                        btn4.setBackgroundResource(R.drawable.button_border_purple);
                        btn5.setBackgroundResource(R.drawable.button_border_purple);
                        btn5.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click5 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn5.setText(" ");
                                btn4.setText(" ");
                            }
                        }, 2000);
                        click4 = false;
                        click5 = false;
                    }
                } else if (click7 == true) {
                    if ((btn5.getText() == pl[0] && btn7.getText() == en[0]) || (btn5.getText() == en[0] && btn7.getText() == pl[0])) {
                        btn5.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setBackgroundResource(R.drawable.button_border_blue);
                        btn5.setClickable(false);
                        btn7.setClickable(false);
                        click5 = false;
                        click7 = false;
                    } else if ((btn5.getText() == pl[1] && btn7.getText() == en[1]) || (btn5.getText() == en[1] && btn7.getText() == pl[1])) {
                        btn5.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setBackgroundResource(R.drawable.button_border_pink);
                        btn5.setClickable(false);
                        btn7.setClickable(false);
                        click5 = false;
                        click7 = false;
                    } else if ((btn5.getText() == pl[2] && btn7.getText() == en[2]) || (btn5.getText() == en[2] && btn7.getText() == pl[2])) {
                        btn5.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setBackgroundResource(R.drawable.button_border_yellow);
                        btn5.setClickable(false);
                        btn7.setClickable(false);
                        click5 = false;
                        click7 = false;
                    } else if ((btn5.getText() == pl[3] && btn7.getText() == en[3]) || (btn5.getText() == en[3] && btn7.getText() == pl[3])) {
                        btn5.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setBackgroundResource(R.drawable.button_border_purple);
                        btn5.setClickable(false);
                        btn7.setClickable(false);
                        click5 = false;
                        click7 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn5.setText(" ");
                                btn7.setText(" ");
                            }
                        }, 2000);
                        click5 = false;
                        click7 = false;
                    }
                } else if (click6 == true) {
                    if ((btn6.getText() == pl[0] && btn5.getText() == en[0]) || (btn6.getText() == en[0] && btn5.getText() == pl[0])) {
                        btn6.setBackgroundResource(R.drawable.button_border_blue);
                        btn5.setBackgroundResource(R.drawable.button_border_blue);
                        btn5.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click5 = false;
                    } else if ((btn6.getText() == pl[1] && btn5.getText() == en[1]) || (btn6.getText() == en[1] && btn5.getText() == pl[1])) {
                        btn6.setBackgroundResource(R.drawable.button_border_pink);
                        btn5.setBackgroundResource(R.drawable.button_border_pink);
                        btn5.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click5 = false;
                    } else if ((btn6.getText() == pl[2] && btn5.getText() == en[2]) || (btn6.getText() == en[2] && btn5.getText() == pl[2])) {
                        btn6.setBackgroundResource(R.drawable.button_border_yellow);
                        btn5.setBackgroundResource(R.drawable.button_border_yellow);
                        btn5.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click5 = false;
                    } else if ((btn6.getText() == pl[3] && btn5.getText() == en[3]) || (btn6.getText() == en[3] && btn5.getText() == pl[3])) {
                        btn6.setBackgroundResource(R.drawable.button_border_purple);
                        btn5.setBackgroundResource(R.drawable.button_border_purple);
                        btn5.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click5 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn5.setText(" ");
                                btn6.setText(" ");
                            }
                        }, 2000);
                        click6 = false;
                        click5 = false;
                    }
                } else if (click8 == true) {
                    if ((btn8.getText() == pl[0] && btn5.getText() == en[0]) || (btn8.getText() == en[0] && btn5.getText() == pl[0])) {
                        btn5.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setBackgroundResource(R.drawable.button_border_blue);
                        btn5.setClickable(false);
                        btn8.setClickable(false);
                        click5 = false;
                        click8 = false;
                    } else if ((btn8.getText() == pl[1] && btn5.getText() == en[1]) || (btn8.getText() == en[1] && btn5.getText() == pl[1])) {
                        btn5.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setBackgroundResource(R.drawable.button_border_pink);
                        btn5.setClickable(false);
                        btn8.setClickable(false);
                        click5 = false;
                        click8 = false;
                    } else if ((btn8.getText() == pl[2] && btn5.getText() == en[2]) || (btn8.getText() == en[2] && btn5.getText() == pl[2])) {
                        btn5.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setBackgroundResource(R.drawable.button_border_yellow);
                        btn5.setClickable(false);
                        btn8.setClickable(false);
                        click5 = false;
                        click8 = false;
                    } else if ((btn8.getText() == pl[3] && btn5.getText() == en[3]) || (btn8.getText() == en[3] && btn5.getText() == pl[3])) {
                        btn5.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setBackgroundResource(R.drawable.button_border_purple);
                        btn5.setClickable(false);
                        btn8.setClickable(false);
                        click5 = false;
                        click8 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn5.setText(" ");
                                btn8.setText(" ");
                            }
                        }, 2000);
                        click5 = false;
                        click8 = false;
                    }
                }
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click6 = true;
                btn6.setText(en[0]);

                if (click1 == true) {
                    if ((btn1.getText() == pl[0] && btn6.getText() == en[0]) || (btn1.getText() == en[0] && btn6.getText() == pl[0])) {
                        btn1.setBackgroundResource(R.drawable.button_border_blue);
                        btn6.setBackgroundResource(R.drawable.button_border_blue);
                        btn6.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click6 = false;
                    } else if ((btn1.getText() == pl[1] && btn6.getText() == en[1]) || (btn1.getText() == en[1] && btn6.getText() == pl[1])) {
                        btn1.setBackgroundResource(R.drawable.button_border_pink);
                        btn6.setBackgroundResource(R.drawable.button_border_pink);
                        btn6.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click6 = false;
                    } else if ((btn1.getText() == pl[2] && btn6.getText() == en[2]) || (btn1.getText() == en[2] && btn6.getText() == pl[2])) {
                        btn1.setBackgroundResource(R.drawable.button_border_yellow);
                        btn6.setBackgroundResource(R.drawable.button_border_yellow);
                        btn6.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click6 = false;
                    } else if ((btn1.getText() == pl[3] && btn6.getText() == en[3]) || (btn1.getText() == en[3] && btn6.getText() == pl[3])) {
                        btn1.setBackgroundResource(R.drawable.button_border_purple);
                        btn6.setBackgroundResource(R.drawable.button_border_purple);
                        btn6.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click6 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn6.setText(" ");
                                btn1.setText(" ");
                            }
                        }, 2000);
                        click1 = false;
                        click6 = false;
                    }
                } else if (click2 == true) {
                    if ((btn2.getText() == pl[0] && btn6.getText() == en[0]) || (btn2.getText() == en[0] && btn6.getText() == pl[0])) {
                        btn2.setBackgroundResource(R.drawable.button_border_blue);
                        btn6.setBackgroundResource(R.drawable.button_border_blue);
                        btn6.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click6 = false;
                    } else if ((btn2.getText() == pl[1] && btn6.getText() == en[1]) || (btn2.getText() == en[1] && btn6.getText() == pl[1])) {
                        btn2.setBackgroundResource(R.drawable.button_border_pink);
                        btn6.setBackgroundResource(R.drawable.button_border_pink);
                        btn6.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click6 = false;
                    } else if ((btn2.getText() == pl[2] && btn6.getText() == en[2]) || (btn2.getText() == en[2] && btn6.getText() == pl[2])) {
                        btn2.setBackgroundResource(R.drawable.button_border_yellow);
                        btn6.setBackgroundResource(R.drawable.button_border_yellow);
                        btn6.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click6 = false;
                    } else if ((btn2.getText() == pl[3] && btn6.getText() == en[3]) || (btn2.getText() == en[3] && btn6.getText() == pl[3])) {
                        btn2.setBackgroundResource(R.drawable.button_border_purple);
                        btn6.setBackgroundResource(R.drawable.button_border_purple);
                        btn6.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click6 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn6.setText(" ");
                                btn2.setText(" ");
                            }
                        }, 2000);
                        click2 = false;
                        click6 = false;
                    }
                } else if (click3 == true) {
                    if ((btn3.getText() == pl[0] && btn6.getText() == en[0]) || (btn3.getText() == en[0] && btn6.getText() == pl[0])) {
                        btn3.setBackgroundResource(R.drawable.button_border_blue);
                        btn6.setBackgroundResource(R.drawable.button_border_blue);
                        btn6.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click6 = false;
                    } else if ((btn3.getText() == pl[1] && btn6.getText() == en[1]) || (btn3.getText() == en[1] && btn6.getText() == pl[1])) {
                        btn3.setBackgroundResource(R.drawable.button_border_pink);
                        btn6.setBackgroundResource(R.drawable.button_border_pink);
                        btn6.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click6 = false;
                    } else if ((btn3.getText() == pl[2] && btn6.getText() == en[2]) || (btn3.getText() == en[2] && btn6.getText() == pl[2])) {
                        btn3.setBackgroundResource(R.drawable.button_border_yellow);
                        btn6.setBackgroundResource(R.drawable.button_border_yellow);
                        btn6.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click6 = false;
                    } else if ((btn3.getText() == pl[3] && btn6.getText() == en[3]) || (btn3.getText() == en[3] && btn6.getText() == pl[3])) {
                        btn3.setBackgroundResource(R.drawable.button_border_purple);
                        btn6.setBackgroundResource(R.drawable.button_border_purple);
                        btn6.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click6 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn6.setText(" ");
                                btn3.setText(" ");
                            }
                        }, 2000);
                        click3 = false;
                        click6 = false;
                    }
                } else if (click4 == true) {
                    if ((btn4.getText() == pl[0] && btn6.getText() == en[0]) || (btn4.getText() == en[0] && btn6.getText() == pl[0])) {
                        btn4.setBackgroundResource(R.drawable.button_border_blue);
                        btn6.setBackgroundResource(R.drawable.button_border_blue);
                        btn6.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click6 = false;
                    } else if ((btn4.getText() == pl[1] && btn6.getText() == en[1]) || (btn4.getText() == en[1] && btn6.getText() == pl[1])) {
                        btn4.setBackgroundResource(R.drawable.button_border_pink);
                        btn6.setBackgroundResource(R.drawable.button_border_pink);
                        btn6.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click6 = false;
                    } else if ((btn4.getText() == pl[2] && btn6.getText() == en[2]) || (btn4.getText() == en[2] && btn6.getText() == pl[2])) {
                        btn4.setBackgroundResource(R.drawable.button_border_yellow);
                        btn6.setBackgroundResource(R.drawable.button_border_yellow);
                        btn6.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click6 = false;
                    } else if ((btn4.getText() == pl[3] && btn6.getText() == en[3]) || (btn4.getText() == en[3] && btn6.getText() == pl[3])) {
                        btn4.setBackgroundResource(R.drawable.button_border_purple);
                        btn6.setBackgroundResource(R.drawable.button_border_purple);
                        btn6.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click6 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn6.setText(" ");
                                btn4.setText(" ");
                            }
                        }, 2000);
                        click4 = false;
                        click6 = false;
                    }
                } else if (click5 == true) {
                    if ((btn5.getText() == pl[0] && btn6.getText() == en[0]) || (btn5.getText() == en[0] && btn6.getText() == pl[0])) {
                        btn5.setBackgroundResource(R.drawable.button_border_blue);
                        btn6.setBackgroundResource(R.drawable.button_border_blue);
                        btn6.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click6 = false;
                    } else if ((btn5.getText() == pl[1] && btn6.getText() == en[1]) || (btn5.getText() == en[1] && btn6.getText() == pl[1])) {
                        btn5.setBackgroundResource(R.drawable.button_border_pink);
                        btn6.setBackgroundResource(R.drawable.button_border_pink);
                        btn6.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click6 = false;
                    } else if ((btn5.getText() == pl[2] && btn6.getText() == en[2]) || (btn5.getText() == en[2] && btn6.getText() == pl[2])) {
                        btn5.setBackgroundResource(R.drawable.button_border_yellow);
                        btn6.setBackgroundResource(R.drawable.button_border_yellow);
                        btn6.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click6 = false;
                    } else if ((btn5.getText() == pl[3] && btn6.getText() == en[3]) || (btn5.getText() == en[3] && btn6.getText() == pl[3])) {
                        btn5.setBackgroundResource(R.drawable.button_border_purple);
                        btn6.setBackgroundResource(R.drawable.button_border_purple);
                        btn6.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click6 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn6.setText(" ");
                                btn5.setText(" ");
                            }
                        }, 2000);
                        click5 = false;
                        click6 = false;
                    }
                } else if (click7 == true) {
                    if ((btn7.getText() == pl[0] && btn6.getText() == en[0]) || (btn7.getText() == en[0] && btn6.getText() == pl[0])) {
                        btn6.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setBackgroundResource(R.drawable.button_border_blue);
                        btn6.setClickable(false);
                        btn7.setClickable(false);
                        click6 = false;
                        click7 = false;
                    } else if ((btn7.getText() == pl[1] && btn6.getText() == en[1]) || (btn7.getText() == en[1] && btn6.getText() == pl[1])) {
                        btn6.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setBackgroundResource(R.drawable.button_border_pink);
                        btn6.setClickable(false);
                        btn7.setClickable(false);
                        click6 = false;
                        click7 = false;
                    } else if ((btn7.getText() == pl[2] && btn6.getText() == en[2]) || (btn7.getText() == en[2] && btn6.getText() == pl[2])) {
                        btn6.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setBackgroundResource(R.drawable.button_border_yellow);
                        btn6.setClickable(false);
                        btn7.setClickable(false);
                        click6 = false;
                        click7 = false;
                    } else if ((btn7.getText() == pl[3] && btn6.getText() == en[3]) || (btn7.getText() == en[3] && btn6.getText() == pl[3])) {
                        btn6.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setBackgroundResource(R.drawable.button_border_purple);
                        btn6.setClickable(false);
                        btn7.setClickable(false);
                        click6 = false;
                        click7 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn6.setText(" ");
                                btn7.setText(" ");
                            }
                        }, 2000);
                        click6 = false;
                        click7 = false;
                    }
                } else if (click8 == true) {
                    if ((btn8.getText() == pl[0] && btn6.getText() == en[0]) || (btn8.getText() == en[0] && btn6.getText() == pl[0])) {
                        btn6.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setBackgroundResource(R.drawable.button_border_blue);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        click6 = false;
                        click8 = false;
                    } else if ((btn8.getText() == pl[1] && btn6.getText() == en[1]) || (btn8.getText() == en[1] && btn6.getText() == pl[1])) {
                        btn6.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setBackgroundResource(R.drawable.button_border_pink);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        click6 = false;
                        click8 = false;
                    } else if ((btn8.getText() == pl[2] && btn6.getText() == en[2]) || (btn8.getText() == en[2] && btn6.getText() == pl[2])) {
                        btn6.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setBackgroundResource(R.drawable.button_border_yellow);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        click6 = false;
                        click8 = false;
                    } else if ((btn8.getText() == pl[3] && btn6.getText() == en[3]) || (btn8.getText() == en[3] && btn6.getText() == pl[3])) {
                        btn6.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setBackgroundResource(R.drawable.button_border_purple);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        click6 = false;
                        click8 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn6.setText(" ");
                                btn8.setText(" ");
                            }
                        }, 2000);
                        click6 = false;
                        click8 = false;
                    }
                }
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click7 = true;
                btn7.setText(pl[2]);

                if (click1 == true) {
                    if ((btn1.getText() == pl[0] && btn7.getText() == en[0]) || (btn1.getText() == en[0] && btn7.getText() == pl[0])) {
                        btn1.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click7 = false;
                    } else if ((btn1.getText() == pl[1] && btn7.getText() == en[1]) || (btn1.getText() == en[1] && btn7.getText() == pl[1])) {
                        btn1.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click7 = false;
                    } else if ((btn1.getText() == pl[2] && btn7.getText() == en[2]) || (btn1.getText() == en[2] && btn7.getText() == pl[2])) {
                        btn1.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click7 = false;
                    } else if ((btn1.getText() == pl[3] && btn7.getText() == en[3]) || (btn1.getText() == en[3] && btn7.getText() == pl[3])) {
                        btn1.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click7 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn7.setText(" ");
                                btn1.setText(" ");
                            }
                        }, 2000);
                        click1 = false;
                        click7 = false;
                    }
                } else if (click2 == true) {
                    if ((btn2.getText() == pl[0] && btn7.getText() == en[0]) || (btn2.getText() == en[0] && btn7.getText() == pl[0])) {
                        btn2.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click7 = false;
                    } else if ((btn2.getText() == pl[1] && btn7.getText() == en[1]) || (btn2.getText() == en[1] && btn7.getText() == pl[1])) {
                        btn2.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click7 = false;
                    } else if ((btn2.getText() == pl[2] && btn7.getText() == en[2]) || (btn2.getText() == en[2] && btn7.getText() == pl[2])) {
                        btn2.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click7 = false;
                    } else if ((btn2.getText() == pl[3] && btn7.getText() == en[3]) || (btn2.getText() == en[3] && btn7.getText() == pl[3])) {
                        btn2.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click7 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn7.setText(" ");
                                btn2.setText(" ");
                            }
                        }, 2000);
                        click2 = false;
                        click7 = false;
                    }
                } else if (click3 == true) {
                    if ((btn3.getText() == pl[0] && btn7.getText() == en[0]) || (btn3.getText() == en[0] && btn7.getText() == pl[0])) {
                        btn3.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click7 = false;
                    } else if ((btn3.getText() == pl[1] && btn7.getText() == en[1]) || (btn3.getText() == en[1] && btn7.getText() == pl[1])) {
                        btn3.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click7 = false;
                    } else if ((btn3.getText() == pl[2] && btn7.getText() == en[2]) || (btn3.getText() == en[2] && btn7.getText() == pl[2])) {
                        btn3.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click7 = false;
                    } else if ((btn3.getText() == pl[3] && btn7.getText() == en[3]) || (btn3.getText() == en[3] && btn7.getText() == pl[3])) {
                        btn3.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click7 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn7.setText(" ");
                                btn3.setText(" ");
                            }
                        }, 2000);
                        click3 = false;
                        click7 = false;
                    }
                } else if (click4 == true) {
                    if ((btn4.getText() == pl[0] && btn7.getText() == en[0]) || (btn4.getText() == en[0] && btn7.getText() == pl[0])) {
                        btn4.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click7 = false;
                    } else if ((btn4.getText() == pl[1] && btn7.getText() == en[1]) || (btn4.getText() == en[1] && btn7.getText() == pl[1])) {
                        btn4.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click7 = false;
                    } else if ((btn4.getText() == pl[2] && btn7.getText() == en[2]) || (btn4.getText() == en[2] && btn7.getText() == pl[2])) {
                        btn4.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click7 = false;
                    } else if ((btn4.getText() == pl[3] && btn7.getText() == en[3]) || (btn4.getText() == en[3] && btn7.getText() == pl[3])) {
                        btn4.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click7 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn7.setText(" ");
                                btn4.setText(" ");
                            }
                        }, 2000);
                        click4 = false;
                        click7 = false;
                    }
                } else if (click5 == true) {
                    if ((btn5.getText() == pl[0] && btn7.getText() == en[0]) || (btn5.getText() == en[0] && btn7.getText() == pl[0])) {
                        btn5.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click7 = false;
                    } else if ((btn5.getText() == pl[1] && btn7.getText() == en[1]) || (btn5.getText() == en[1] && btn7.getText() == pl[1])) {
                        btn5.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click7 = false;
                    } else if ((btn5.getText() == pl[2] && btn7.getText() == en[2]) || (btn5.getText() == en[2] && btn7.getText() == pl[2])) {
                        btn5.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click7 = false;
                    } else if ((btn5.getText() == pl[3] && btn7.getText() == en[3]) || (btn5.getText() == en[3] && btn7.getText() == pl[3])) {
                        btn5.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click7 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn7.setText(" ");
                                btn5.setText(" ");
                            }
                        }, 2000);
                        click5 = false;
                        click7 = false;
                    }
                } else if (click6 == true) {
                    if ((btn6.getText() == pl[0] && btn7.getText() == en[0]) || (btn6.getText() == en[0] && btn7.getText() == pl[0])) {
                        btn6.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click7 = false;
                    } else if ((btn6.getText() == pl[1] && btn7.getText() == en[1]) || (btn6.getText() == en[1] && btn7.getText() == pl[1])) {
                        btn6.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click7 = false;
                    } else if ((btn6.getText() == pl[2] && btn7.getText() == en[2]) || (btn6.getText() == en[2] && btn7.getText() == pl[2])) {
                        btn6.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click7 = false;
                    } else if ((btn6.getText() == pl[3] && btn7.getText() == en[3]) || (btn6.getText() == en[3] && btn7.getText() == pl[3])) {
                        btn6.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click7 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn7.setText(" ");
                                btn6.setText(" ");
                            }
                        }, 2000);
                        click6 = false;
                        click7 = false;
                    }
                } else if (click8 == true) {
                    if ((btn8.getText() == pl[0] && btn7.getText() == en[0]) || (btn8.getText() == en[0] && btn7.getText() == pl[0])) {
                        btn7.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setClickable(false);
                        btn8.setClickable(false);
                        click7 = false;
                        click8 = false;
                    } else if ((btn8.getText() == pl[1] && btn7.getText() == en[1]) || (btn8.getText() == en[1] && btn7.getText() == pl[1])) {
                        btn7.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setClickable(false);
                        btn8.setClickable(false);
                        click7 = false;
                        click8 = false;
                    } else if ((btn8.getText() == pl[2] && btn7.getText() == en[2]) || (btn8.getText() == en[2] && btn7.getText() == pl[2])) {
                        btn7.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setClickable(false);
                        btn8.setClickable(false);
                        click7 = false;
                        click8 = false;
                    } else if ((btn8.getText() == pl[3] && btn7.getText() == en[3]) || (btn8.getText() == en[3] && btn7.getText() == pl[3])) {
                        btn7.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setClickable(false);
                        btn8.setClickable(false);
                        click7 = false;
                        click8 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn7.setText(" ");
                                btn8.setText(" ");
                            }
                        }, 2000);
                        click7 = false;
                        click8 = false;
                    }
                }
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click8 = true;
                btn8.setText(en[1]);

                if (click1 == true) {
                    if ((btn1.getText() == pl[0] && btn8.getText() == en[0]) || (btn1.getText() == en[0] && btn8.getText() == pl[0])) {
                        btn1.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click8 = false;
                    } else if ((btn1.getText() == pl[1] && btn8.getText() == en[1]) || (btn1.getText() == en[1] && btn8.getText() == pl[1])) {
                        btn1.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click8 = false;
                    } else if ((btn1.getText() == pl[2] && btn8.getText() == en[2]) || (btn1.getText() == en[2] && btn8.getText() == pl[2])) {
                        btn1.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click8 = false;
                    } else if ((btn1.getText() == pl[3] && btn8.getText() == en[3]) || (btn1.getText() == en[3] && btn8.getText() == pl[3])) {
                        btn1.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click8 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn8.setText(" ");
                                btn1.setText(" ");
                            }
                        }, 2000);
                        click1 = false;
                        click8 = false;
                    }
                } else if (click2 == true) {
                    if ((btn2.getText() == pl[0] && btn8.getText() == en[0]) || (btn2.getText() == en[0] && btn8.getText() == pl[0])) {
                        btn2.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click8 = false;
                    } else if ((btn2.getText() == pl[1] && btn8.getText() == en[1]) || (btn2.getText() == en[1] && btn8.getText() == pl[1])) {
                        btn2.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click8 = false;
                    } else if ((btn2.getText() == pl[2] && btn8.getText() == en[2]) || (btn2.getText() == en[2] && btn8.getText() == pl[2])) {
                        btn2.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click8 = false;
                    } else if ((btn2.getText() == pl[3] && btn8.getText() == en[3]) || (btn2.getText() == en[3] && btn8.getText() == pl[3])) {
                        btn2.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click8 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn8.setText(" ");
                                btn2.setText(" ");
                            }
                        }, 2000);
                        click2 = false;
                        click8 = false;
                    }
                } else if (click3 == true) {
                    if ((btn3.getText() == pl[0] && btn8.getText() == en[0]) || (btn3.getText() == en[0] && btn8.getText() == pl[0])) {
                        btn3.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click8 = false;
                    } else if ((btn3.getText() == pl[1] && btn8.getText() == en[1]) || (btn3.getText() == en[1] && btn8.getText() == pl[1])) {
                        btn3.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click8 = false;
                    } else if ((btn3.getText() == pl[2] && btn8.getText() == en[2]) || (btn3.getText() == en[2] && btn8.getText() == pl[2])) {
                        btn3.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click8 = false;
                    } else if ((btn3.getText() == pl[3] && btn8.getText() == en[3]) || (btn3.getText() == en[3] && btn8.getText() == pl[3])) {
                        btn3.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click8 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn8.setText(" ");
                                btn3.setText(" ");
                            }
                        }, 2000);
                        click3 = false;
                        click8 = false;
                    }
                } else if (click4 == true) {
                    if ((btn4.getText() == pl[0] && btn8.getText() == en[0]) || (btn4.getText() == en[0] && btn8.getText() == pl[0])) {
                        btn4.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click8 = false;
                    } else if ((btn4.getText() == pl[1] && btn8.getText() == en[1]) || (btn4.getText() == en[1] && btn8.getText() == pl[1])) {
                        btn4.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click8 = false;
                    } else if ((btn4.getText() == pl[2] && btn8.getText() == en[2]) || (btn4.getText() == en[2] && btn8.getText() == pl[2])) {
                        btn4.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click8 = false;
                    } else if ((btn4.getText() == pl[3] && btn8.getText() == en[3]) || (btn4.getText() == en[3] && btn8.getText() == pl[3])) {
                        btn4.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click8 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn8.setText(" ");
                                btn4.setText(" ");
                            }
                        }, 2000);
                        click4 = false;
                        click8 = false;
                    }
                } else if (click5 == true) {
                    if ((btn5.getText() == pl[0] && btn8.getText() == en[0]) || (btn5.getText() == en[0] && btn8.getText() == pl[0])) {
                        btn5.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click8 = false;
                    } else if ((btn5.getText() == pl[1] && btn8.getText() == en[1]) || (btn5.getText() == en[1] && btn8.getText() == pl[1])) {
                        btn5.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click8 = false;
                    } else if ((btn5.getText() == pl[2] && btn8.getText() == en[2]) || (btn5.getText() == en[2] && btn8.getText() == pl[2])) {
                        btn5.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click8 = false;
                    } else if ((btn5.getText() == pl[3] && btn8.getText() == en[3]) || (btn5.getText() == en[3] && btn8.getText() == pl[3])) {
                        btn5.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click8 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn8.setText(" ");
                                btn5.setText(" ");
                            }
                        }, 2000);
                        click5 = false;
                        click8 = false;
                    }
                } else if (click6 == true) {
                    if ((btn6.getText() == pl[0] && btn8.getText() == en[0]) || (btn6.getText() == en[0] && btn8.getText() == pl[0])) {
                        btn6.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click8 = false;
                    } else if ((btn6.getText() == pl[1] && btn8.getText() == en[1]) || (btn6.getText() == en[1] && btn8.getText() == pl[1])) {
                        btn6.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click8 = false;
                    } else if ((btn6.getText() == pl[2] && btn8.getText() == en[2]) || (btn6.getText() == en[2] && btn8.getText() == pl[2])) {
                        btn6.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click8 = false;
                    } else if ((btn6.getText() == pl[3] && btn8.getText() == en[3]) || (btn6.getText() == en[3] && btn8.getText() == pl[3])) {
                        btn6.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click8 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn8.setText(" ");
                                btn6.setText(" ");
                            }
                        }, 2000);
                        click6 = false;
                        click8 = false;
                    }
                } else if (click7 == true) {
                    if ((btn7.getText() == pl[0] && btn8.getText() == en[0]) || (btn7.getText() == en[0] && btn8.getText() == pl[0])) {
                        btn7.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setClickable(false);
                        btn7.setClickable(false);
                        click7 = false;
                        click8 = false;
                    } else if ((btn7.getText() == pl[1] && btn8.getText() == en[1]) || (btn7.getText() == en[1] && btn8.getText() == pl[1])) {
                        btn7.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setClickable(false);
                        btn7.setClickable(false);
                        click7 = false;
                        click8 = false;
                    } else if ((btn7.getText() == pl[2] && btn8.getText() == en[2]) || (btn7.getText() == en[2] && btn8.getText() == pl[2])) {
                        btn7.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setClickable(false);
                        btn7.setClickable(false);
                        click7 = false;
                        click8 = false;
                    } else if ((btn7.getText() == pl[3] && btn8.getText() == en[3]) || (btn7.getText() == en[3] && btn8.getText() == pl[3])) {
                        btn7.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setClickable(false);
                        btn7.setClickable(false);
                        click7 = false;
                        click8 = false;
                    } else {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn8.setText(" ");
                                btn7.setText(" ");
                            }
                        }, 2000);
                        click7 = false;
                        click8 = false;
                    }
                }
            }
        });
    }
}
