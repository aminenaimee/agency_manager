package ma.formations.ioc.serviceflight.dto;

import java.time.LocalDate;

public class FlightDto {
    private Long id;
    private String name;
    private String departure;
    private String arrival;
    private LocalDate departureTime;
    private LocalDate arrivalTime;
    private String duration;
    private String price;
    private String airline;
    private String imageUrl;
    private String description;
    private boolean status;
    private String type;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private Long userId;
    private String imagePaths;
    public FlightDto() {
    }

    public FlightDto(Long id, String name, String departure, String arrival, LocalDate departureTime, LocalDate arrivalTime, String duration, String price, String airline, String imageUrl, String description, boolean status, String type, LocalDate departureDate, LocalDate arrivalDate, Long userId, String imagePaths) {
        this.id = id;
        this.name = name;
        this.departure = departure;
        this.arrival = arrival;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        this.price = price;
        this.airline = airline;
        this.imageUrl = imageUrl;
        this.description = description;
        this.status = status;
        this.type = type;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.userId = userId;
        this.imagePaths = imagePaths;
    }

    public FlightDto(String name, String departure, String arrival, LocalDate departureTime, LocalDate arrivalTime, String duration, String price, String airline, String imageUrl, String description, boolean status, String type, LocalDate departureDate, LocalDate arrivalDate, Long userId , String imagePaths) {
        this.name = name;
        this.departure = departure;
        this.arrival = arrival;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        this.price = price;
        this.airline = airline;
        this.imageUrl = imageUrl;
        this.description = description;
        this.status = status;
        this.type = type;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.userId = userId;
        this.imagePaths = imagePaths;
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

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public LocalDate getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDate departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDate getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDate arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getImagePaths() {
        return imagePaths;
    }
    public void setImagePaths(String imagePaths) {
        this.imagePaths = imagePaths;
    }
}
