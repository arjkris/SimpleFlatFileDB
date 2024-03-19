package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DB db = new DB("pats.db");
        if(db.recordsCount()==0)
        {
            Data d1 = new Data(0,"kris",28,"India");
            Data d2 = new Data(1,"mathew",19,"Brazil");
            Data d3 = new Data(2,"logan",65,"USA");
            Data d4 = new Data(3,"sunny",43,"Canada");
            db.append(d1);
            db.append(d2);
            db.append(d3);
            db.append(d4);
        }
        Data d5 = new Data(2,"logan2",63,"USA");
        db.update(d5);
        print(db);
    }

    public static void print(DB db) throws IOException {
        for (int i = 0;i<db.recordsCount();i++)
        {
            Data d = db.select(i);
            System.out.println(d.getId()+"|"+d.getName()+"|"+d.getAge()+"|"+d.getCountry());
        }
    }
}