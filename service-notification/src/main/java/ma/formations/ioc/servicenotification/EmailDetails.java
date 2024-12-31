package ma.formations.ioc.servicenotification;

import java.time.LocalDate;

public class EmailDetails {
    private Long reservationId;
    private String hotelName;
    private String hotelMail;
    private String roomName;
    private String roomType;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String departure;
    private LocalDate departureTime;
    private String VehicleName;
    private String VehicleBrand;
    private LocalDate rentStartDate;
    private LocalDate rentEndDate;

    public EmailDetails() {}

    // Getters and Setters
    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
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

    public String getVehicleName() {
        return VehicleName;
    }
    public void setVehicleName(String VehicleName) {
        this.VehicleName = VehicleName;
    }
    public String getVehicleBrand() {
        return VehicleBrand;
    }
    public void setVehicleBrand(String VehicleBrand) {
        this.VehicleBrand = VehicleBrand;
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


    @Override
    public String toString() {
        return "EmailDetails{" +
                "reservationId=" + reservationId +
                ", hotelName='" + hotelName + '\'' +
                ", hotelMail='" + hotelMail + '\'' +
                ", roomName='" + roomName + '\'' +
                ", roomType='" + roomType + '\'' +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", departure='" + departure + '\'' +
                ", departureTime=" + departureTime +
                '}';
    }
}
