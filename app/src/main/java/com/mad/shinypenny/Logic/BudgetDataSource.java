package com.mad.shinypenny.Logic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mad.shinypenny.data.DBHelper;
import com.mad.shinypenny.data.BudgetCategory;
import com.mad.shinypenny.data.TransactionItem;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
    public long createBudget(BudgetCategory budget) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.KEY_bId, budget.getID());
        values.put(dbHelper.KEY_bName, budget.getName());

        database = dbHelper.getWritableDatabase();

        // insert row
        long budget_id = database.insert(dbHelper.TABLE_Budget, null, values);

        return budget_id;
    }

    /*
* get single budget
*/
    public BudgetCategory getBudget(String budget_id) {
        database = dbHelper.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + dbHelper.TABLE_Budget + " WHERE "
               + dbHelper.KEY_bId + " = '" + budget_id + "'";

        Cursor c = database.rawQuery(selectQuery, null);
        BudgetCategory budget = new BudgetCategory();


        if( c != null) {
            c.moveToFirst();
            budget.setID(c.getString(c.getColumnIndex(dbHelper.KEY_bId)));
            budget.setName((c.getString(c.getColumnIndex(dbHelper.KEY_bName))));
            c.close();
        }else
         Log.e(null, "empty");

        return budget;
    }


    /*
* Updating a budget
*/
    public int updateBudget(BudgetCategory budget) {
        database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(dbHelper.KEY_bId, budget.getID());
        values.put(dbHelper.KEY_bName, budget.getName());

        // updating row
        return database.update(dbHelper.TABLE_Budget, values, dbHelper.KEY_bId + " = ?",
                new String[] { String.valueOf(budget.getID()) });
    }

    /*
* Deleting a budget
*/
    public void deleteBudget(String budget_id) {
        database = dbHelper.getWritableDatabase();
        database.delete(dbHelper.TABLE_Budget, dbHelper.KEY_bId + " = ?",
                new String[] { String.valueOf(budget_id) });

    }

    public List<BudgetCategory> retrieveAllBudget(){
        List<BudgetCategory> budgets = new ArrayList<>();
        database = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + dbHelper.TABLE_Budget;
        Cursor c = database.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                BudgetCategory budget = new BudgetCategory();
                budget.setID(c.getString(c.getColumnIndex(dbHelper.KEY_bId)));
                budget.setName((c.getString(c.getColumnIndex(dbHelper.KEY_bName))));

                // adding to transactionItem list
                budgets.add(budget);
            } while (c.moveToNext());
        }

        return budgets;
    }




}
