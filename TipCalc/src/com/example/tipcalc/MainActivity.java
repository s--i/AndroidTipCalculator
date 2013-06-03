package com.example.tipcalc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void calculateTip(View view) {
		InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE); 
		inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		
		EditText billAmount = (EditText) findViewById(R.id.billAmount);
		
		
		float bill;
		try {
			bill = Float.parseFloat(billAmount.getText().toString());
		} catch (NumberFormatException e) {
			bill = 0;
		}
		
		float tip = 0;
		switch(view.getId()) {
			case R.id.tenPercent:
				tip = this.calculateTip(bill, 10);
				break;
			case R.id.fifteenPercent:
				tip = this.calculateTip(bill, 15);
				break;
			case R.id.twentyPercent:
				tip = this.calculateTip(bill, 20);
				break;
			default:
				throw new RuntimeException("Unknow button ID");
		}
		
		TextView tipAmount = (TextView) findViewById(R.id.tipAmount);
		tipAmount.setText(String.format("Tip is $%.2f%n", tip));
	}
	
	private float calculateTip(float amount, float percentage) {
		return amount + (amount * (percentage / 100));
	}
}