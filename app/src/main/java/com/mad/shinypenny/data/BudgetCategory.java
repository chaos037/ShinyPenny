package com.mad.shinypenny.data;

/**
 * Created by WeiYang on 3/16/2015.
 */
public class BudgetCategory {

    private String ID;
    private String Name;

    public BudgetCategory(String ID, String name) {
        this.ID = ID;
        Name = name;
    }

    public BudgetCategory() {

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
