package com.mad.shinypenny;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tarc on 3/17/2015.
 */
public class BudgetDataSource {

    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public BudgetDataSource(Context context){
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public BudgetDataSource(){}

    // Creating a Budget
    public long createBudget(Budget budget) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.KEY_id, budget.getID());
        values.put(dbHelper.KEY_name, budget.getName());

        database = dbHelper.getWritableDatabase();

        // insert row
        long budget_id = database.insert(dbHelper.TABLE_Budget, null, values);

        return budget_id;
    }

    /*
* get single budget
*/
    public Budget getBudget(String budget_id) {
        database = dbHelper.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + dbHelper.TABLE_Budget + " WHERE "
               + dbHelper.KEY_id + " = '" + budget_id + "'";

        Cursor c = database.rawQuery(selectQuery, null);
        Budget budget = new Budget();


        if( c != null) {
            c.moveToFirst();
            budget.setID(c.getString(c.getColumnIndex(dbHelper.KEY_id)));
            budget.setName((c.getString(c.getColumnIndex(dbHelper.KEY_name))));
            c.close();
        }else
         Log.e(null, "empty");

        return budget;
    }


    /*
* Updating a budget
*/
    public int updateBudget(Budget budget) {
        database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(dbHelper.KEY_id, budget.getID());
        values.put(dbHelper.KEY_name, budget.getName());

        // updating row
        return database.update(dbHelper.TABLE_Budget, values, dbHelper.KEY_id + " = ?",
                new String[] { String.valueOf(budget.getID()) });
    }

    /*
* Deleting a budget
*/
    public void deleteBudget(String budget_id) {
        database = dbHelper.getWritableDatabase();
        database.delete(dbHelper.TABLE_Budget, dbHelper.KEY_id + " = ?",
                new String[] { String.valueOf(budget_id) });
    }

    public List<Budget> retrieveAllBudget(){
        List<Budget> budgets = new ArrayList<>();
        database = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + dbHelper.TABLE_Budget;
        Cursor c = database.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Budget budget = new Budget();
                budget.setID(c.getString(c.getColumnIndex(dbHelper.KEY_id)));
                budget.setName((c.getString(c.getColumnIndex(dbHelper.KEY_name))));

                // adding to transactionItem list
                budgets.add(budget);
            } while (c.moveToNext());
        }

        return budgets;
    }


    /*
* getting all budget under same category in the current month
* */
    public List<TransactionItem> getAllTransactionItemWithinCurrentMonth(String transactionItem_category) throws ParseException {
        List<TransactionItem> transactionItems = new ArrayList<TransactionItem>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        //At the particular month
        String selectQuery = "SELECT  * FROM " + dbHelper.TABLE_TransactionItem + " WHERE "
                + dbHelper.KEY_category_ID + " = '" + transactionItem_category +
                " AND strftime('%m'," + dbHelper.KEY_date + ") > strftime('%m', 'now')";


        database = dbHelper.getReadableDatabase();
        Cursor c = database.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                TransactionItem transactionItem = new TransactionItem();
                transactionItem.setID(c.getString(c.getColumnIndex(dbHelper.KEY_id)));
                transactionItem.setRecurrID(c.getString(c.getColumnIndex(dbHelper.KEY_recurr_ID)));
                transactionItem.setDate(formatter.parse(c.getString(c.getColumnIndex(dbHelper.KEY_date))));
                transactionItem.setCategoryID(c.getString(c.getColumnIndex(dbHelper.KEY_category_ID)));
                transactionItem.setValue(c.getDouble(c.getColumnIndex(dbHelper.KEY_value)));


                // adding to transactionItem list
                transactionItems.add(transactionItem);
            } while (c.moveToNext());
        }

        return transactionItems;
    }


    /*
* getting all budget under same category between 2 month
* */
    public List<TransactionItem> getAllTransactionItemBetweenTwoMonth(String transactionItem_category, String beginMonth, String endMonth) throws ParseException {
        List<TransactionItem> transactionItems = new ArrayList<TransactionItem>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        //At the particular month
        String selectQuery = "SELECT  * FROM " + dbHelper.TABLE_TransactionItem + " WHERE "
                + dbHelper.KEY_category_ID + " = '" + transactionItem_category + " AND "
                + dbHelper.KEY_date + " between '" + beginMonth + "' and '" + endMonth + "'";


        database = dbHelper.getReadableDatabase();
        Cursor c = database.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                TransactionItem transactionItem = new TransactionItem();
                transactionItem.setID(c.getString(c.getColumnIndex(dbHelper.KEY_id)));
                transactionItem.setRecurrID(c.getString(c.getColumnIndex(dbHelper.KEY_recurr_ID)));
                transactionItem.setDate(formatter.parse(c.getString(c.getColumnIndex(dbHelper.KEY_date))));
                transactionItem.setCategoryID(c.getString(c.getColumnIndex(dbHelper.KEY_category_ID)));
                transactionItem.setValue(c.getDouble(c.getColumnIndex(dbHelper.KEY_value)));


                // adding to transactionItem list
                transactionItems.add(transactionItem);
            } while (c.moveToNext());
        }

        return transactionItems;
    }

    /*
* getting all budget under same category in the current date
* */
    public List<TransactionItem> getAllTransactionItemInCurrentDate(String transactionItem_category) throws ParseException {
        List<TransactionItem> transactionItems = new ArrayList<TransactionItem>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        //At the particular month
        String selectQuery = "SELECT  * FROM " + dbHelper.TABLE_TransactionItem + " WHERE "
                + dbHelper.KEY_category_ID + " = '" + transactionItem_category +
                " AND " + dbHelper.KEY_date + " = 'now'";


        database = dbHelper.getReadableDatabase();
        Cursor c = database.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                TransactionItem transactionItem = new TransactionItem();
                transactionItem.setID(c.getString(c.getColumnIndex(dbHelper.KEY_id)));
                transactionItem.setRecurrID(c.getString(c.getColumnIndex(dbHelper.KEY_recurr_ID)));
                transactionItem.setDate(formatter.parse(c.getString(c.getColumnIndex(dbHelper.KEY_date))));
                transactionItem.setCategoryID(c.getString(c.getColumnIndex(dbHelper.KEY_category_ID)));
                transactionItem.setValue(c.getDouble(c.getColumnIndex(dbHelper.KEY_value)));


                // adding to transactionItem list
                transactionItems.add(transactionItem);
            } while (c.moveToNext());
        }

        return transactionItems;
    }

}
