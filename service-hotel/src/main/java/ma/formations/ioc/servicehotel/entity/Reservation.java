package ma.formations.ioc.servicehotel.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientId;
    private Long hotelId;
    private Long roomId;
    private Long carId;
    private Long flightId;
    private Long totalPrice;

    public Reservation() {
    }

    public Reservation(Long flightId, Long carId, Long roomId, Long hotelId, String clientId, Long id) {
        this.flightId = flightId;
        this.carId = carId;
        this.roomId = roomId;
        this.hotelId = hotelId;
        this.clientId = clientId;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", hotelId=" + hotelId +
                ", roomId=" + roomId +
                ", carId=" + carId +
                ", flightId=" + flightId +
                '}';
    }

    public Long getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }
}


