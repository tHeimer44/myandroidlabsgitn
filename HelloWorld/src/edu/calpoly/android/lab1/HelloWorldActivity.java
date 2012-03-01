package edu.calpoly.android.lab1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class HelloWorldActivity extends Activity implements OnClickListener{
	android.widget.EditText myEdit;
	EditText myEdit2;
	Button myButton;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        myEdit =(EditText) findViewById(R.id.editText1);
        myButton = (Button) findViewById(R.id.button1);
        myButton.setOnClickListener(this);
        
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, HelloActivity.class);
		intent.putExtra("NameUser", myEdit.getText().toString());
		startActivity(intent);
		// TODO Auto-generated method stub
	}

}