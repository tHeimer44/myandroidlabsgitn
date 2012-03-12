package edu.calpoly.android.lab3;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

public class AdvancedJokeList extends Activity implements OnMenuItemClickListener {

	protected boolean colorTv=true;
	protected LinearLayout  rootLiner;
	protected LinearLayout  HeadLiner;
	protected ScrollView m_vwScrollview;
	
	/**
	 * Contains the name of the Author for the jokes.
	 */
	protected String m_strAuthorName;

	/**
	 * Contains the list of Jokes the Activity will present to the user.
	 **/
	protected ArrayList<Joke> m_arrJokeList;

	/**
	 * Adapter used to bind an AdapterView to List of Jokes.
	 */
	protected JokeListAdapter m_jokeAdapter;

	/**
	 * ViewGroup used for maintaining a list of Views that each display Jokes.
	 **/
	protected ListView m_vwJokeLayout;

	/**
	 * EditText used for entering text for a new Joke to be added to
	 * m_arrJokeList.
	 **/
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
		
	/**
	 * Context-Menu MenuItem ID's
	 * IMPORTANT: You must use these when creating your MenuItems or the tests
	 * used to grade your submission will fail.
	 */
	protected static final int REMOVE_JOKE_MENUITEM = Menu.FIRST;
	protected static final int UPLOAD_JOKE_MENUITEM = Menu.FIRST + 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initLayout();
		initAddJokeListeners();
		registerForContextMenu(m_vwJokeLayout);
		m_arrJokeList=new ArrayList<Joke>();
		m_jokeAdapter= new JokeListAdapter(this, m_arrJokeList);
		m_vwJokeLayout.setAdapter(m_jokeAdapter);
		m_vwJokeLayout.setOnItemLongClickListener(m_jokeAdapter)	;							// My почему адаптер?
		m_strAuthorName=getResources().getString(R.string.author_name);
		for (String S : this.getResources().getStringArray(R.array.jokeList)) {
			addJoke(new Joke(S,m_strAuthorName));	
		}
		// TODO
	}

	/**
	 * Method is used to encapsulate the code that initializes and sets the
	 * Layout for this Activity.
	 */
	protected void initLayout() {
		// TODO
		setContentView(R.layout.advanced);
		m_vwJokeButton=(Button)findViewById(R.id.addJokeButton);
		m_vwJokeEditText=(EditText)findViewById(R.id.newJokeEditText);
		m_vwJokeLayout=(ListView) findViewById(R.id.jokeListViewGroup);
		m_vwJokeLayout.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	}

	/**
	 * Method is used to encapsulate the code that initializes and sets the
	 * Event Listeners which will respond to requests to "Add" a new Joke to the
	 * list.
	 */
	protected void initAddJokeListeners() {
		// TODO
		m_vwJokeEditText.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub

				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					addJoke(new Joke(m_vwJokeEditText.getText().toString(),m_strAuthorName));
					return true;
				}
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					addJoke(new Joke(m_vwJokeEditText.getText().toString(),m_strAuthorName));
					return true;
				}
				if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
					addJoke(new Joke(m_vwJokeEditText.getText().toString(),m_strAuthorName));
					return true;
				}
				return false;

			}
		});

		m_vwJokeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				addJoke(new Joke(m_vwJokeEditText.getText().toString(),m_strAuthorName));
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(m_vwJokeEditText.getWindowToken(),0);
				
			}
		});
	}

	/**
	 * Method used for encapsulating the logic necessary to properly add a new
	 * Joke to m_arrJokeList, and display it on screen.
	 * 
	 * @param joke
	 *            The Joke to add to list of Jokes.
	 */
	protected void addJoke(Joke joke) {
		// TODO
	/*	JokeView m_vwJokeBlock = new JokeView(this, joke);
		if (colorTv = !colorTv)
		{m_vwJokeBlock.setBackgroundColor(getResources().getColor(R.color.dark));} 
		else 
		{m_vwJokeBlock.setBackgroundColor(getResources().getColor(R.color.light));}
		m_vwJokeLayout.addView(m_vwJokeBlock);
		*/
		m_arrJokeList.add(joke);
		m_jokeAdapter.notifyDataSetChanged();
		

	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(Menu.NONE, REMOVE_JOKE_MENUITEM, Menu.NONE, getResources().getString(R.string.remove_menuitem));
		menu.add(Menu.NONE, UPLOAD_JOKE_MENUITEM, Menu.NONE, getResources().getString(R.string.upload_menuitem));
		menu.getItem(0).setOnMenuItemClickListener(this);
		menu.getItem(1).setOnMenuItemClickListener(this);

	}
	
	/**
	 * Method used to retrieve Jokes from online server. The getJoke script
	 * takes a single optional parameter, which should be encode in "UTF-8".
	 * This parameter allows tells script to only retrieve Jokes whose author
	 * name matches the value in the parameter.
	 * 
	 * param-1) "author": The author of the joke.
	 * 
	 * URL: http://simexusa.com/aac/getJokes.php?
	 * 
	 */
	protected void getJokesFromServer() {
		// TODO
		try {
		String JokeUrl="http://simexusa.com/aac/getAllJokes.php?";
		
		JokeUrl += "&author=";
		JokeUrl += java.net.URLEncoder.encode(getResources().getString(R.string.author_name));
		
		URL url = new java.net.URL(JokeUrl);
		Log.d("str", JokeUrl);
		java.util.Scanner Sca = new java.util.Scanner(new InputStreamReader(url.openStream()));
		Sca.useDelimiter("\n");
		Log.d("str", "2");
		String s="";
		Log.d("str", s);
		String st;
		Log.d("strB", st=""+Sca.hasNextLine());
		while (Sca.hasNextLine()) {
	        // str is one line of text; readLine() strips the newline character(s)
			s=Sca.nextLine();
			addJoke(new Joke(s,m_strAuthorName));
			Log.d("str", s);
	   }
		
	}
	catch (MalformedURLException e) {
		// TODO: handle exception
		e.printStackTrace();
	}
		catch (IOException e) { 
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * This method uploads a single Joke to the server. This method should test
	 * the response from the server and display success or failure to the user
	 * via a Toast Notification
	 * 
	 * The addJoke script on the server requires two parameters, both of which
	 * should be encode in "UTF-8":
	 * 
	 * param-1) "joke": The text of the joke.
	 * 
	 * param-2) "author": The author of the joke.
	 * 
	 * URL: http://simexusa.com/aac/addJoke.php?
	 * 
	 * @param joke
	 *            The Joke to be uploaded to the server.
	 * 
	 */
	protected void uploadJokeToServer(Joke joke) {
		// TODO
		String str = null;
	try {
			
		String JokeUrl="http://simexusa.com/aac/addOneJoke.php?joke=";
		JokeUrl += java.net.URLEncoder.encode(joke.getJoke());
		JokeUrl += "&author=";
		JokeUrl += java.net.URLEncoder.encode(joke.getAuthor());
		Log.d("df", JokeUrl);
			// Create a URL for the desired page
			URL url = new java.net.URL(JokeUrl);

			// Read all the text returned by the server
			java.util.Scanner Sca = new java.util.Scanner(new InputStreamReader(url.openStream()));
			
			str="";
			while (Sca.hasNextLine()) {
		        // str is one line of text; readLine() strips the newline character(s)
				str+= Sca.nextLine();
		   }
			
			
			

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	Log.d("Str", str);
	
		if (str.equals("1 record added")) {
			str = "Upload Succeeded!";
		} else {
			str = "Upload Failed!";
		}
		Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();

	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
        case REMOVE_JOKE_MENUITEM:
		m_arrJokeList.remove(m_jokeAdapter.getSelectedPosition());
		m_jokeAdapter.notifyDataSetChanged();
		return false;
        case UPLOAD_JOKE_MENUITEM:
    		uploadJokeToServer(m_arrJokeList.get(m_jokeAdapter.getSelectedPosition()));
    	return false;
		}
		return false;
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		 MenuInflater inflater = getMenuInflater();
		    inflater.inflate(R.menu.menu, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
         case R.id.like_menuitem:
        	 m_jokeAdapter.SetFilter(Joke.LIKE);
        	 m_jokeAdapter.notifyDataSetChanged();
            return true;
        case R.id.dislike_menuitem:
        	m_jokeAdapter.SetFilter(Joke.DISLIKE);
       	 m_jokeAdapter.notifyDataSetChanged();
            return true;
        case R.id.unrated_menuitem:
        	m_jokeAdapter.SetFilter(Joke.UNRATED);
       	 m_jokeAdapter.notifyDataSetChanged();
            return true;
        case R.id.show_all_menuitem:
        	m_jokeAdapter.SetFilter(0);
        	m_jokeAdapter.notifyDataSetChanged();
            return true;
        case R.id.download_menuitem:
        	getJokesFromServer();
            return true;   
        default:
            return super.onOptionsItemSelected(item);
    }
		
	}

}