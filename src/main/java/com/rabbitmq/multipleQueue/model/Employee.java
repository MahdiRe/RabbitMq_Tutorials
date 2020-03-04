package com.rabbitmq.multipleQueue.model;

public class Employee {

    private int employeeId;
    private String employeeName;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @Override
    public String toString() {
        return "[ Employee Id: " + this.employeeId + " ," + " Employee Name: " + this.employeeName + " ]";
    }
}
