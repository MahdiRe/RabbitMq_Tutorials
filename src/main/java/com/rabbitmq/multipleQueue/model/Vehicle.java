package com.rabbitmq.multipleQueue.model;

public class Vehicle {

    private int vehicleId;
    private String vehicleType;

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public String toString() {
        return "[ Vehicle Id: " + this.vehicleId + " ," + " Vehicle Type: " + this.vehicleType + " ]";
    }
}
