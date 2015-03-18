package com.mad.shinypenny;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by WeiYang on 3/17/2015.
 */
public class IncomeDataSource {
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    // Creating a Income

    public long createIncome(Income income) {
        database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(dbHelper.KEY_id, String.valueOf(income.getID()));
        values.put(dbHelper.KEY_name, String.valueOf(income.getName()));

        // insert row
        long income_id = database.insert(dbHelper.TABLE_Income, null, values);

        return income_id;
    }

    /*
* get single income
*/
    public Income getIncome(long income_id) {
        database = dbHelper.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + dbHelper.TABLE_Income + " WHERE "
                + dbHelper.KEY_id + " = " + income_id;

        Cursor c = database.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Income income = new Income();
        income.setID(c.getString(c.getColumnIndex(dbHelper.KEY_id)));
        income.setName((c.getString(c.getColumnIndex(dbHelper.KEY_name))));

        return income;
    }

    /*
* Updating a income
*/
    public int updateIncome(Income income) {
        database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(dbHelper.KEY_id, income.getID());
        values.put(dbHelper.KEY_name, income.getName());

        // updating row
        return database.update(dbHelper.TABLE_Income, values, dbHelper.KEY_id + " = ?",
                new String[] { String.valueOf(income.getID()) });
    }

    /*
* Deleting a income
*/
    public void deleteIncome(long income_id) {
        database = dbHelper.getWritableDatabase();
        database.delete(dbHelper.TABLE_Income, dbHelper.KEY_id + " = ?",
                new String[] { String.valueOf(income_id) });
    }






}
