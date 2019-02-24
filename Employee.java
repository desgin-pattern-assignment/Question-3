package question3;

import java.util.ArrayList;

public class Employee {
    private static int numberOfEmployees = 0;
    private int id;
    private String name;
    private String jobTitle;
    private int salary;
    private ArrayList<Employee> subordinates;
    private Employee boss = null;

    public Employee(String name, String jobTitle, int salary) {
        this.name = name;
        this.jobTitle = jobTitle;
        this.salary = salary;
        subordinates = new ArrayList<>();
        setID();
    }

    public int getID() {
        return id;
    }
    
    private void setID() {
        numberOfEmployees++;
        this.id = numberOfEmployees;
    }
    
    public void add(Employee e) {
        subordinates.add(e);
        e.setBoss(this);
    }

    public void remove(Employee e) {
        subordinates.remove(e);
        e.setBoss(null);
    }
    
    public String getName() {
        return name;
    }
    
    public String getJobTitle() {
        return jobTitle;
    }
    
    public int getSalary() {
        return salary;
    }

    public ArrayList<Employee> getSubordinates(){
        return subordinates;
    }
    
    public Employee getBoss() {
        return boss;
    }
    
    public void setBoss(Employee boss) {
        this.boss = boss;
    }
    
    public int getControlSpan(){
        int totalSalary = salary;
        if (subordinates.isEmpty()) {
            return totalSalary;
        }
        for (Employee subordinate : subordinates) {
            totalSalary += subordinate.getControlSpan();
        }
        return totalSalary;
    }

    @Override
    public String toString(){
        return ("ID: " + id + "\n" + name + "\n" + jobTitle + "\n$" + salary + "\n$" + getControlSpan());
    }
}