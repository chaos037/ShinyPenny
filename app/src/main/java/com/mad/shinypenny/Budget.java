package com.mad.shinypenny;

/**
 * Created by WeiYang on 3/16/2015.
 */
public class Budget {

    private String ID;
    private String Name;

    public Budget(String ID, String name) {
        this.ID = ID;
        Name = name;
    }

    public Budget() {

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
