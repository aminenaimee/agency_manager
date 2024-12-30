package ma.formations.ioc.servicehotel.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import ma.formations.ioc.servicehotel.entity.Hotel;
import ma.formations.ioc.servicehotel.enums.RoomType;

import java.time.LocalDate;

@Data
public class RoomDto {
    private Long id;
    private String name;
    private int beds;
    private double price;
    private String description;
    private boolean available;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private RoomType type;
    private Hotel hotel;

    public RoomDto() {
    }

    public RoomDto(Long id, String name, int beds, double price, String description, boolean available, LocalDate checkIn, LocalDate checkOut, RoomType type, Hotel hotel) {
        this.id = id;
        this.name = name;
        this.beds = beds;
        this.price = price;
        this.description = description;
        this.available = available;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.type = type;
        this.hotel = hotel;
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

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}