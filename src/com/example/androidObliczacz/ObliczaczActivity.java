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
import android.widget.Toast;

/**
 * Main activity for Obliczacz
 * 
 * @author panslaw
 *
 */
public class ObliczaczActivity extends Activity implements OnClickListener {
	/**
	 * Resource for 'count' button
	 */
	Button btnCount;
	
	/**
	 * Resource for input points value
	 */
	EditText inputValue;
	
	/**
	 * Table with results
	 */
	TableLayout scores;
	
	/**
	 * Percentage upper bound
	 */
	double[] threshold = {0.6, 0.72, 0.8, 0.9, 0.95, 1};
	
	/**
	 * Percentage lower bound
	 */
	double[] thresholdMin = {0, 0.61, 0.73, 0.81, 0.91, 0.96};
	
	/**
	 * Table with percentage interval
	 */
	String[] keys = {"0-60", "61-72", "73-80", "81-90", "91-95", "96-100"};
	
	/**
	 * Table with evaluations
	 */
	String[] evaluation = {"2", "3", "3+", "4", "4+", "5"};
	
	/**
	 * Number format with two places
	 */
	DecimalFormat df = new DecimalFormat("##.##");
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // click button listener
        btnCount = (Button) findViewById(R.id.btnCount);
        btnCount.setOnClickListener(this);
    }
    
    /**
     * Click event - method get input value and count every evaluation 
     * intervals
     */
    public void onClick(View view) {
    	
    	// table layout for results
    	scores = (TableLayout) findViewById(R.id.scores);
    	scores.removeAllViews();
    	
    	// input points resource
    	inputValue = (EditText) findViewById(R.id.points);
    	    	
    	// check if user put something
    	if (inputValue.getText().toString().length() > 0) {
    		// parse to integer
    		Integer value = Integer.parseInt(inputValue.getText().toString());
    		
	    	// add labels - first th row
	    	TableRow th = new TableRow(this);
	    	th.setBackgroundColor(Color.rgb(90, 146, 206));
	    	
	    	// set first three columns
	    	TextView thKeys = new TextView(this);
	    	thKeys.setText(R.string.percentage);
	    	th.addView(thKeys);
	    	
	    	TextView thPoints = new TextView(this);
	    	thPoints.setText(R.string.points);
	    	th.addView(thPoints);
	    	
	    	TextView thEvaluation = new TextView(this);
	    	thEvaluation.setText(R.string.evaluations);
	    	th.addView(thEvaluation);
	    	
	    	// add to table
	    	scores.addView(th, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	    	
	    	// if we've got number we can build rest of table
	    	for (int i = 0; i < threshold.length; i++) {
	    		// new table row
				TableRow tr = new TableRow(this);
				
				// set color background color for odd row
				if ((i % 2) != 0)
					tr.setBackgroundColor(Color.rgb(237, 244, 252));
				
				// write percentage to column
				TextView labelKeys = new TextView(this);
				labelKeys.setText(keys[i]);
				tr.addView(labelKeys);
				
				// count and write points interval
				TextView labelValue = new TextView(this);
				labelValue.setText(df.format(value*thresholdMin[i]) + " - " + df.format(value*threshold[i]));
				tr.addView(labelValue);
				
				// write evaluation
				TextView labelEvaluation = new TextView(this);
				labelEvaluation.setText(evaluation[i]);
				tr.addView(labelEvaluation);
				
				// add to table
				scores.addView(tr, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			}
    	} else {
    		// if user click with empty input show msg
    		Toast.makeText(getApplicationContext(), R.string.write_points, Toast.LENGTH_SHORT).show();
    	}
    }
}