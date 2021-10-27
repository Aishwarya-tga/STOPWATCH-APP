package com.example.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;


public class MainActivity extends Activity {

    private int sec = 0;
    private boolean run;
    private boolean wasrunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null) {
            sec = savedInstanceState.getInt("sec");
            run = savedInstanceState.getBoolean("run");
            wasrunning = savedInstanceState.getBoolean("wasrunning");
        }
        Timerfunc();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("sec",sec);
        savedInstanceState.putBoolean("run",run);
        savedInstanceState.putBoolean("wasrunning",wasrunning);
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasrunning = run;
        run = false;
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(wasrunning) {
            run = true;
        }
    }

    public void EnterStart(View view)
    {
        run = true;
    }
    public void EnterStop(View view)
    {
        run = false;
    }
    public void EnterReset(View view)
    {
        sec = 0;
        run = false;
    }

    private void Timerfunc()
    {
        final TextView timers = (TextView) findViewById(R.id.timer);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hrs = sec/3600;
                int min = (sec%3600)/60;
                int secs = sec%60;
                String time = String.format(Locale.getDefault(),"%d:%02d:%02d",hrs,min,secs);
                timers.setText(time);
                if(run) {
                    sec++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }

}