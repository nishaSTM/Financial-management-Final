package com.example.artroo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SpendActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		Intent intent=getIntent();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datalayout);
		((TextView)findViewById(R.id.data_item)).setText(intent.getStringExtra(Constant.DATA_VALUE));	
	}

	public void onHomeClick(View v) {			
    	startActivity(new Intent(this, MainActivity.class));
    	finish();    
	}


}
