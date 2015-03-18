package com.mad.shinypenny;

/**
 * Created by WeiYang on 3/16/2015.
 */
public class Income {

    private String ID;
    private String Name;

    public Income(String ID, String name) {
        this.ID = ID;
        Name = name;
    }

    public Income() {

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
