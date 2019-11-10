package com.example.recyclerview;

public class Listitem {
    private String head;
    private String des;
    private String date;
    private int i;



    public Listitem(String head, String des, String date, int i) {
        this.head = head;
        this.des = des;
        this.date = date;
        this.i=i;
    }

    public String getHead() {
        return head;
    }

    public String getDate() {
        return date;
    }

    public String getDes() {
        return des;
    }
    public int getI() {
        return i;
    }
    public void change(String s){
        head=s;
    }
}
