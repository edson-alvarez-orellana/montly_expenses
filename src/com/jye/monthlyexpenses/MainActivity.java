package com.jye.monthlyexpenses;

import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.jye.monthlyexpenses.data.DatabaseHelper;
import com.jye.monthlyexpenses.model.Expense;
import com.jye.monthlyexpenses.model.Schedule;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	/**
	 * Main buttons
	 */
	private Button buttonViewExpenses;
	private Button buttonAddExpense;
	private Button buttonExit;
	/**
	 * Expense dialog fields	
	 */
	private EditText editTextExpense;
	private EditText editTextPrice;

	private Schedule schedule;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d(TAG, "onCreate...");
		initialize();
	}

	private void initialize() {
		/**
		 * Initialize view expenses button
		 */
		buttonViewExpenses = (Button)findViewById(R.id.buttonViewExpenses);
		buttonViewExpenses.setOnClickListener(expensesDetailButtonListener);
		/**
		 * Initialize add expense button
		 */
		buttonAddExpense = (Button)findViewById(R.id.buttonAddExpense);
		buttonAddExpense.setOnClickListener(addExpenseButtonListener);
		/**
		 * Initialize exit button
		 */
		buttonExit = (Button) findViewById(R.id.buttonExit);
		buttonExit.setOnClickListener(exitButtonListener);

		schedule = new Schedule();
		
		DatabaseHelper.dropTable(this);
		DatabaseHelper.createTable(this);
		//DatabaseHelper.fillDatabase(this);
	}

	private OnClickListener exitButtonListener = new OnClickListener() {

		public void onClick(android.view.View v) {
			getExitDialog().show();
		}
	};

	private OnClickListener addExpenseButtonListener = new OnClickListener() {

		public void onClick(android.view.View v) {
			getAddExpenseDialog().show();
		}
	};

	private OnClickListener expensesDetailButtonListener = new OnClickListener() {

		public void onClick(android.view.View v) {
			getExpensesDetailsDialog().show();
		}
	};

	public AlertDialog getExitDialog() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

		alertDialogBuilder.setTitle(R.string.exit_title);

		alertDialogBuilder
		.setMessage(R.string.exit_message)
		.setCancelable(false)
		.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				MainActivity.this.finish();
			}
		})
		.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				dialog.cancel();
			}
		});

		AlertDialog alertDialog = alertDialogBuilder.create();

		return alertDialog;
	}

	public Dialog getAddExpenseDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		LayoutInflater inflater = this.getLayoutInflater();

		final View view = inflater.inflate(R.layout.new_expense, null); 

		builder.setView(view)
		.setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {

				editTextExpense = (EditText)view.findViewById(R.id.editTextExpense);
				editTextPrice = (EditText)view.findViewById(R.id.editTextPrice);

				addExpense();
			}
		})
		.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});      
		return builder.create();
	}

	public AlertDialog getExpensesDetailsDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setMessage(DatabaseHelper.showTableValues(this))
		.setTitle("Expenses");

		// Add the buttons
		builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});

		return builder.create();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void addExpense() {
		if(editTextExpense != null && editTextPrice != null) {
			
			Expense expense = new Expense();
			expense.setName(editTextExpense.getText().toString());
			expense.setPrice(Float.parseFloat(editTextPrice.getText().toString()));
			expense.setDate(new Date());
			schedule.getCurrentMonth().addExpense(expense);
			DatabaseHelper.insertIntoTable(this, expense);
		} else {
			Log.d(TAG, "Expense name and Price NULL...");
		}
	}
}
