package com.mad.shinypenny;

import java.util.Date;

/**
 * Created by WeiYang on 3/16/2015.
 */
public class RecurringItem {

    private String ID;
    private String CategoryID;
    private String Cycle;
    private Date BeginDate;
    private Date EndDate;
    private double Value;

    public RecurringItem(String ID, String categoryID, String cycle, Date beginDate, Date endDate, double value) {
        this.ID = ID;
        CategoryID = categoryID;
        Cycle = cycle;
        BeginDate = beginDate;
        EndDate = endDate;
        Value = value;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String categoryID) {
        CategoryID = categoryID;
    }

    public String getCycle() {
        return Cycle;
    }

    public void setCycle(String cycle) {
        Cycle = cycle;
    }

    public Date getBeginDate() {
        return BeginDate;
    }

    public void setBeginDate(Date beginDate) {
        BeginDate = beginDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public double getValue() {
        return Value;
    }

    public void setValue(double value) {
        Value = value;
    }
}
