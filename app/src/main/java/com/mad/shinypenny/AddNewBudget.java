package com.mad.shinypenny;


import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.mad.shinypenny.Logic.BudgetDataSource;
import com.mad.shinypenny.data.BudgetCategory;
import com.mad.shinypenny.data.RecurringItem;
import com.mad.shinypenny.data.TransactionItem;

import java.sql.SQLException;
import java.util.List;


public class AddNewBudget extends ActionBarActivity {

    Spinner spnBudgetCategory;
    Spinner spnCycleCategory;
    private BudgetDataSource budgetDataSource;
    EditText edtBudgetAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        edtBudgetAmount = (EditText) findViewById(R.id.newBudgetAmount);

        budgetDataSource = new BudgetDataSource(this);
        try {
            budgetDataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        initializeBudgetSpinner();
        initializeCycleSpinner();
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
        String[] cycleItem = {"Only Once","Daily", "Weekly", "Bi-Weekly", "Monthly", "Yearly"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cycleItem);
        spnCycleCategory.setAdapter(adapter);
        spnCycleCategory.setSelection(0);
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

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }

    public void addNewBudget(View view) {
        RecurringItem newRecurringItem = new RecurringItem();
        TransactionItem newTransactionItem = new TransactionItem();

        spnBudgetCategory.getSelectedItem().toString();
        spnCycleCategory.getSelectedItem().toString();
        edtBudgetAmount.getText().toString();

    }
}


