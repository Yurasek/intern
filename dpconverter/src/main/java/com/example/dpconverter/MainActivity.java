package com.example.dpconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;
import java.util.List;

import static android.widget.AdapterView.*;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener{

    private int specPosition;
    private ListView listView;
    private EditText editText;
    private int[] multiplier = {75, 100, 150, 200, 300, 400};
    private List<Double> convertedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText2);
        editText.setText("40");
        Spinner density = (Spinner)findViewById(R.id.spinner);
        listView = (ListView)findViewById(R.id.listView);
        ArrayAdapter<CharSequence> densityType = ArrayAdapter.createFromResource(this,
                R.array.densitys_type, android.R.layout.simple_spinner_item);

        densityType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        density.setAdapter(densityType);
        density.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        specPosition = position;
        convertedValues = new ArrayList<>();
        for(int i = 0;i < multiplier.length;i++){
            convertedValues.add(multiplier[i]*(Double.parseDouble(editText.getText().toString())/multiplier[specPosition]));
        }
        ArrayAdapter<Double> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                convertedValues
        );
        listView.setAdapter(adapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }
}