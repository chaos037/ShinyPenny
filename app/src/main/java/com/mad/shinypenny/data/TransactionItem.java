package com.mad.shinypenny.data;

import java.util.Date;

/**
 * Created by WeiYang on 3/16/2015.
 */
public class TransactionItem {

    private String ID;
    private String RecurrID;
    private String CategoryID;
    private Date date;
    private double Value;

    public TransactionItem(String ID, String recurrID, String categoryID, Date date, double value) {
        this.ID = ID;
        RecurrID = recurrID;
        CategoryID = categoryID;
        this.date = date;
        Value = value;
    }

    public TransactionItem() {

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getRecurrID() {
        return RecurrID;
    }

    public void setRecurrID(String recurrID) {
        RecurrID = recurrID;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String categoryID) {
        CategoryID = categoryID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getValue() {
        return Value;
    }

    public void setValue(double value) {
        Value = value;
    }

}
