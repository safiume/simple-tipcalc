/*
 * Copyright (C) 2014 Shannon A. Fiume
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.tipcalc;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import java.text.DecimalFormat;

public class TipCalc extends Activity {
	public EditText etValue;
	public TextView tipLabel;
	public String tipButton;
	public String tipPBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tip_calc);
		// View structure for etValue EditText field
		etValue = (EditText) findViewById(R.id.etValue);
		// View structure for tipLabel return field
		tipLabel = (TextView) findViewById(R.id.tipLabel);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tip_calc, menu);
		return true;
	}
	
	// Calculate Tip on Doubles
	private String calcTip(Double payTotal, Double tipPercent){
		DecimalFormat currencyUS = new DecimalFormat("$000.00");
		double percent = .01;
		double Tip = percent * payTotal * tipPercent;
		String theTip = currencyUS.format(Tip);
		return theTip;
	}

	// Handle a submission click
	public void onSubmit(View v){
		
		// Return the structure of the object that was clicked 
		// and grab it's text label to determine the tip percent
		
			 Button clickedButton = (Button) findViewById(v.getId());
			 tipButton = clickedButton.getText().toString();

		// Handle % and spaces before the % if any
		if (tipButton.contains("%") || tipButton.contains(" ")) {
			if (tipButton.contains(" ")) {
				// String string = "20 %";
				String[] tPercents = tipButton.split(" ");
				tipPBtn = tPercents[0];
			} else {
			// String string = "20%";
			String[] tPercents = tipButton.split("%");
			tipPBtn = tPercents[0];
			}
		} else {
		    throw new IllegalArgumentException("Tip Button " + tipButton + " does not contain %.");
		}
		
		Double tipPercent = Double.parseDouble(tipPBtn);
		String etTextValue = etValue.getText().toString();
		Double tipTotal = Double.valueOf(etTextValue);
		// Calculate the tip
		String tipValue = calcTip(tipTotal, tipPercent);
		tipLabel.setText(tipValue);
	}
}