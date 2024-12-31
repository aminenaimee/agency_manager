package ma.formations.ioc.servicehotel.dto;

import java.time.LocalDate;

public class VehicleDto {  private Long id;
    private String name;
    private String brand;
    private String model;
    private String registrationNumber;
    private String imageUrl;
    private String description;
    private boolean status;
    private String fuel;
    private String transmission;
    private String price;
    private String year;
    private String imagePaths;
    private LocalDate rentStartDate;
    private LocalDate rentEndDate;

    public VehicleDto() {
    }

    public VehicleDto(Long id, String name, String brand, String model, String registrationNumber, String imageUrl, String description, boolean status, String fuel, String transmission, String price, String year, String imagePaths, LocalDate rentStartDate, LocalDate rentEndDate) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.imageUrl = imageUrl;
        this.description = description;
        this.status = status;
        this.fuel = fuel;
        this.transmission = transmission;
        this.price = price;
        this.year = year;
        this.imagePaths = imagePaths;
        this.rentStartDate = rentStartDate;
        this.rentEndDate = rentEndDate;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(String imagePaths) {
        this.imagePaths = imagePaths;
    }

    public LocalDate getRentStartDate() {
        return rentStartDate;
    }

    public void setRentStartDate(LocalDate rentStartDate) {
        this.rentStartDate = rentStartDate;
    }

    public LocalDate getRentEndDate() {
        return rentEndDate;
    }

    public void setRentEndDate(LocalDate rentEndDate) {
        this.rentEndDate = rentEndDate;
    }
}
