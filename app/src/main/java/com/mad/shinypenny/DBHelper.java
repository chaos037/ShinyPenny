package com.mad.shinypenny;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by WeiYang on 3/16/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int version = 1;

    // Database Name
    private static final String name = "shinnyPenny";

    // Table Names
    public static final String TABLE_Expense = "expense";
    public static final String TABLE_Income = "income";
    public static final String TABLE_Budget = "budget";
    public static final String TABLE_TransactionItem = "transactionItem";
    public static final String TABLE_RecurringItem = "recurringItem";

//    // Common column names
//    private static final String KEY_ID = "id";
//    private static final String KEY_CREATED_AT = "created_at";

    // Expense , Income , Budget Table - column nmaes
    public static final String KEY_id = "id";
    public static final String KEY_name = "name";

    // TransactionItem Table - column names
    public static final String KEY_recurr_ID = "recurrid";
    public static final String KEY_category_ID = "categoryid";
    public static final String KEY_date = "date";
    public static final String KEY_value = "value";

    // RecurringItem Table - column names
    public static final String KEY_endDate = "endDate";
    public static final String KEY_beginDate = "beingDate";
    public static final String KEY_cycle = "cycle";

    // Table Create Statements
    // Expense table create statement
    private static final String CREATE_TABLE_Expense = "CREATE TABLE " + TABLE_Expense + "("
            + KEY_id + " TEXT PRIMARY KEY,"
            + KEY_name + " TEXT)";

    // Income table create statement
    private static final String CREATE_TABLE_Income = "CREATE TABLE " + TABLE_Income + "("
            + KEY_id + " TEXT PRIMARY KEY,"
            + KEY_name + " TEXT)";

    // Budget table create statement
    private static final String CREATE_TABLE_Budget = "CREATE TABLE " + TABLE_Budget + "("
            + KEY_id + " TEXT PRIMARY KEY,"
            + KEY_name + " TEXT)";

    //TransactionItem table create statement
    private static final String CREATE_TABLE_TRANSACTIONITEM = "CREATE TABLE "+ TABLE_TransactionItem + "("
            + KEY_id + " TEXT PRIMARY KEY,"
            + KEY_recurr_ID + " TEXT,"
            + KEY_category_ID + " TEXT,"
            + KEY_date + " Date,"
            + KEY_value + " double)";

    //RecurringItem table create statement
    private static final String CREATE_TABLE_RECURRINGTEM = "CREATE TABLE " + TABLE_RecurringItem + "("
            + KEY_id + " TEXT PRIMARY KEY,"
            + KEY_category_ID + " TEXT,"
            + KEY_cycle + " TEXT,"
            + KEY_beginDate + " Date,"
            + KEY_endDate + " Date,"
            + KEY_value + " double)";

    private static final String Insert_Budget_Category = "INSERT INTO " + TABLE_Budget + " VALUES('B1', 'Car Loan');"
            + " INSERT INTO " + TABLE_Budget + " VALUES('B2', 'Edu Loan');"
            + " INSERT INTO " + TABLE_Budget + " VALUES('B3', 'House Loan');"
            + " INSERT INTO " + TABLE_Budget + " VALUES('B4', 'Insurance Loan');"
           + " INSERT INTO " + TABLE_Budget + " VALUES('B5', 'Other');";

    private static final String Insert_Expense_Category = "INSERT INTO " + TABLE_Expense + " VALUES('E1','Rent');"
            + " INSERT INTO " + TABLE_Expense + " VALUES('E2','Entertainment');"
            + " INSERT INTO " + TABLE_Expense + " VALUES('E3','Fashion');"
            + " INSERT INTO " + TABLE_Expense + " VALUES('E4','Computer equipment');"
            + " INSERT INTO " + TABLE_Expense + " VALUES('E5','Phone');"
            + " INSERT INTO " + TABLE_Expense + " VALUES('E6','Other');";

    private static final String Insert_Income_Category = "INSERT INTO " + TABLE_Income + " VALUES('I1','Salary');"
            + " INSERT INTO " + TABLE_Income + " VALUES('I2','Bonus');"
            + " INSERT INTO " + TABLE_Income + " VALUES('I3','Pension');"
            + " INSERT INTO " + TABLE_Income + " VALUES('I4','Part-time Work');"
            + " INSERT INTO " + TABLE_Income + " VALUES('I5','Other');";

    public DBHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_Expense);
        String[] queries = Insert_Expense_Category.split(";");
        for(String query : queries){
            db.execSQL(query);
        }
        db.execSQL(CREATE_TABLE_Income);
        queries = Insert_Income_Category.split(";");
        for(String query : queries){
            db.execSQL(query);
        }
        db.execSQL(CREATE_TABLE_Budget);
        queries = Insert_Budget_Category.split(";");
        for(String query : queries){
            db.execSQL(query);
        }
        db.execSQL(CREATE_TABLE_TRANSACTIONITEM);
        db.execSQL(CREATE_TABLE_RECURRINGTEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Expense);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Income);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Budget);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TransactionItem);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RecurringItem);

        // create new tables
        onCreate(db);
    }

    //Creating a Expense

//    public long createExpense(Expense expense) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_id, String.valueOf(expense.getID()));
//        values.put(KEY_name, String.valueOf(expense.getName()));
//
//        // insert row
//        long expense_id = db.insert(TABLE_Expense, null, values);
//
//        return expense_id;
//
//    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }


}
