package com.andrios.fleetknowledge.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class AndriosDbAdapter {
	//Database Fields
	public static final String KEY_ROWID = "_id";
	public static final String KEY_QUESTION = "question";
	public static final String KEY_ASKED = "isAsked";
	public static final String KEY_ANSWER = "answer";
	public static final String DATABASE_TABLE = "questions";
	public static final String DATABASE_CREEDS_TABLE = "creeds";

	public static final String KEY_TITLE = "title";
	public static final String KEY_BODY = "body";
	public static final String KEY_KNOWN = "isKnown";
	
	
	
	private Context context;
	private SQLiteDatabase database;
	private AndriosDatabaseHelper dbHelper;
	
	public AndriosDbAdapter(Context context){
		this.context = context;
	}
	
	public AndriosDbAdapter open() throws SQLException{
		dbHelper = new AndriosDatabaseHelper(context);
		database = dbHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		dbHelper.close();
	}
	
	/**
	 * Create a new crisis information. If the information is sucessfully created
	 * return the new rowID for that set, otherwise return a -1 to indicate failure.
	 */
	public long createQuestion(String question, int isAsked, String answer){
		ContentValues initialValues = createQuestionContentValues(question, isAsked, answer);
		return database.insert(DATABASE_TABLE, null, initialValues);
	}
	
	/**
	 * Update the information
	 */
	public boolean updateQuestion(long rowId, String question, int isAsked, String answer){
		ContentValues updateValues = createQuestionContentValues(question,isAsked, answer);
		return database.update(DATABASE_TABLE, updateValues, KEY_ROWID + "=" + rowId, null) >0;
		
	}
	
	/**
	 * Deletes information
	 */
	public boolean deleteQuestion(long rowId){
		return database.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null)>0;
	}
	
	/**
	 * Return a Cursor over the list of all Information Sets in the database
	 * @return Cursor over all Information Sets
	 */
	public Cursor fetchAllQuestions(){
		return database.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_QUESTION, KEY_ASKED, KEY_ANSWER}, null, null, null, null, null);
	}
	
	/**
	 * Return a Cursor positioned at the defined information set
	 */
	public Cursor fetchQuestion(long rowId) throws SQLException{
		Cursor mCursor = database.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_QUESTION, KEY_ASKED, KEY_ANSWER}, KEY_ROWID + "=" + rowId, null, null, null, null, null);
		if(mCursor != null){
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	private ContentValues createQuestionContentValues(String question, int isAsked, String answer){
		ContentValues values = new ContentValues();
		values.put(KEY_QUESTION, question);
		values.put(KEY_ASKED, isAsked);
		values.put(KEY_ANSWER, answer);
		return values;
	}
	
	
	
	
	
	
	
	
	
	
	// CREEDS
	
	
	/**
	 * Create a new crisis information. If the information is sucessfully created
	 * return the new rowID for that set, otherwise return a -1 to indicate failure.
	 */
	public long createCreed(String title, String body, int isKnown){
		ContentValues initialValues = createCreedContentValues(title, body, isKnown);
		return database.insert(DATABASE_CREEDS_TABLE, null, initialValues);
	}
	
	/**
	 * Update the information
	 */
	public boolean updateCreed(long rowId, String title, String body, int isKnown){
		ContentValues updateValues = createCreedContentValues(title, body, isKnown);
		return database.update(DATABASE_CREEDS_TABLE, updateValues, KEY_ROWID + "=" + rowId, null) >0;
		
	}
	
	/**
	 * Deletes information
	 */
	public boolean deleteCreed(long rowId){
		return database.delete(DATABASE_CREEDS_TABLE, KEY_ROWID + "=" + rowId, null)>0;
	}
	
	/**
	 * Return a Cursor over the list of all Information Sets in the database
	 * @return Cursor over all Information Sets
	 */
	public Cursor fetchAllCreeds(){
		return database.query(DATABASE_CREEDS_TABLE, new String[] {KEY_ROWID, KEY_TITLE, KEY_BODY, KEY_KNOWN}, null, null, null, null, null);
	}
	
	/**
	 * Return a Cursor positioned at the defined information set
	 */
	public Cursor fetchCreed(long rowId) throws SQLException{
		Cursor mCursor = database.query(DATABASE_CREEDS_TABLE, new String[] {KEY_ROWID, KEY_TITLE, KEY_BODY, KEY_KNOWN}, KEY_ROWID + "=" + rowId, null, null, null, null, null);
		if(mCursor != null){
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	private ContentValues createCreedContentValues(String title, String body, int isKnown){
		ContentValues values = new ContentValues();
		values.put(KEY_TITLE, title);
		values.put(KEY_BODY, body);
		values.put(KEY_KNOWN, isKnown);
		return values;
	}
	
	
	
	// SHIPS
	// SHIPS STATIC VARIABLES
		public static final String DATABASE_SHIPS_TABLE = "ships";

	public static final String KEY_SHIP_TYPE = "type";
	public static final String KEY_SHIP_CLASS = "ship_class";
	public static final String KEY_SHIP_DIMENSIONS = "dimensions";
	public static final String KEY_SHIP_CREW = "crew";
	public static final String KEY_SHIP_WEAPONS = "weapons";
	public static final String KEY_SHIP_PERFORMANCE = "performance";
	public static final String KEY_SHIP_PROPULSION = "propulsion";
	public static final String KEY_SHIP_AIRCRAFT = "aircraft";
	public static final String KEY_SHIP_EW = "ew";
	public static final String KEY_SHIP_SENSORS = "sensors";
	public static final String KEY_SHIP_BOATS = "boats";
	public static final String KEY_SHIP_ABOUT = "about";
	public static final String KEY_SHIP_IMAGE = "image";
	public static final String KEY_SHIP_LINK = "link";
		/**
		 * Create a new crisis information. If the information is sucessfully created
		 * return the new rowID for that set, otherwise return a -1 to indicate failure.
		 */
		public long createShip(String type, String ship_class, String dimension, String crew, String weapons, String performance, String propulsion, String aircraft, String ew, String sensors, String boats, String about, String image, String link){
			ContentValues initialValues = createShipContentValues(type, ship_class, dimension, crew, weapons, performance, propulsion, aircraft, ew, sensors, boats, about, image, link);
			return database.insert(DATABASE_SHIPS_TABLE, null, initialValues);
		}
		
		/**
		 * Update the information
		 */
		public boolean updateShip(long rowId, String type, String ship_class, String dimension, String crew, String weapons, String performance, String propulsion, String aircraft, String ew, String sensors, String boats, String about, String image, String link){
			ContentValues updateValues = createShipContentValues(type, ship_class, dimension, crew, weapons, performance, propulsion, aircraft, ew, sensors, boats, about, image, link);
			return database.update(DATABASE_SHIPS_TABLE, updateValues, KEY_ROWID + "=" + rowId, null) >0;
			
		}
		
		/**
		 * Deletes information
		 */
		public boolean deleteShip(long rowId){
			return database.delete(DATABASE_SHIPS_TABLE, KEY_ROWID + "=" + rowId, null)>0;
		}
		
		/**
		 * Return a Cursor over the list of all Information Sets in the database
		 * @return Cursor over all Information Sets
		 */
		public Cursor fetchAllShips(){
			return database.query(DATABASE_SHIPS_TABLE, new String[] {KEY_ROWID, KEY_SHIP_TYPE, KEY_SHIP_CLASS, KEY_SHIP_DIMENSIONS, KEY_SHIP_CREW, KEY_SHIP_WEAPONS, KEY_SHIP_PERFORMANCE, KEY_SHIP_PROPULSION, KEY_SHIP_AIRCRAFT, KEY_SHIP_EW, KEY_SHIP_SENSORS, KEY_SHIP_BOATS, KEY_SHIP_ABOUT, KEY_SHIP_IMAGE, KEY_SHIP_LINK}, null, null, null, null, null);
		}
		
		/**
		 * Return a Cursor positioned at the defined information set
		 */
		public Cursor fetchShip(long rowId) throws SQLException{
			Cursor mCursor = database.query(DATABASE_SHIPS_TABLE, new String[] {KEY_ROWID, KEY_SHIP_TYPE, KEY_SHIP_CLASS, KEY_SHIP_DIMENSIONS, KEY_SHIP_CREW, KEY_SHIP_WEAPONS, KEY_SHIP_PERFORMANCE, KEY_SHIP_PROPULSION, KEY_SHIP_AIRCRAFT, KEY_SHIP_EW, KEY_SHIP_SENSORS, KEY_SHIP_BOATS, KEY_SHIP_ABOUT, KEY_SHIP_IMAGE, KEY_SHIP_LINK}, KEY_ROWID + "=" + rowId, null, null, null, null, null);
			if(mCursor != null){
				mCursor.moveToFirst();
			}
			return mCursor;
		}
		
		private ContentValues createShipContentValues(String type, String ship_class, String dimension, String crew, String weapons, String performance, String propulsion, String aircraft, String ew, String sensors, String boats, String about, String image, String link){
			ContentValues values = new ContentValues();
			values.put(KEY_SHIP_TYPE, type);
			values.put(KEY_SHIP_CLASS, ship_class );
			values.put(KEY_SHIP_DIMENSIONS, dimension);
			values.put(KEY_SHIP_CREW, crew );
			values.put(KEY_SHIP_WEAPONS, weapons );
			values.put(KEY_SHIP_PERFORMANCE, performance );
			values.put(KEY_SHIP_PROPULSION, propulsion);
			values.put(KEY_SHIP_AIRCRAFT, aircraft);
			values.put(KEY_SHIP_EW, ew);
			values.put(KEY_SHIP_SENSORS, sensors);
			values.put(KEY_SHIP_BOATS, boats);
			values.put(KEY_SHIP_ABOUT, about);
			values.put(KEY_SHIP_IMAGE, image);
			values.put(KEY_SHIP_LINK, link);
			
			return values;
		}
		
		// SUBS
		// SUBS STATIC VARIABLES
			public static final String DATABASE_SUBS_TABLE = "subs";

		public static final String KEY_SUB_TYPE = "type";
		public static final String KEY_SUB_CLASS = "sub_class";
		public static final String KEY_SUB_DIMENSIONS = "dimensions";
		public static final String KEY_SUB_CREW = "crew";
		public static final String KEY_SUB_WEAPONS = "weapons";
		public static final String KEY_SUB_PERFORMANCE = "performance";
		public static final String KEY_SUB_PROPULSION = "propulsion";
		public static final String KEY_SUB_AIRCRAFT = "aircraft";
		public static final String KEY_SUB_EW = "ew";
		public static final String KEY_SUB_SENSORS = "sensors";
		public static final String KEY_SUB_BOATS = "boats";
		public static final String KEY_SUB_ABOUT = "about";
		public static final String KEY_SUB_IMAGE = "image";
		public static final String KEY_SUB_LINK = "link";
			/**
			 * Create a new crisis information. If the information is sucessfully created
			 * return the new rowID for that set, otherwise return a -1 to indicate failure.
			 */
			public long createSub(String type, String sub_class, String dimension, String crew, String weapons, String performance, String propulsion, String aircraft, String ew, String sensors, String boats, String about, String image, String link){
				ContentValues initialValues = createSubContentValues(type, sub_class, dimension, crew, weapons, performance, propulsion, aircraft, ew, sensors, boats, about, image, link);
				return database.insert(DATABASE_SUBS_TABLE, null, initialValues);
			}
			
			/**
			 * Update the information
			 */
			public boolean updateSub(long rowId, String type, String sub_class, String dimension, String crew, String weapons, String performance, String propulsion, String aircraft, String ew, String sensors, String boats, String about, String image, String link){
				ContentValues updateValues = createSubContentValues(type, sub_class, dimension, crew, weapons, performance, propulsion, aircraft, ew, sensors, boats, about, image, link);
				return database.update(DATABASE_SUBS_TABLE, updateValues, KEY_ROWID + "=" + rowId, null) >0;
				
			}
			
			/**
			 * Deletes information
			 */
			public boolean deleteSub(long rowId){
				return database.delete(DATABASE_SUBS_TABLE, KEY_ROWID + "=" + rowId, null)>0;
			}
			
			/**
			 * Return a Cursor over the list of all Information Sets in the database
			 * @return Cursor over all Information Sets
			 */
			public Cursor fetchAllSubs(){
				return database.query(DATABASE_SUBS_TABLE, new String[] {KEY_ROWID, KEY_SUB_TYPE, KEY_SUB_CLASS, KEY_SUB_DIMENSIONS, KEY_SUB_CREW, KEY_SUB_WEAPONS, KEY_SUB_PERFORMANCE, KEY_SUB_PROPULSION, KEY_SUB_AIRCRAFT, KEY_SUB_EW, KEY_SUB_SENSORS, KEY_SUB_BOATS, KEY_SUB_ABOUT, KEY_SUB_IMAGE, KEY_SUB_LINK}, null, null, null, null, null);
			}
			
			/**
			 * Return a Cursor positioned at the defined information set
			 */
			public Cursor fetchSub(long rowId) throws SQLException{
				Cursor mCursor = database.query(DATABASE_SUBS_TABLE, new String[] {KEY_ROWID, KEY_SUB_TYPE, KEY_SUB_CLASS, KEY_SUB_DIMENSIONS, KEY_SUB_CREW, KEY_SUB_WEAPONS, KEY_SUB_PERFORMANCE, KEY_SUB_PROPULSION, KEY_SUB_AIRCRAFT, KEY_SUB_EW, KEY_SUB_SENSORS, KEY_SUB_BOATS, KEY_SUB_ABOUT, KEY_SUB_IMAGE, KEY_SUB_LINK}, KEY_ROWID + "=" + rowId, null, null, null, null, null);
				if(mCursor != null){
					mCursor.moveToFirst();
				}
				return mCursor;
			}
			
			private ContentValues createSubContentValues(String type, String sub_class, String dimension, String crew, String weapons, String performance, String propulsion, String aircraft, String ew, String sensors, String boats, String about, String image, String link){
				ContentValues values = new ContentValues();
				values.put(KEY_SUB_TYPE, type);
				values.put(KEY_SUB_CLASS, sub_class );
				values.put(KEY_SUB_DIMENSIONS, dimension);
				values.put(KEY_SUB_CREW, crew );
				values.put(KEY_SUB_WEAPONS, weapons );
				values.put(KEY_SUB_PERFORMANCE, performance );
				values.put(KEY_SUB_PROPULSION, propulsion);
				values.put(KEY_SUB_AIRCRAFT, aircraft);
				values.put(KEY_SUB_EW, ew);
				values.put(KEY_SUB_SENSORS, sensors);
				values.put(KEY_SUB_BOATS, boats);
				values.put(KEY_SUB_ABOUT, about);
				values.put(KEY_SUB_IMAGE, image);
				values.put(KEY_SUB_LINK, link);
				
				return values;
			}
	

			// AC
			// AC STATIC VARIABLES
				public static final String DATABASE_AC_TABLE = "aircraft";

			public static final String KEY_AC_FUNCTION = "function";
			public static final String KEY_AC_TYPE = "ac_type";
			public static final String KEY_AC_PROPULSION = "propulsion";
			public static final String KEY_AC_PERFORMANCE = "performance";
			public static final String KEY_AC_SIZE = "size";
			public static final String KEY_AC_CREW = "crew";
			public static final String KEY_AC_SENSORS = "sensors";
			public static final String KEY_AC_ARMAMENT = "armament";
			public static final String KEY_AC_CURRENT = "current";
			public static final String KEY_AC_MISSION = "mission";
			public static final String KEY_AC_IMAGE = "image";
			public static final String KEY_AC_LINK = "link";
				/**
				 * Create a new crisis information. If the information is sucessfully created
				 * return the new rowID for that set, otherwise return a -1 to indicate failure.
				 */
				public long createAircraft(String function, String ac_type, String propulsion, String performance, String size, String crew, String sensors, String armament, String current, String mission, String image, String link){
					ContentValues initialValues = createAircraftContentValues(function, ac_type, propulsion, performance, size, crew, sensors, armament, current, mission,  image, link);
					return database.insert(DATABASE_AC_TABLE, null, initialValues);
				}
				
				/**
				 * Update the information
				 */
				public boolean updateAircraft(long rowId, String function, String ac_type, String propulsion, String performance, String size, String crew, String sensors, String armament, String current, String mission, String image, String link){
					ContentValues updateValues = createAircraftContentValues(function, ac_type, propulsion, performance, size, crew, sensors, armament, current, mission,  image, link);
					return database.update(DATABASE_AC_TABLE, updateValues, KEY_ROWID + "=" + rowId, null) >0;
					
				}
				
				/**
				 * Deletes information
				 */
				public boolean deleteAircraft(long rowId){
					return database.delete(DATABASE_AC_TABLE, KEY_ROWID + "=" + rowId, null)>0;
				}
				
				/**
				 * Return a Cursor over the list of all Information Sets in the database
				 * @return Cursor over all Information Sets
				 */
				public Cursor fetchAllAircrafts(){
					return database.query(DATABASE_AC_TABLE, new String[] {KEY_ROWID, KEY_AC_FUNCTION, KEY_AC_TYPE, KEY_AC_PROPULSION, KEY_AC_PERFORMANCE, KEY_AC_SIZE, KEY_AC_CREW, KEY_AC_SENSORS, KEY_AC_ARMAMENT, KEY_AC_CURRENT, KEY_AC_MISSION,  KEY_AC_IMAGE,  KEY_AC_LINK}, null, null, null, null, null);
				}
				
				/**
				 * Return a Cursor positioned at the defined information set
				 */
				public Cursor fetchAircraft(long rowId) throws SQLException{
					Cursor mCursor = database.query(DATABASE_AC_TABLE, new String[] {KEY_ROWID, KEY_AC_FUNCTION, KEY_AC_TYPE, KEY_AC_PROPULSION, KEY_AC_PERFORMANCE, KEY_AC_SIZE, KEY_AC_CREW, KEY_AC_SENSORS, KEY_AC_ARMAMENT, KEY_AC_CURRENT, KEY_AC_MISSION,  KEY_AC_IMAGE,  KEY_AC_LINK}, KEY_ROWID + "=" + rowId, null, null, null, null, null);
					if(mCursor != null){
						mCursor.moveToFirst();
					}
					return mCursor;
				}
				
				private ContentValues createAircraftContentValues(String function, String ac_type, String propulsion, String performance, String size, String crew, String sensors, String armament, String current, String mission, String image, String link){
					ContentValues values = new ContentValues();
					values.put(KEY_AC_FUNCTION, function);
					values.put(KEY_AC_TYPE, ac_type );
					values.put(KEY_AC_PROPULSION, propulsion);
					values.put(KEY_AC_PERFORMANCE, performance );
					values.put(KEY_AC_SIZE, size );
					values.put(KEY_AC_CREW, crew );
					values.put(KEY_AC_SENSORS, sensors);
					values.put(KEY_AC_ARMAMENT, armament);
					values.put(KEY_AC_CURRENT, current);
					values.put(KEY_AC_MISSION, mission);
					values.put(KEY_AC_IMAGE, image);
					values.put(KEY_AC_LINK, link);
					
					return values;
				}
			
			
				

				// MISSILE
				// MISSILE STATIC VARIABLES
					public static final String DATABASE_MISSILE_TABLE = "missiles";

				public static final String KEY_MISSILE_LINK = "link";
				public static final String KEY_MISSILE_IMAGE = "image";
				public static final String KEY_MISSILE_PRICE = "price";
				public static final String KEY_MISSILE_FEATURES = "features";
				public static final String KEY_MISSILE_BACKGROUND = "background";
				public static final String KEY_MISSILE_DESCRIPTION = "description";
				public static final String KEY_MISSILE_NAME = "name";
				public static final String KEY_MISSILE_FUNCTION = "function";
				public static final String KEY_MISSILE_DEPLOY_DATE = "deploy_date";
				public static final String KEY_MISSILE_PROPULSION = "propulsion";
				public static final String KEY_MISSILE_DIMENSIONS = "dimensions";
				public static final String KEY_MISSILE_PERFORMANCE = "performance";

				public static final String KEY_MISSILE_WARHEAD = "warhead";
				public static final String KEY_MISSILE_PLATFORM = "platforms";
					/**
					 * Create a new crisis information. If the information is sucessfully created
					 * return the new rowID for that set, otherwise return a -1 to indicate failure.
					 */
					public long createMissile(String link, String image, String price, String features, String background, String description, String name, String function, String deploy_date, String propulsion, String dimensions, String performance, String warhead, String platforms){
						ContentValues initialValues = createMissileContentValues(link, image, price, features, background, description, name, function, deploy_date, propulsion, dimensions, performance, warhead, platforms);
						return database.insert(DATABASE_MISSILE_TABLE, null, initialValues);
					}
					
					/**
					 * Update the information
					 */
					public boolean updateMissile(long rowId, String link, String image, String price, String features, String background, String description, String name, String function, String deploy_date, String propulsion, String dimensions, String performance, String warhead, String platforms){
						ContentValues updateValues = createMissileContentValues(link, image, price, features, background, description, name, function, deploy_date, propulsion, dimensions, performance, warhead, platforms);
						return database.update(DATABASE_MISSILE_TABLE, updateValues, KEY_ROWID + "=" + rowId, null) >0;
						
					}
					
					/**
					 * Deletes information
					 */
					public boolean deleteMissile(long rowId){
						return database.delete(DATABASE_MISSILE_TABLE, KEY_ROWID + "=" + rowId, null)>0;
					}
					
					/**
					 * Return a Cursor over the list of all Information Sets in the database
					 * @return Cursor over all Information Sets
					 */
					public Cursor fetchAllMissiles(){
						return database.query(DATABASE_MISSILE_TABLE, new String[] {KEY_ROWID, KEY_MISSILE_LINK, KEY_MISSILE_IMAGE, KEY_MISSILE_PRICE, KEY_MISSILE_FEATURES, KEY_MISSILE_BACKGROUND, KEY_MISSILE_DESCRIPTION, KEY_MISSILE_NAME, KEY_MISSILE_FUNCTION, KEY_MISSILE_DEPLOY_DATE, KEY_MISSILE_PROPULSION,  KEY_MISSILE_DIMENSIONS,  KEY_MISSILE_PERFORMANCE,  KEY_MISSILE_WARHEAD,  KEY_MISSILE_PLATFORM}, null, null, null, null, null);
					}
					
					/**
					 * Return a Cursor positioned at the defined information set
					 */
					public Cursor fetchMissile(long rowId) throws SQLException{
						Cursor mCursor = database.query(DATABASE_MISSILE_TABLE, new String[] {KEY_ROWID, KEY_MISSILE_LINK, KEY_MISSILE_IMAGE, KEY_MISSILE_PRICE, KEY_MISSILE_FEATURES, KEY_MISSILE_BACKGROUND, KEY_MISSILE_DESCRIPTION, KEY_MISSILE_NAME, KEY_MISSILE_FUNCTION, KEY_MISSILE_DEPLOY_DATE, KEY_MISSILE_PROPULSION,  KEY_MISSILE_DIMENSIONS,  KEY_MISSILE_PERFORMANCE,  KEY_MISSILE_WARHEAD,  KEY_MISSILE_PLATFORM}, KEY_ROWID + "=" + rowId, null, null, null, null, null);
						if(mCursor != null){
							mCursor.moveToFirst();
						}
						return mCursor;
					}
					
					private ContentValues createMissileContentValues(String link, String image, String price, String features, String background, String description, String name, String function, String deploy_date, String propulsion, String dimensions, String performance, String warhead, String platforms){
						ContentValues values = new ContentValues();
						values.put(KEY_MISSILE_LINK, link);
						values.put(KEY_MISSILE_IMAGE, image);
						values.put(KEY_MISSILE_PRICE, price);
						values.put(KEY_MISSILE_FEATURES, features);
						values.put(KEY_MISSILE_BACKGROUND, background);
						values.put(KEY_MISSILE_DESCRIPTION, description);
						values.put(KEY_MISSILE_NAME, name);
						values.put(KEY_MISSILE_FUNCTION, function);
						values.put(KEY_MISSILE_DEPLOY_DATE, deploy_date);
						values.put(KEY_MISSILE_PROPULSION, propulsion);
						values.put(KEY_MISSILE_DIMENSIONS, dimensions);
						values.put(KEY_MISSILE_PERFORMANCE, performance);
						values.put(KEY_MISSILE_WARHEAD, warhead);
						values.put(KEY_MISSILE_PLATFORM, platforms);
						
						return values;
					}
				
				
}
