package ma.formations.ioc.servicehotel.entity;

import java.time.LocalDate;

public class Email {
    private Long reservationId;
    private Long hotelId;
    private String hotelName;
    private String hotelMail;
    private String roomName;
    private String roomType;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public Email() {
    }

    public Email(Long reservationId, Long hotelId, String hotelName, String hotelMail, String roomName, String roomType, LocalDate checkIn, LocalDate checkOut) {
        this.reservationId = reservationId;
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelMail = hotelMail;
        this.roomName = roomName;
        this.roomType = roomType;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelMail() {
        return hotelMail;
    }

    public void setHotelMail(String hotelMail) {
        this.hotelMail = hotelMail;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
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
}
