package com.andrios.fleetknowledge.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AndriosDatabaseHelper extends SQLiteOpenHelper{
	private static final String DATABASE_NAME = "fleetknowledgeapplicationdata";
	private static final int DATABASE_VERSION = 1;
	
	//Database Creation SQL statement
	private static final String QUESTIONS_CREATE = "create table questions (_id integer primary key autoincrement, question text not null, isAsked int not null, answer text not null);";
	private static final String SHIPS_CREATE = "create table ships (_id integer primary key autoincrement, type text not null, ship_class text not null, dimensions text not null, crew text not null, weapons text not null, performance text not null, propulsion text not null, aircraft text not null, ew text not null, sensors text not null, boats text not null, about text not null, image text not null, link text not null);";
	private static final String SUBS_CREATE = "create table subs (_id integer primary key autoincrement, type text not null, sub_class text not null, dimensions text not null, crew text not null, weapons text not null, performance text not null, propulsion text not null, aircraft text not null, ew text not null, sensors text not null, boats text not null, about text not null, image text not null, link text not null);";
	private static final String AIRCRAFT_CREATE = "create table aircraft (_id integer primary key autoincrement, function text not null, ac_type text not null, propulsion text not null, performance text not null, size text not null, crew text not null, sensors text not null, armament text not null, current text not null, mission text not null, link text not null, image text not null);";
	
	private static final String CREEDS_CREATE = "create table creeds (_id integer primary key autoincrement, title text not null, body text not null, isKnown int not null);";

	public AndriosDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	//Method is called during the creation of the database
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(QUESTIONS_CREATE);

		db.execSQL(CREEDS_CREATE);
		db.execSQL(SHIPS_CREATE);
		db.execSQL(SUBS_CREATE);
		db.execSQL(AIRCRAFT_CREATE);
	}

	//Method is called during an upgrade of the database, eg. if you increase the 
	//database version
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(AndriosDatabaseHelper.class.getName(), "Upgrading from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS");
		onCreate(db);
		
	}

}
