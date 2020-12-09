package com.Animesh.Comparators;

import java.util.*;

class MyComp implements Comparator<Account> {
    @Override
    public int compare(Account a1, Account a2) {
        int i = a1.getName().lastIndexOf(" ") + 1;
        int j = a2.getName().lastIndexOf(" ") + 1;

        String lastName1 = a1.getName().substring(i);
        String lastName2 = a2.getName().substring(j);
        int c = lastName1.compareTo(lastName2);
        return c != 0 ? c: a1.getName().compareTo(a2.getName());
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}

public class Main {

    public static void main(String[] args) {
	// write your code here
        Account animesh = new Account("Animesh Paul", 28, 10000.00);
        Account sandeep = new Account("Sandeep Borpatra Gohain", 28, 12000.00);
        Account swagat = new Account("Swagat Bhattacharya", 27, 15000.00);
        Account boris = new Account("Boris Laishram", 28, 10000.00);
        Account praveen = new Account("Praveen Chhetri", 28, 15000.00);
        Account rajeev = new Account("Rajeev Chhetri", 28, 12000.00);

        Set<Account> set = new TreeSet<>(new MyComp());
        set.add(animesh);
        set.add(sandeep);
        set.add(swagat);
        set.add(boris);
        set.add(praveen);
        set.add(rajeev);

        for (Account account : set) {
            System.out.println(account);
        }

        System.out.println("======================================================");

        TreeMap<Account, String> map = new TreeMap<>((Account a1, Account a2) -> {
            return a1.getName().compareTo(a2.getName());
        });

        map.put(animesh, "Animesh Paul");
        map.put(sandeep, "Sandeep Borpatra Gohain");
        map.put(swagat, "Swagat Bhattacharya");
        map.put(boris, "Boris Laishram");
        map.put(praveen, "Praveen Chhetri");
        map.put(rajeev, "Rajeev Chhetri");

        for(Map.Entry<Account, String> item: map.entrySet()) {
            System.out.println(item.getKey());
        }
    }
}
