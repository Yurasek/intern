package com.example.customkeyboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements KeyboardListener{

    final double MAX_VALUE = 9999.99;

    double currentValue = 0.0;

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.show_value);
    }

    @Override
    public void onClickedNumberView(int digit) {
        final double newValue = currentValue * 10 + digit;
        if(currentValue <= MAX_VALUE) {
            textView.setText(Double.toString(currentValue));
        }
    }

    @Override
    public void onClickedDeleteView() {

    }

    @Override
    public void onClickedDeleteSymbolView() {

    }

    @Override
    public void onClickedEnterView() {

    }
}
