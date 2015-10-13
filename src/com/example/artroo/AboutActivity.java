package com.example.artroo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		Intent intent=getIntent();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datalayout2);
		((TextView)findViewById(R.id.data_item)).setText(intent.getStringExtra(Constant.DATA_VALUE));	
		((TextView)findViewById(R.id.dateValue)).setText(intent.getStringExtra(Constant.DATE_DATA));
	}
	public void onHomeClick(View v) {			
    	startActivity(new Intent(this, MainActivity.class));
    	finish();    
	}


}