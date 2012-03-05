package edu.calpoly.android.lab2;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class SimpleJokeList extends Activity {

	/** Contains the list Jokes the Activity will present to the user **/
	protected ArrayList<Joke> m_arrJokeList;

	/**
	 * LinearLayout used for maintaining a list of Views that each display Jokes
	 **/
	protected LinearLayout m_vwJokeLayout;

	/**
	 * EditText used for entering text for a new Joke to be added to
	 * m_arrJokeList.
	 **/
	protected boolean colorTv=true;
	protected ScrollView m_vwScrollview;
	protected LinearLayout  rootLiner;
	protected LinearLayout  HeadLiner;

	
	protected EditText m_vwJokeEditText;

	/**
	 * Button used for creating and adding a new Joke to m_arrJokeList using the
	 * text entered in m_vwJokeEditText.
	 **/
	protected Button m_vwJokeButton;
	
	/**
	 * Background Color values used for alternating between light and dark rows
	 * of Jokes.  
	 */
	protected int m_nDarkColor;
	protected int m_nLightColor;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO
		initLayout();
		initAddJokeListeners();
		m_arrJokeList=new ArrayList<Joke>();
		for (String S : this.getResources().getStringArray(R.array.jokeList)) {
			addJoke(S);	
		}

	}
	
	/**
	 * Method used to encapsulate the code that initializes and sets the Layout
	 * for this Activity. 
	 */
	protected void initLayout() {
		// TODO
		
		rootLiner=new LinearLayout(this);
		HeadLiner=new LinearLayout(this);
		rootLiner.setOrientation(LinearLayout.VERTICAL);
		HeadLiner.setOrientation(LinearLayout.HORIZONTAL);	
		m_vwJokeButton = new Button(this);
		m_vwJokeButton.setText("Add Joke");
		HeadLiner.addView(m_vwJokeButton);
		m_vwJokeEditText= new EditText(this);
		m_vwJokeEditText.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		HeadLiner.addView(m_vwJokeEditText);
		
		rootLiner.addView(HeadLiner);
		m_vwJokeLayout = new LinearLayout(this);
		m_vwJokeLayout.setOrientation(LinearLayout.VERTICAL);		
		m_vwScrollview = new ScrollView(this);
		m_vwScrollview.addView(m_vwJokeLayout);
		
		rootLiner.addView(m_vwScrollview);
		setContentView(rootLiner);
	}
	
	/**
	 * Method used to encapsulate the code that initializes and sets the Event
	 * Listeners which will respond to requests to "Add" a new Joke to the 
	 * list. 
	 */
	protected void initAddJokeListeners() {
		// TODO
		
		m_vwJokeEditText.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				
				if (keyCode== KeyEvent.KEYCODE_ENTER){addJoke(m_vwJokeEditText.getText().toString());return true;}
				if (keyCode== KeyEvent.KEYCODE_BACK){addJoke(m_vwJokeEditText.getText().toString());return true;}
				if (keyCode== KeyEvent.KEYCODE_DPAD_CENTER){addJoke(m_vwJokeEditText.getText().toString());return true;}
				return false;
				
			}
		});

		m_vwJokeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				addJoke(m_vwJokeEditText.getText().toString());
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(m_vwJokeEditText.getWindowToken(),
						0);

			}
		});
		
	}

	/**
	 * Method used for encapsulating the logic necessary to properly initialize
	 * a new joke, add it to m_arrJokeList, and display it on screen.
	 * 
	 * @param strJoke
	 *            A string containing the text of the Joke to add.
	 */
	protected void addJoke(String strJoke) {
		// TODO
		TextView tv = new TextView(this);
		tv.setText(strJoke);
		tv.setTextSize((float)16.0);
		if (colorTv = !colorTv)
		{tv.setBackgroundColor(getResources().getColor(R.color.dark));} 
		else 
		{tv.setBackgroundColor(getResources().getColor(R.color.light));}
		m_vwJokeLayout.addView(tv);
		m_arrJokeList.add(new Joke(strJoke));
	}
}