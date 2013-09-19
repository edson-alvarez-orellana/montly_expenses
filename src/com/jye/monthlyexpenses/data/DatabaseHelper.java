package com.jye.monthlyexpenses.data;

import java.util.List;

import com.jye.monthlyexpenses.model.Expense;

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

	public static void createTable(Context context){
		
		try{
			sqliteDataBase = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
			sqliteDataBase.execSQL("CREATE TABLE IF  NOT EXISTS "
					+ EXPENSES_TABLE
					+ " (ID INTEGER PRIMARY KEY, NAME TEXT, PRICE REAL);");
			sqliteDataBase.close();
			
			Toast.makeText(context, "Database has been created", Toast.LENGTH_LONG);
			
		}catch(Exception e){
			Toast.makeText(context, "Error in creating table", Toast.LENGTH_LONG);
		}
	}

	public static void fillDatabase(Context context){
		try{
			sqliteDataBase = context.openOrCreateDatabase(DB_NAME,Context.MODE_PRIVATE,null);
			sqliteDataBase.execSQL("INSERT INTO " + EXPENSES_TABLE + "(NAME, PRICE) VALUES('Luz',200)");
			sqliteDataBase.execSQL("INSERT INTO " + EXPENSES_TABLE + "(NAME, PRICE) VALUES('Internet',150.5)");
			sqliteDataBase.execSQL("INSERT INTO " + EXPENSES_TABLE + "(NAME, PRICE) VALUES('Gas',75)");
			sqliteDataBase.execSQL("INSERT INTO " + EXPENSES_TABLE + "(NAME, PRICE) VALUES('Agua',100)");

			sqliteDataBase.close();

		}catch(Exception e){
			Toast.makeText(context, "Error in inserting into table", Toast.LENGTH_LONG);
		}
	}

	public static void insertIntoTable(Context context, Expense expense){
		try{
			if(expense != null && expense.getName() != null) {
				
				String name = expense.getName();
				float price = expense.getPrice();
				
				sqliteDataBase = context.openOrCreateDatabase(
						DB_NAME, 
						Context.MODE_PRIVATE, 
						null);

				sqliteDataBase.execSQL("INSERT INTO " + EXPENSES_TABLE + "(NAME, PRICE) VALUES('" + name + "', "+ price+ ")");
				sqliteDataBase.close();
				
				Toast.makeText(context, "Expense: " + name + " inserted successfully!", Toast.LENGTH_LONG);
				
			} else {
				Toast.makeText(context, "Expense is null", Toast.LENGTH_LONG);	
			}

		}catch(Exception e){
			Toast.makeText(context, "Error in inserting into table", Toast.LENGTH_LONG);
		}
	}
	public static String showTableValues(Context context){
		String expensesSummary = "";
		float total = 0;		

		try{
			sqliteDataBase = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE,null);
			Cursor allrows  = sqliteDataBase.rawQuery("SELECT * FROM "+  EXPENSES_TABLE, null);
			System.out.println("COUNT : " + allrows.getCount());

			if(allrows.moveToFirst()){
				do{
					String id = allrows.getString(0);
					String name= allrows.getString(1);
					float price= allrows.getFloat(2);

					expensesSummary += name + " - " + price + "\n";
					total += price;
				}
				while(allrows.moveToNext());
			}

			expensesSummary += "-------------------------------\nTotal: " + total;
			sqliteDataBase.close();
		}catch(Exception e){
			Toast.makeText(context, "Error encountered.", Toast.LENGTH_LONG);
		}

		return expensesSummary;
	}

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
