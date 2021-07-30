package com.example.traffic_light;



import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout b1, b2, b3;
    private Button button1;
    private boolean start_stop = false;
    private int counter=0;

    protected void onCreate(Bundle savesInstanceState) {

        super.onCreate(savesInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.bulb_1);
        b2 = findViewById(R.id.bulb_2);
        b3 = findViewById(R.id.bulb_3);
        button1= findViewById(R.id.button1);

    }

    @SuppressLint("SetTextI18n")
    public void onClickStart(View view) {
        if (!start_stop) {
            button1.setText("stop");
            start_stop=true;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (start_stop) {
                        counter++;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                switch (counter){
                                    case 1:
                                        b1.setBackgroundColor(getResources().getColor(R.color.green));
                                        b2.setBackgroundColor(getResources().getColor(R.color.grey));
                                        b3.setBackgroundColor(getResources().getColor(R.color.grey));
                                        break;
                                    case 2:
                                        b1.setBackgroundColor(getResources().getColor(R.color.grey));
                                        b2.setBackgroundColor(getResources().getColor(R.color.green));
                                        b3.setBackgroundColor(getResources().getColor(R.color.grey));
                                        break;
                                    case 3:
                                        b1.setBackgroundColor(getResources().getColor(R.color.grey));
                                        b2.setBackgroundColor(getResources().getColor(R.color.grey));
                                        b3.setBackgroundColor(getResources().getColor(R.color.green));
                                        counter=0;
                                        break;
                                }
                            }
                        });
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }).start();

        }else {
            start_stop=false;
            button1.setText("start");
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        start_stop=false;
    }
}
