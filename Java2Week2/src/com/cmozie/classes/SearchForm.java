/*
 * project 			Java2Week2
 * 
 * package			com.cmozie.classes
 * 
 * name				cameronmozie
 * 
 * date				Oct 10, 2013
 */

package com.cmozie.classes;


import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;



// TODO: Auto-generated Javadoc
/**
 * The Class SearchForm.
 */
@SuppressLint("ViewConstructor")
public class SearchForm extends LinearLayout {
	
	EditText _searchField;
	Button _searchButton;
	
	
	/**
	 * Instantiates a new search form.
	 *
	 * @param context the context
	 * @param hint the hint
	 * @param buttonText the button text
	 */
	public SearchForm(Context context,String hint, String buttonText) {
		
		//allows to import the value
		super(context);
		
		_searchField = new EditText(context);
		
		LayoutParams lp;
		
		lp = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f);
		
		_searchField.setLayoutParams(lp);
		_searchField.setHint(hint);
		
		_searchButton = new Button(context);
		_searchButton.setText(buttonText);
		
		this.addView(_searchField);
		this.addView(_searchButton);
		
		lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		this.setLayoutParams(lp);
		
	}
	
	/**
	 * Gets the field.
	 *
	 * @return the field
	 */
	public EditText getField(){
		
		return _searchField;
	}

	/**
	 * Gets the button.
	 *
	 * @return the button
	 */
	public Button getButton(){
		
		return _searchButton;
	}
}
