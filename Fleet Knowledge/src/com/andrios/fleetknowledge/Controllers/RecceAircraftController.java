package com.andrios.fleetknowledge.Controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import com.andrios.fleetknowledge.R;
import com.andrios.fleetknowledge.Adapters.ExpandableAircraftAdapter;
import com.andrios.fleetknowledge.Database.AndriosDbAdapter;
import com.andrios.fleetknowledge.Models.Aircraft;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.ExpandableListView.OnChildClickListener;

public class RecceAircraftController extends Activity {
	private static final int ACTIVITY_CREATE = 0;
	private static final int ACTIVITY_EDIT = 1;
	
	AndriosDbAdapter mDbAdapter;
	ExpandableListView listView;
	ExpandableAircraftAdapter listAdapter;
	ArrayList<Aircraft> shipList;
	Cursor cursor;
	Button newBTN;
	
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.recceshipsview);
        mDbAdapter = new AndriosDbAdapter(this);
		mDbAdapter.open();
		

		setConnections();
		setOnClickListeners();
		

    }

	

	private void setConnections() {
		TextView titleLBL = (TextView) findViewById(R.id.recceShipsViewTitleLBL);
		titleLBL.setText("US Navy Aircraft");
		newBTN = (Button) findViewById(R.id.creedsNewBTN);
		listView = (ExpandableListView) findViewById(R.id.recceShipViewExpandableListView);
		fillData();
		listAdapter = new ExpandableAircraftAdapter(this, new ArrayList<String>(), new ArrayList<ArrayList<Aircraft>>());
		listView.setAdapter(listAdapter);
		addAircraftsToAdapter();
	}
	
	private void addAircraftsToAdapter(){
		
		for(int i = 0; i < shipList.size(); i++){
			listAdapter.addItem(shipList.remove(0));
			i--;
		}
	}

	private void setOnClickListeners() {
		listView.setOnChildClickListener(new OnChildClickListener() {

			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Intent intent = new Intent(v.getContext(), RecceAircraftDetailsController.class);
				intent.putExtra("aircraft", (Aircraft) listAdapter.getChild(groupPosition, childPosition));
				startActivity(intent);
				
				return false;
			}
			
		});
		
	}
	
	 private void fillData(){
	    	cursor = mDbAdapter.fetchAllAircrafts();
	    	startManagingCursor(cursor);
	    	
	    	
	    	
		    	cursor.moveToFirst();
		    	shipList = new ArrayList<Aircraft>();
		        while (cursor.isAfterLast() == false) {
		            shipList.add(new Aircraft(cursor, RecceAircraftController.this));
		       	    cursor.moveToNext();
		        }
		        cursor.close();
	    	
	    	
	    }
	 
	    /**
	     * Called with the result of the other activity requestCode was the origin
	     * request code sent to the activity resultCode is the return code, 0
	     * if everything is ok intent can be used to get some data from the caller
	     */
	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
	    	super.onActivityResult(requestCode, resultCode, intent);
	    	fillData();
	    }
	 
	 
	 
	 
	 
	 @Override 
	    protected void onDestroy(){
	    	super.onDestroy();
	    	if(mDbAdapter != null){
	    		mDbAdapter.close();
	    	}
	    }
}