package ma.formations.ioc.servicehotel.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import ma.formations.ioc.servicehotel.dto.HotelDto;

import java.util.List;

@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    private int stars;
    private double price;
    private String description;
    private String phone ;
    private String email;
    private String address;
    private String imagePaths;
    private String imageUrl;




    @OneToMany(mappedBy = "hotel")
    @ToString.Exclude
    @JsonManagedReference
    private List<Room> rooms;

    public  Hotel(){};



    public HotelDto getHotelDto(){
        HotelDto dto = new HotelDto();
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setCity(this.city);
        dto.setStars(this.stars);
        dto.setPrice(this.price);
        dto.setDescription(this.description);
        dto.setPhone(this.phone);
        dto.setEmail(this.email);
        dto.setAddress(this.address);
        dto.setRooms(this.rooms);
        dto.setImagePaths(this.getImagePaths());
        dto.setImageUrl(this.getImageUrl());
        return dto;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", stars=" + stars +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", rooms=" + rooms +
                '}';
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Hotel(Long id, String name, String city, int stars, double price, String description, String phone, String email, String address, String imagePaths, String imageUrl, List<Room> rooms) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.stars = stars;
        this.price = price;
        this.description = description;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.imagePaths = imagePaths;
        this.imageUrl = imageUrl;
        this.rooms = rooms;
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
