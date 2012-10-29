package com.example.androidObliczacz;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

public class ObliczaczActivity extends Activity implements OnClickListener {
	Button btnCount;
	EditText inputValue;
	TableLayout scores;
	double[] threshold = {0.6, 0.72, 0.8, 0.9, 0.95, 1};
	double[] thresholdMin = {0, 0.61, 0.73, 0.81, 0.91, 0.96};
	String[] keys = {"0-60", "61-72", "73-80", "81-90", "91-95", "96-100"};
	String[] evaluation = {"2", "3", "3+", "4", "4+", "5"};
	DecimalFormat df = new DecimalFormat("##.##");
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btnCount = (Button) findViewById(R.id.btnCount);
        btnCount.setOnClickListener(this);
    }
    
    public void onClick(View view) {
    	
    	scores = (TableLayout) findViewById(R.id.scores);
    	scores.removeAllViews();
    	
    	inputValue = (EditText) findViewById(R.id.points);
    	Integer value = Integer.parseInt(inputValue.getText().toString());
    	
    	// add labels
    	TableRow th = new TableRow(this);
    	th.setBackgroundColor(Color.rgb(90, 146, 206));
    	TextView thKeys = new TextView(this);
    	thKeys.setText("Procenty");
    	th.addView(thKeys);
    	
    	TextView thPoints = new TextView(this);
    	thPoints.setText("Punkty");
    	th.addView(thPoints);
    	
    	TextView thEvaluation = new TextView(this);
    	thEvaluation.setText("Oceny");
    	th.addView(thEvaluation);
    	scores.addView(th, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    	
    	for (int i = 0; i < threshold.length; i++) {
			//result = result + keys[i] + " - " + df.format(value*threshold[i]) + "\n";
			TableRow tr = new TableRow(this);
			
			if ((i % 2) == 0)
				tr.setBackgroundColor(Color.rgb(237, 244, 252));
			
			TextView labelKeys = new TextView(this);
			labelKeys.setText(keys[i]);
			tr.addView(labelKeys);
			
			TextView labelValue = new TextView(this);
			labelValue.setText(df.format(value*thresholdMin[i]) + "-" + df.format(value*threshold[i]));
			tr.addView(labelValue);
			
			TextView labelEvaluation = new TextView(this);
			labelEvaluation.setText(evaluation[i]);
			tr.addView(labelEvaluation);
			
			scores.addView(tr, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		}
    }
    
}
