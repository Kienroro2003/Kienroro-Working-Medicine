package com.example.medicinesalesmanagement.dto;

import java.time.LocalDateTime;

public class MedicineUpdateDto {
    private Integer id_medicine;
    private String nameMedicine;
    private String unit;
    private double price;
    private int quantity;
    private LocalDateTime productionDate;
    private LocalDateTime expirationDate;
    private EmployeeDto employee;

    public MedicineUpdateDto() {
    }

    public MedicineUpdateDto(Integer id_medicine, String name, String unit, double price, int quantity, LocalDateTime productionDate, LocalDateTime expirationDate, EmployeeDto employee) {
        this.id_medicine = id_medicine;
        this.nameMedicine = name;
        this.unit = unit;
        this.price = price;
        this.quantity = quantity;
        this.productionDate = productionDate;
        this.expirationDate = expirationDate;
        this.employee = employee;
    }

    public Integer getId_medicine() {
        return id_medicine;
    }

    public void setId_medicine(Integer id_medicine) {
        this.id_medicine = id_medicine;
    }

    public String getNameMedicine() {
        return nameMedicine;
    }

    public void setNameMedicine(String nameMedicine) {
        this.nameMedicine = nameMedicine;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDateTime productionDate) {
        this.productionDate = productionDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "MedicineUpdateDto{" +
                "id_medicine=" + id_medicine +
                ", name='" + nameMedicine + '\'' +
                ", unit='" + unit + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", productionDate=" + productionDate +
                ", expirationDate=" + expirationDate +
                ", employee=" + employee +
                '}';
    }
}
