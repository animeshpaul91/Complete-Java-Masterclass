package org.animesh.javabrains.rest.model;

public class MyDate {
    private int date, month, year;

    public MyDate() {
    }

    public MyDate(int date, int month, int year) {
        this.date = date;
        this.month = month;
        this.year = year;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object obj) {
        MyDate that = (MyDate) obj;
        return this.date == that.date && this.month == that.month && this.year == that.year;
    }

    @Override
    public String toString() {
        return "MyDate{" +
                "date=" + date +
                ", month=" + month +
                ", year=" + year +
                '}';
    }
}
