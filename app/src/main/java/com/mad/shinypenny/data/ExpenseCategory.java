package com.mad.shinypenny.data;

/**
 * Created by WeiYang on 3/16/2015.
 */
public class ExpenseCategory {

    private String ID;
    private String Name;
    public ExpenseCategory() {

    }
    public ExpenseCategory(String ID, String name) {
        this.ID = ID;
        Name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

