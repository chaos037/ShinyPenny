package com.mad.shinypenny.Logic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mad.shinypenny.data.DBHelper;
import com.mad.shinypenny.data.RecurringItem;
import com.mad.shinypenny.data.TransactionItem;

import java.sql.SQLException;

/**
 * Created by chin on 19/3/2015.
 */
public class RecurringItemDataSource {
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public RecurringItemDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public String generateID() {
        int iD = 1000;
        String getID = "", lastID = "", newID = "";

        String selectQuery = "SELECT " + DBHelper.KEY_rId + " FROM " + dbHelper.TABLE_RecurringItem;
        database = dbHelper.getReadableDatabase();
        Cursor c = database.rawQuery(selectQuery, null);

        if (c != null) {
            c.moveToLast();
            lastID = c.getString(c.getColumnIndex(dbHelper.KEY_rId));
            for (int i = 1; i < 5; i++) {
                getID += lastID.charAt(i);
            }
            iD = Integer.parseInt(getID) + 1;

            c.close();
        } else {
            iD = 1000;
        }
        newID = String.format("%d", "R" + iD);
        return newID;
    }



    public long createRecurringItem(RecurringItem recurringItem) {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        String newID = generateID();
        values.put(dbHelper.KEY_rId, newID);
        values.put(dbHelper.KEY_rCategory_ID, String.valueOf(recurringItem.getCategoryID()));
        values.put(dbHelper.KEY_rCycle, String.valueOf(recurringItem.getCycle()));
        values.put(dbHelper.KEY_rBeginDate, String.valueOf(recurringItem.getBeginDate()));
        values.put(dbHelper.KEY_rEndDate, String.valueOf(recurringItem.getEndDate()));
        values.put(dbHelper.KEY_rValue, String.valueOf(recurringItem.getValue()));

        // insert row
        long recurr_id = database.insert(dbHelper.TABLE_RecurringItem, null, values);

        return recurr_id;
    }


//    public List<TransactionItem> getAllTransactionItemWithinCurrentMonth(String transactionItem_category) throws ParseException {
//        List<TransactionItem> transactionItems = new ArrayList<TransactionItem>();
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//
//        //At the particular month
//        String selectQuery = "SELECT  * FROM " + dbHelper.TABLE_TransactionItem + " WHERE "
//                + dbHelper.KEY_category_ID + " = '" + transactionItem_category +
//                " AND strftime('%m'," + dbHelper.KEY_date + ") > strftime('%m', 'now')";
//
//
//        database = dbHelper.getReadableDatabase();
//        Cursor c = database.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (c.moveToFirst()) {
//            do {
//                TransactionItem transactionItem = new TransactionItem();
//                transactionItem.setID(c.getString(c.getColumnIndex(dbHelper.KEY_id)));
//                transactionItem.setRecurrID(c.getString(c.getColumnIndex(dbHelper.KEY_recurr_ID)));
//                transactionItem.setDate(formatter.parse(c.getString(c.getColumnIndex(dbHelper.KEY_date))));
//                transactionItem.setCategoryID(c.getString(c.getColumnIndex(dbHelper.KEY_category_ID)));
//                transactionItem.setValue(c.getDouble(c.getColumnIndex(dbHelper.KEY_value)));
//
//
//                // adding to transactionItem list
//                transactionItems.add(transactionItem);
//            } while (c.moveToNext());
//        }
//
//        return transactionItems;
//    }
//
//
//    /*
//* getting all budget under same category between 2 month
//* */
//    public List<TransactionItem> getAllTransactionItemBetweenTwoMonth(String transactionItem_category, String beginMonth, String endMonth) throws ParseException {
//        List<TransactionItem> transactionItems = new ArrayList<TransactionItem>();
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//
//        //At the particular month
//        String selectQuery = "SELECT  * FROM " + dbHelper.TABLE_TransactionItem + " WHERE "
//                + dbHelper.KEY_category_ID + " = '" + transactionItem_category + " AND "
//                + dbHelper.KEY_date + " between '" + beginMonth + "' and '" + endMonth + "'";
//
//
//        database = dbHelper.getReadableDatabase();
//        Cursor c = database.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (c.moveToFirst()) {
//            do {
//                TransactionItem transactionItem = new TransactionItem();
//                transactionItem.setID(c.getString(c.getColumnIndex(dbHelper.KEY_id)));
//                transactionItem.setRecurrID(c.getString(c.getColumnIndex(dbHelper.KEY_recurr_ID)));
//                transactionItem.setDate(formatter.parse(c.getString(c.getColumnIndex(dbHelper.KEY_date))));
//                transactionItem.setCategoryID(c.getString(c.getColumnIndex(dbHelper.KEY_category_ID)));
//                transactionItem.setValue(c.getDouble(c.getColumnIndex(dbHelper.KEY_value)));
//
//
//                // adding to transactionItem list
//                transactionItems.add(transactionItem);
//            } while (c.moveToNext());
//        }
//
//        return transactionItems;
//    }
//
//    /*
//* getting all budget under same category in the current date
//* */
//    public List<TransactionItem> getAllTransactionItemInCurrentDate(String transactionItem_category) throws ParseException {
//        List<TransactionItem> transactionItems = new ArrayList<TransactionItem>();
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//
//        //At the particular month
//        String selectQuery = "SELECT  * FROM " + dbHelper.TABLE_TransactionItem + " WHERE "
//                + dbHelper.KEY_category_ID + " = '" + transactionItem_category +
//                " AND " + dbHelper.KEY_date + " = 'now'";
//
//
//        database = dbHelper.getReadableDatabase();
//        Cursor c = database.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (c.moveToFirst()) {
//            do {
//                TransactionItem transactionItem = new TransactionItem();
//                transactionItem.setID(c.getString(c.getColumnIndex(dbHelper.KEY_id)));
//                transactionItem.setRecurrID(c.getString(c.getColumnIndex(dbHelper.KEY_recurr_ID)));
//                transactionItem.setDate(formatter.parse(c.getString(c.getColumnIndex(dbHelper.KEY_date))));
//                transactionItem.setCategoryID(c.getString(c.getColumnIndex(dbHelper.KEY_category_ID)));
//                transactionItem.setValue(c.getDouble(c.getColumnIndex(dbHelper.KEY_value)));
//
//
//                // adding to transactionItem list
//                transactionItems.add(transactionItem);
//            } while (c.moveToNext());
//        }
//
//        return transactionItems;
//    }
}
