package com.example.hw1618autoservice.model;

public class ServiceOrder {
    private long id;
    private String customerName;
    private String carModel;
    private String serviceDescription;

    public ServiceOrder(long id, String customerName, String carModel, String serviceDescription) {
        this.id = id;
        this.customerName = customerName;
        this.carModel = carModel;
        this.serviceDescription = serviceDescription;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }
}