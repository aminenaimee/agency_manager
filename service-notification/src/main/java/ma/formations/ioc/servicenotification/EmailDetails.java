package ma.formations.ioc.servicenotification;

import java.time.LocalDate;

public class EmailDetails {
    private Long reservationId;
    private Long hotelId;
    private String hotelName;
    private String hotelMail;
    private String roomName;
    private String roomType;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String departure;
    private LocalDate departureTime;


    public EmailDetails() {
    }

    public EmailDetails(Long reservationId, Long hotelId, String hotelName, String hotelMail, String roomName, String roomType, LocalDate checkIn, LocalDate checkOut, String departure, LocalDate departureTime) {
        this.reservationId = reservationId;
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelMail = hotelMail;
        this.roomName = roomName;
        this.roomType = roomType;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.departure = departure;
        this.departureTime = departureTime;
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

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public LocalDate getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDate departureTime) {
        this.departureTime = departureTime;
    }
}
