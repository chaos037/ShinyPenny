package com.mad.shinypenny;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.mad.shinypenny.Logic.BudgetDataSource;
import com.mad.shinypenny.data.BudgetCategory;
import com.mad.shinypenny.data.RecurringItem;
import com.mad.shinypenny.data.TransactionItem;

import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public class AddNewBudget extends ActionBarActivity {

    Spinner spnBudgetCategory;
    Spinner spnCycleCategory;
    private BudgetDataSource budgetDataSource;
    EditText edtBudgetAmount;
    EditText budgetStartDate;
    EditText budgetEndDate;
    SimpleDateFormat dateFormatter;
    private DatePickerDialog startDatePickerDialog;
    private DatePickerDialog endDatePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        budgetDataSource = new BudgetDataSource(this);
        try {
            budgetDataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        edtBudgetAmount = (EditText) findViewById(R.id.newBudgetAmount);

        budgetStartDate = (EditText) findViewById(R.id.budgetStartDate);
        budgetStartDate.setInputType(InputType.TYPE_NULL);
        budgetStartDate.requestFocus();

        budgetEndDate = (EditText) findViewById(R.id.budgetEndDate);
        budgetEndDate.setInputType(InputType.TYPE_NULL);
        budgetEndDate.requestFocus();
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

        initializeBudgetSpinner();
        initializeCycleSpinner();
        setDateTimeField();
    }

    public void initializeBudgetSpinner() {
        spnBudgetCategory = (Spinner) findViewById(R.id.budgetCategorySpinner);
        List<BudgetCategory> budgetList = budgetDataSource.retrieveAllBudget();
        String[] budgetCategoryArray = new String[budgetList.size()];
        for (int i = 0; i < budgetList.size(); i++) {
            budgetCategoryArray[i] = budgetList.get(i).getName();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, budgetCategoryArray);
        spnBudgetCategory.setAdapter(adapter);
        spnBudgetCategory.setSelection(0);
    }

    public void initializeCycleSpinner() {
        spnCycleCategory = (Spinner) findViewById(R.id.budgetCycleSpinner);
        String[] cycleItem = {"Only Once", "Daily", "Weekly", "Bi-Weekly", "Monthly", "Yearly"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cycleItem);
        spnCycleCategory.setAdapter(adapter);
        spnCycleCategory.setSelection(0);
    }

    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        startDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                budgetStartDate.setText(dateFormatter.format(newDate.getTime()));
                endDatePickerDialog.getDatePicker().setMinDate(newDate.getTimeInMillis());

            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        endDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                budgetEndDate.setText(dateFormatter.format(newDate.getTime()));
                startDatePickerDialog.getDatePicker().setMaxDate(newDate.getTimeInMillis());
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void addNewBudget(View view) throws Exception{
        RecurringItem newRecurringItem = new RecurringItem();
        TransactionItem newTransactionItem;
        double amount = 0.0;
        Date startDate;
        Date endDate;
        String cycle;
        String category;
        spnBudgetCategory.getSelectedItemPosition();

        cycle = spnCycleCategory.getSelectedItem().toString();
        amount = Double.parseDouble(edtBudgetAmount.getText().toString());
        startDate = dateFormatter.parse(budgetStartDate.getText().toString());
        endDate = dateFormatter.parse(budgetEndDate.getText().toString());

        if(cycle.equals("Only Once")){
            newTransactionItem = new TransactionItem();
            newTransactionItem.setID("");
            newTransactionItem.getCategoryID();
        }else{


        }

//        BudgetCategory budget = new BudgetCategory(budgetId.getText().toString(),budgetName.getText().toString());
//        long l = budgetDataSource.createBudget(budget);
//        if(l!=-1) {
//            Log.e(null, String.valueOf( l));
//            budgetId.setText(null);
//            budgetName.setText(null);
//        }

    }


    public void promptStartDate(View view) {
        startDatePickerDialog.show();
    }

    public void promptEndDate(View view) {
        endDatePickerDialog.show();
    }
}


