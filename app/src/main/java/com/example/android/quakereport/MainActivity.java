package com.example.android.quakereport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_TEXT = "com.example.android.quakereport.EXTRA_TEXT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView minMag =(TextView)findViewById(R.id.minMag);
        setSeekBarListener1(minMag);

        final TextView limit =(TextView)findViewById(R.id.limit);
        setSeekBarListener2(limit);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEarthquakeActivity(minMag, limit);
            }
        });
    }

    public void openEarthquakeActivity(final TextView minMag, final TextView limit) {


        String text = build_request_url(minMag.getText().toString(), limit.getText().toString());

        Intent intent = new Intent(this, EarthquakeActivity.class);
        intent.putExtra(EXTRA_TEXT, text);
        startActivity(intent);
    }

    private  void setSeekBarListener1(final TextView minMag) {
        SeekBar customSeekBar =(SeekBar)findViewById(R.id.seekBar1);
        // perform seek bar change listener event used for getting the progress value
        customSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                minMag.setText("" + ((float)progressChangedValue)/10);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private  void setSeekBarListener2(final TextView limit) {
        SeekBar customSeekBar =(SeekBar)findViewById(R.id.seekBar2);
        // perform seek bar change listener event used for getting the progress value
        customSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                limit.setText("" + (progressChangedValue));
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    private static String build_request_url(String minMug, String limit){
        String min = "&minmag=" + minMug;
        String lim = "&limit=" + limit;
        return ("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time"  + min + lim);
    }
}