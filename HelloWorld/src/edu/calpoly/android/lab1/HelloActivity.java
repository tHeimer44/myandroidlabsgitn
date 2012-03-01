package edu.calpoly.android.lab1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class HelloActivity extends Activity {
	TextView tv ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.namexml);
		Bundle extBundle = this.getIntent().getExtras();
		String name = extBundle.getString("NameUser");
		 tv = (TextView) findViewById(R.id.textView1);
		 tv.setText("Hello"+name);
	}
	
	

}
