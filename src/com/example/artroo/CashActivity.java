package com.example.artroo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class CashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deteditor);
		Intent intent = getIntent();
		Spinner spinnerFrom = (Spinner) findViewById(R.id.deteditor_from);
		String selectedSpinnerValueFrom = spinnerFrom.getSelectedItem().toString();
		Spinner spinnerTo = (Spinner) findViewById(R.id.deteditor_to);
		String selectedSpinnerValueTo = spinnerTo.getSelectedItem().toString();
		EditText moneyEditText=(EditText)findViewById(R.id.deteditor_money);
		EditText noteEditText=(EditText)findViewById(R.id.deteditor_note);
		
		


	}
	
	public void Create(View view)
	{
		
	}
	
	public void Cancel(View view)
	{
		
	}




}

