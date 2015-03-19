package com.mad.shinypenny.Logic;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mad.shinypenny.data.DBHelper;
import com.mad.shinypenny.data.IncomeCategory;


public class IncomeDataSource {
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    // Creating a Income

    public long createIncome(IncomeCategory incomeCategory) {
        database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(dbHelper.KEY_iId, String.valueOf(incomeCategory.getID()));
        values.put(dbHelper.KEY_iName, String.valueOf(incomeCategory.getName()));

        // insert row
        long income_id = database.insert(dbHelper.TABLE_Income, null, values);

        return income_id;
    }

    /*
* get single income
*/
    public IncomeCategory getIncome(long income_id) {
        database = dbHelper.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + dbHelper.TABLE_Income + " WHERE "
                + dbHelper.KEY_iId + " = " + income_id;

        Cursor c = database.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        IncomeCategory incomeCategory = new IncomeCategory();
        incomeCategory.setID(c.getString(c.getColumnIndex(dbHelper.KEY_iId)));
        incomeCategory.setName((c.getString(c.getColumnIndex(dbHelper.KEY_iName))));

        return incomeCategory;
    }

    /*
* Updating a income
*/
    public int updateIncome(IncomeCategory incomeCategory) {
        database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(dbHelper.KEY_iId, incomeCategory.getID());
        values.put(dbHelper.KEY_iName, incomeCategory.getName());

        // updating row
        return database.update(dbHelper.TABLE_Income, values, dbHelper.KEY_iId + " = ?",
                new String[] { String.valueOf(incomeCategory.getID()) });
    }

    /*
* Deleting a income
*/
    public void deleteIncome(long income_id) {
        database = dbHelper.getWritableDatabase();
        database.delete(dbHelper.TABLE_Income, dbHelper.KEY_iId + " = ?",
                new String[] { String.valueOf(income_id) });
    }






}
