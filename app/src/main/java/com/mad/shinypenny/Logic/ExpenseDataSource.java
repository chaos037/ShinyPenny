package com.mad.shinypenny.Logic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.mad.shinypenny.data.DBHelper;
import com.mad.shinypenny.data.ExpenseCategory;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDataSource {

    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public ExpenseDataSource(Context context){
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public ExpenseDataSource(){}

    // Creating a Budget
    public long createExpense(ExpenseCategory expense) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.KEY_eId, expense.getID());
        values.put(dbHelper.KEY_eName, expense.getName());

        database = dbHelper.getWritableDatabase();

        // insert row
        long expense_id = database.insert(dbHelper.TABLE_Expense, null, values);

        return expense_id;
    }

    /*
* get single budget
*/
    public ExpenseCategory getExpense(String expense_id) {
        database = dbHelper.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + dbHelper.TABLE_Expense + " WHERE "
               + dbHelper.KEY_eId + " = '" + expense_id + "'";

        Cursor c = database.rawQuery(selectQuery, null);
        ExpenseCategory expense = new ExpenseCategory();


        if( c != null) {
            c.moveToFirst();
            expense.setID(c.getString(c.getColumnIndex(dbHelper.KEY_eId)));
            expense.setName((c.getString(c.getColumnIndex(dbHelper.KEY_eName))));
            c.close();
        }

        return expense;
    }


    /*
* Updating a budget
*/
    public int updateExpense(ExpenseCategory expense) {
        database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(dbHelper.KEY_eId, expense.getID());
        values.put(dbHelper.KEY_eName, expense.getName());

        // updating row
        return database.update(dbHelper.TABLE_Expense, values, dbHelper.KEY_eId + " = ?",
                new String[] { String.valueOf(expense.getID()) });
    }

    /*
* Deleting a budget
*/
    public void deleteExpense(String expense_id) {
        database = dbHelper.getWritableDatabase();
        database.delete(dbHelper.TABLE_Expense, dbHelper.KEY_eId + " = ?",
                new String[] { String.valueOf(expense_id) });
    }

    public List<ExpenseCategory> retrieveAllExpense(){
        List<ExpenseCategory> expenses = new ArrayList<>();
        database = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + dbHelper.TABLE_Expense;
        Cursor c = database.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                ExpenseCategory expense = new ExpenseCategory();
                expense.setID(c.getString(c.getColumnIndex(dbHelper.KEY_eId)));
                expense.setName((c.getString(c.getColumnIndex(dbHelper.KEY_eName))));

                // adding to transactionItem list
                expenses.add(expense);
            } while (c.moveToNext());
        }

        return expenses;
    }




}
