package com.example.myaaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Bundle;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    RelativeLayout layout_joystick;
    ImageView image_joystick, image_border;
    TextView textView1, textView2, textView3, textView4, textView5;
    Button riight_click,left_click;
    public String message;
    JoyStickClass js;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        textView4 = (TextView)findViewById(R.id.textView4);
        textView5 = (TextView)findViewById(R.id.textView5);
        riight_click = (Button) findViewById(R.id.right);
        left_click = (Button) findViewById(R.id.left);
        layout_joystick = (RelativeLayout)findViewById(R.id.layout_joystick);

        riight_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send sendcode = new send();
                message = "B";
                sendcode.execute();
            }
        });
        left_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send sendcode = new send();
                message = "N";
                sendcode.execute();
            }
        });


        js = new JoyStickClass(getApplicationContext()
                , layout_joystick, R.drawable.image_button);
        js.setStickSize(150, 150);
        js.setLayoutSize(500, 500);
        js.setLayoutAlpha(150);
        js.setStickAlpha(100);
        js.setOffset(90);
        js.setMinimumDistance(50);

        layout_joystick.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                js.drawStick(arg1);
                if(arg1.getAction() == MotionEvent.ACTION_DOWN
                        || arg1.getAction() == MotionEvent.ACTION_MOVE) {
                    textView1.setText("X : " + String.valueOf(js.getX()));
                    textView2.setText("Y : " + String.valueOf(js.getY()));
                    textView3.setText("Angle : " + String.valueOf(js.getAngle()));
                    textView4.setText("Distance : " + String.valueOf(js.getDistance()));

                    int direction = js.get8Direction();
                    if(direction == JoyStickClass.STICK_UP) {
                        textView5.setText("Direction : Up");
                        send sendcode = new send();
                        message = "D";
                        sendcode.execute();
                    } else if(direction == JoyStickClass.STICK_UPRIGHT) {
                        textView5.setText("Direction : Up Right");
                        send sendcode = new send();
                        message = "La";
                        sendcode.execute();
                    } else if(direction == JoyStickClass.STICK_RIGHT) {
                        textView5.setText("Direction : Right");
                        send sendcode = new send();
                        message = "L";
                        sendcode.execute();
                    } else if(direction == JoyStickClass.STICK_DOWNRIGHT) {
                        textView5.setText("Direction : Down Right");
                        send sendcode = new send();
                        message = "Ll";
                        sendcode.execute();
                    } else if(direction == JoyStickClass.STICK_DOWN) {
                        textView5.setText("Direction : Down");
                        send sendcode = new send();
                        message = "U";
                        sendcode.execute();
                    } else if(direction == JoyStickClass.STICK_DOWNLEFT) {
                        textView5.setText("Direction : Down Left");
                        send sendcode = new send();
                        message = "Rr";
                        sendcode.execute();
                    } else if(direction == JoyStickClass.STICK_LEFT) {
                        textView5.setText("Direction : Left");
                        send sendcode = new send();
                        message = "R";
                        sendcode.execute();
                    } else if(direction == JoyStickClass.STICK_UPLEFT) {
                        textView5.setText("Direction : Up Left");
                        send sendcode = new send();
                        message = "Rd";
                        sendcode.execute();
                    } else if(direction == JoyStickClass.STICK_NONE) {
                        textView5.setText("Direction : Center");

                    }
                } else if(arg1.getAction() == MotionEvent.ACTION_UP) {
                    textView1.setText("X :");
                    textView2.setText("Y :");
                    textView3.setText("Angle :");
                    textView4.setText("Distance :");
                    textView5.setText("Direction :");
                }
                return true;
            }


        });


        }
    class send extends AsyncTask<Void,Void,Void> {
        Intent intent = getIntent();
        final String remo_ip = intent.getStringExtra(barcode.EXTRA_TEXT);
        Socket s;
        PrintWriter pw;

        @Override
        protected Void doInBackground(Void... params) {
            try {
                s = new Socket(remo_ip, 8000);
                pw = new PrintWriter(s.getOutputStream());
                pw.write(message);
                pw.flush();
                pw.close();
                s.close();
            } catch (UnknownHostException e) {
                System.out.println("Fail");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Fail");
                e.printStackTrace();
            }
            return null;
        }
    }

    }



