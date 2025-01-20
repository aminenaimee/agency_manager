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
    private Long price;
    private String description;
    private boolean available;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private RoomType type;
    private Hotel hotel;
    private String imagePaths;
    private String imageUrl;
    public RoomDto() {
    }

    public RoomDto(String imageUrl, String imagePaths, Hotel hotel, RoomType type, LocalDate checkOut, LocalDate checkIn, boolean available, String description, Long price, int beds, String name, Long id) {
        this.imageUrl = imageUrl;
        this.imagePaths = imagePaths;
        this.hotel = hotel;
        this.type = type;
        this.checkOut = checkOut;
        this.checkIn = checkIn;
        this.available = available;
        this.description = description;
        this.price = price;
        this.beds = beds;
        this.name = name;
        this.id = id;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
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

    public String getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(String imagePaths) {
        this.imagePaths = imagePaths;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}