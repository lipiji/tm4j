package com.lipiji.tm.rcrp;

import java.util.ArrayList;
import java.util.List;

public class CRP {
    
    public void generate(int N, double alpha) {
        List<Table> tables = new ArrayList<>();
        Table t = new Table(1);
        Person p = new Person("1", 1);
        t.sitdown(p);
        tables.add(t);
        
        for (int i = 2; i < N; i++) {
            p = new Person(Integer.toString(i), i);
            double r = Math.random();
            // see if exsiting tables
            boolean exsit = false;
            for (Table tb : tables) {
                if (r > (tb.getCustomerCount() / (i - 1 + alpha))) {
                    tb.sitdown(p);
                    exsit = true;
                    break;
                }
            }
            // new table
            if (!exsit) {
                t = new Table(tables.size() + 1);
                t.sitdown(p);
                tables.add(t);
            }
        }
        
        // print
        System.out.println("#Tables = " + tables.size());
        for (Table tb : tables) {
            System.out.println("#Customers = " + tb.getCustomerCount() + " ~~ " + tb.getCustomers());
        }
        System.out.println("\n\n");
    }
    
    public static void main(String args[]) {
        CRP crp = new CRP();
        crp.generate(1000, 0.1);
        crp.generate(1000, 1.0);
        crp.generate(1000, 10.0);
        crp.generate(1000, 100.0);
    }

    private class Person {
        private int id;
        private String name;
        
        public Person(String name, int id){
            this.name = name;
            this.id = id;
        }
        
        @Override
        public String toString() {
            return name;
        }
    }
    
    private class Table {
        private int id;
        private List<Person> customers;
        
        public Table(int id) {
            this.id = id;
            this.customers = new ArrayList<>();
        }
        
        public void sitdown(Person p) {
            this.customers.add(p);
        }
        
        public int getCustomerCount() {
            return customers.size();
        }
        
        public List<Person> getCustomers() {
            return customers;
        }
    }
}
