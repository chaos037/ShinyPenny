package com.mad.shinypenny;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.sql.SQLException;
import java.util.List;


public class AddNewBudget extends ActionBarActivity {

    Spinner spnBudgetCategory;
    Spinner spnCycleCategory;
    private BudgetDataSource budgetDataSource;
    EditText edtBudgetAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        edtBudgetAmount = (EditText)findViewById(R.id.newBudgetAmount);
        spnBudgetCategory = (Spinner) findViewById(R.id.budgetCategorySpinner);
        spnCycleCategory = (Spinner) findViewById(R.id.budgetCycleSpinner);

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

        List<Budget> budgetList = budgetDataSource.retrieveAllBudget();
        String[] budgetCategoryArray = new String[budgetList.size()];
        for (int i = 0; i < budgetList.size(); i++) {
            budgetCategoryArray[i] = budgetList.get(i).getName();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, budgetCategoryArray);
        spnBudgetCategory.setAdapter(adapter);
    }

    public void initializeCycleSpinner() {
        String[] cycleItem = {"Daily", "Weekly", "Bi-Weekly", "Monthly", "Yearly"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cycleItem);
        spnCycleCategory.setAdapter(adapter);
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
}
