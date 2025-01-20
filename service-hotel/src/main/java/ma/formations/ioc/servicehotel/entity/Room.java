package ma.formations.ioc.servicehotel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.formations.ioc.servicehotel.dto.RoomDto;
import ma.formations.ioc.servicehotel.enums.RoomType;

import java.time.LocalDate;


@Data
@Entity

public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int beds;
    private Long price;
    private String description;
    private boolean available;
    private LocalDate checkIn;
    private LocalDate checkOut;
    @Enumerated(EnumType.STRING)
    private RoomType type;
    private String imagePaths;
    private String imageUrl;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel")
    private Hotel hotel;

    public Room() {
    }

    public Room(Hotel hotel, String imageUrl, String imagePaths, RoomType type, LocalDate checkOut, LocalDate checkIn, boolean available, String description, Long price, int beds, String name, Long id) {
        this.hotel = hotel;
        this.imageUrl = imageUrl;
        this.imagePaths = imagePaths;
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

    public Room(RoomDto roomDto) {
        this.id = roomDto.getId();
        this.name = roomDto.getName();
        this.beds = roomDto.getBeds();
        this.price = roomDto.getPrice();
        this.description = roomDto.getDescription();
        this.available = roomDto.isAvailable();
        this.checkIn = roomDto.getCheckIn();
        this.checkOut = roomDto.getCheckOut();
        this.type = roomDto.getType();
        this.hotel = roomDto.getHotel();
        this.imagePaths =roomDto.getImagePaths();
        this.imageUrl =roomDto.getImageUrl();
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
