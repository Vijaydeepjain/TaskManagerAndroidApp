package com.example.taskmanager;

public class model2 {
    String name,time,date;

    public model2() {
    }

    public model2(String name, String time, String date) {
        this.name = name;
        this.time = time;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
