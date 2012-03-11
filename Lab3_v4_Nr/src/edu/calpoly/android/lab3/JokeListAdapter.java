package edu.calpoly.android.lab3;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;

public class JokeListAdapter extends BaseAdapter implements OnItemLongClickListener {
	
	private int m_filter;

	/**
	 * The application Context in which this JokeListAdapter is being used.
	 */
	private Context m_context;

	/**
	 * The dataset to which this JokeListAdapter is bound.
	 */
	private List<Joke> m_jokeList;

	/**
	 * The position in the dataset of the currently selected Joke.
	 */
	private int m_nSelectedPosition;

	/**
	 * Parameterized constructor that takes in the application Context in which
	 * it is being used and the Collection of Joke objects to which it is bound.
	 * m_nSelectedPosition will be initialized to Adapter.NO_SELECTION.
	 * 
	 * @param context
	 *            The application Context in which this JokeListAdapter is being
	 *            used.
	 * 
	 * @param jokeList
	 *            The Collection of Joke objects to which this JokeListAdapter
	 *            is bound.
	 */
	public JokeListAdapter(Context context, List<Joke> jokeList) {
		//TODO
		m_context=context;
		m_jokeList=jokeList;
		m_nSelectedPosition=Adapter.NO_SELECTION;
		Activity ac= new Activity();
		m_filter= 0;

	}

	/**
	 * Accessor method for retrieving the position in the dataset of the
	 * currently selected Joke.
	 * 
	 * @return an integer representing the position in the dataset of the
	 *         currently selected Joke.
	 */
	public int getSelectedPosition() {
		//TODO
		
		return m_nSelectedPosition;
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return m_jokeList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return m_jokeList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		if (convertView==null)
		{
			JokeView jokeV=new JokeView(m_context, (Joke) getItem(position));
			filter(jokeV, (Joke) getItem(position));
			return jokeV;
		}
		else
		{
			JokeView jokeV= (JokeView) convertView;
			jokeV.setJoke((Joke) getItem(position));
			filter(jokeV, (Joke) getItem(position));
			return convertView;
		}
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position,
			long id) {
		// TODO Auto-generated method stub
		m_nSelectedPosition=position;
		return false;
		
	}

	public void SetFilter(int f)
	{
		m_filter=f;
	}
	public void filter(JokeView jokeV, Joke joke) 
	{
		switch (m_filter)
		{
		
		case 0 : jokeV.setVisibility(jokeV.VISIBLE); break;
		case Joke.UNRATED : if (m_filter==joke.getRating())  {jokeV.setVisibility(jokeV.VISIBLE);} else {jokeV.setVisibility(jokeV.GONE);} break;
		case Joke.LIKE : if    (m_filter==joke.getRating())  {jokeV.setVisibility(jokeV.VISIBLE);} else {jokeV.setVisibility(jokeV.GONE);} break;
		case Joke.DISLIKE: if  (m_filter==joke.getRating())  {jokeV.setVisibility(jokeV.VISIBLE);} else {jokeV.setVisibility(jokeV.GONE);} break;	
		}
	}
}
