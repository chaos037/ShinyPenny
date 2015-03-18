package com.mad.shinypenny;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    EditText budgetId;
    EditText budgetName;
    TextView textView;
    private BudgetDataSource budgetDataSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        budgetDataSource = new BudgetDataSource(this);
        try {
            budgetDataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        budgetId = (EditText)findViewById(R.id.editTextID);
        budgetName = (EditText)findViewById(R.id.editTextName);
        textView = (TextView)findViewById(R.id.textViewN);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void addBudget(View view){
        Budget budget = new Budget(budgetId.getText().toString(),budgetName.getText().toString());
        long l = budgetDataSource.createBudget(budget);
        if(l!=-1) {
            Log.e(null, String.valueOf( l));
            budgetId.setText(null);
            budgetName.setText(null);
        }
    }

    public void retrieveBudget(View view){
        Budget budget = budgetDataSource.getBudget(budgetId.getText().toString());
        budgetId.setText(budget.getID());
        budgetName.setText(budget.getName());
    }

    public void updateBudget(View view){
        Budget budget = new Budget(budgetId.getText().toString(),budgetName.getText().toString());
        budgetDataSource.updateBudget(budget);
        budgetId.setText(null);
        budgetName.setText(null);
    }

    public void deleteBudget(View view){
        budgetDataSource.deleteBudget(budgetId.getText().toString());
    }

    public void retrieveAllBudget(View view){
        List<Budget> budgetList = budgetDataSource.retrieveAllBudget();
        textView.setText((String.format("%2d",budgetList.size())));


    }


    public void addBudgett(View view) {
        Intent intent = new Intent(this,AddNewBudget.class);
        startActivity(intent);
    }
    //----------------dhskjjkhjksjdhlhlhlh
}
