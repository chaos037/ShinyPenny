package com.mad.shinypenny.data;

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

    // Expense Category Table - column names
    public static final String KEY_eId = "eId";
    public static final String KEY_eName = "eName";

    // Budget Category Table - column names
    public static final String KEY_bId = "bId";
    public static final String KEY_bName = "bName";

    // Income Category Table - column names
    public static final String KEY_iId = "iId";
    public static final String KEY_iName = "iName";


    // TransactionItem Table - column names (recurr id below)
    public static final String KEY_tId = "tId";
    public static final String KEY_tCategory_ID = "tCategoryid";
    public static final String KEY_tDate = "tDate";
    public static final String KEY_tValue = "tValue";

    // RecurringItem Table - column names
    public static final String KEY_rId = "rid";
    public static final String KEY_rCategory_ID = "rCategoryid";
    public static final String KEY_rEndDate = "rEndDate";
    public static final String KEY_rBeginDate = "rBeginDate";
    public static final String KEY_rCycle = "rCycle";
    public static final String KEY_rValue = "rValue";

    // Table Create Statements
    // Expense table create statement
    private static final String CREATE_TABLE_Expense = "CREATE TABLE " + TABLE_Expense + "("
            + KEY_eId + " TEXT PRIMARY KEY,"
            + KEY_eName + " TEXT)";

    // Income table create statement
    private static final String CREATE_TABLE_Income = "CREATE TABLE " + TABLE_Income + "("
            + KEY_iId + " TEXT PRIMARY KEY,"
            + KEY_iName + " TEXT)";

    // Budget table create statement
    private static final String CREATE_TABLE_Budget = "CREATE TABLE " + TABLE_Budget + "("
            + KEY_bId + " TEXT PRIMARY KEY,"
            + KEY_bName + " TEXT)";

    //TransactionItem table create statement
    private static final String CREATE_TABLE_TRANSACTIONITEM = "CREATE TABLE "+ TABLE_TransactionItem + "("
            + KEY_tId + " TEXT PRIMARY KEY,"
            + KEY_rId + " TEXT,"
            + KEY_tCategory_ID + " TEXT,"
            + KEY_tDate + " Date,"
            + KEY_tValue + " double," +
            "FOREIGN KEY ("+ KEY_rId +") REFERENCES "+ TABLE_RecurringItem+"("+KEY_rId +"))";

    //RecurringItem table create statement
    private static final String CREATE_TABLE_RECURRINGTEM = "CREATE TABLE " + TABLE_RecurringItem + "("
            + KEY_rId + " TEXT PRIMARY KEY,"
            + KEY_rCategory_ID + " TEXT,"
            + KEY_rCycle + " TEXT,"
            + KEY_rBeginDate + " Date,"
            + KEY_rEndDate + " Date,"
            + KEY_rValue + " double," +
            "FOREIGN KEY ("+ KEY_rCategory_ID +") REFERENCES "+ TABLE_Expense+"("+KEY_eId +")," +
            "FOREIGN KEY ("+ KEY_rCategory_ID +") REFERENCES "+ TABLE_Budget+"("+KEY_bId +")," +
            "FOREIGN KEY ("+ KEY_rCategory_ID +") REFERENCES "+ TABLE_Income+"("+KEY_iId +"));";

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
        db.execSQL(CREATE_TABLE_RECURRINGTEM);
        db.execSQL(CREATE_TABLE_TRANSACTIONITEM);

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



    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }


}
