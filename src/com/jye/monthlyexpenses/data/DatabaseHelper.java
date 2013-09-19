package com.jye.monthlyexpenses.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DatabaseHelper {

	private static SQLiteDatabase sqliteDataBase;
	private static String DB_NAME = "EXPENSES.db";
	private static String EXPENSES_TABLE = "MONTLY_EXPENSES";


	// <span id="IL_AD5" class="IL_AD">CREATE EXPENSES_TABLE</span> IF NOT EXISTS 
	public static void createTable(Context context){
		try{
			sqliteDataBase = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
			sqliteDataBase.execSQL("CREATE TABLE IF  NOT EXISTS "+ EXPENSES_TABLE +" (ID INTEGER PRIMARY KEY, NAME TEXT, PRICE REAL);");
			sqliteDataBase.close();
		}catch(Exception e){
			Toast.makeText(context, "Error in creating table", Toast.LENGTH_LONG);
		}
	}
	// THIS FUNCTION INSERTS DATA TO THE DATABASE
	public static void insertIntoTable(Context context){
		try{
			sqliteDataBase = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE,null);
			sqliteDataBase.execSQL("INSERT INTO " + EXPENSES_TABLE + "(NAME, PRICE) VALUES('Internet', 200)");
			sqliteDataBase.execSQL("INSERT INTO " + EXPENSES_TABLE + "(NAME, PRICE) VALUES('Luz', 40)");
			sqliteDataBase.execSQL("INSERT INTO " + EXPENSES_TABLE + "(NAME, PRICE) VALUES('Telefono', 200)");
			sqliteDataBase.execSQL("INSERT INTO " + EXPENSES_TABLE + "(NAME, PRICE) VALUES('Gas', 20)");
			sqliteDataBase.close();
		}catch(Exception e){
			Toast.makeText(context, "Error in inserting into table", Toast.LENGTH_LONG);
		}
	}
	//		// THIS FUNCTION SHOWS DATA FROM THE DATABASE 
	//		public void showTableValues(Context context){
	//			try{
	//				sqliteDataBase = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE,null);
	//				Cursor allrows  = sqliteDataBase.rawQuery("SELECT * FROM "+  EXPENSES_TABLE, null);
	//				System.out.println("COUNT : " + allrows.getCount());
	//				Integer cindex = allrows.getColumnIndex("NAME");
	//				Integer cindex1 = allrows.getColumnIndex("PLACE");
	//
	//				TextView t = new TextView(this);
	//				t.setText("========================================");
	//				//Linear.removeAllViews();
	//				Linear.addView(t);
	//
	//				if(allrows.moveToFirst()){
	//					do{
	//						LinearLayout id_row   = new LinearLayout(this);
	//						LinearLayout name_row = new LinearLayout(this);
	//						LinearLayout place_row= new LinearLayout(this);
	//
	//						final TextView id_  = new TextView(this);
	//						final TextView name_ = new TextView(this);
	//						final TextView place_ = new TextView(this);
	//						final TextView   sep  = new TextView(this);
	//
	//						String ID = allrows.getString(0);
	//						String NAME= allrows.getString(1);
	//						String PLACE= allrows.getString(2);
	//
	//						id_.setTextColor(Color.RED);
	//						id_.setPadding(20, 5, 0, 5);
	//						name_.setTextColor(Color.RED);
	//						name_.setPadding(20, 5, 0, 5);
	//						place_.setTextColor(Color.RED);
	//						place_.setPadding(20, 5, 0, 5);
	//
	//						System.out.println("NAME " + allrows.getString(cindex) + " PLACE : "+ allrows.getString(cindex1));
	//						System.out.println("ID : "+ ID  + " || NAME " + NAME + "|| PLACE : "+ PLACE);
	//
	//						id_.setText("ID : " + ID);
	//						id_row.addView(id_);
	//						Linear.addView(id_row);
	//						name_.setText("NAME : "+NAME);
	//						name_row.addView(name_);
	//						Linear.addView(name_row);
	//						place_.setText("PLACE : " + PLACE);
	//						place_row.addView(place_);
	//						Linear.addView(place_row);
	//						sep.setText("---------------------------------------------------------------");
	//						Linear.addView(sep);
	//					}
	//					while(allrows.moveToNext());
	//				}
	//				sqliteDataBase.close();
	//			}catch(Exception e){
	//				Toast.makeText(context, "Error encountered.", Toast.LENGTH_LONG);
	//			}
	//		}
	
//	// THIS FUNCTION UPDATES THE DATABASE ACCORDING TO THE CONDITION 
//	public static void updateTable(Context context){
//		try{
//			sqliteDataBase = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE,null);
//			sqliteDataBase.execSQL("UPDATE " + EXPENSES_TABLE + " SET NAME = 'MAX' WHERE PLACE = 'USA'");
//			sqliteDataBase.close();
//		}catch(Exception e){
//			Toast.makeText(context, "Error encountered", Toast.LENGTH_LONG);
//		}
//	}
//	
//	// THIS FUNCTION DELETES VALUES FROM THE DATABASE ACCORDING TO THE CONDITION
//	public static void deleteValues(Context context){
//		try{
//			sqliteDataBase = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE,null);
//			sqliteDataBase.execSQL("DELETE FROM " + EXPENSES_TABLE + " WHERE PLACE = 'USA'");
//			sqliteDataBase.close();
//		}catch(Exception e){
//			Toast.makeText(context, "Error encountered while deleting.", Toast.LENGTH_LONG);
//		}
//	}
	
	// THIS FUNTION DROPS A EXPENSES_TABLE 
	public static void dropTable(Context context){
		try{
			sqliteDataBase = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
			sqliteDataBase.execSQL("DROP TABLE " + EXPENSES_TABLE);
			sqliteDataBase.close();
		}catch(Exception e){
			Toast.makeText(context, "Error encountered while dropping.", Toast.LENGTH_LONG);
		}
	}
}
